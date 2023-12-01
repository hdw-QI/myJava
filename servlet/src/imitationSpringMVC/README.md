模仿springmvc 一个servlet可以接收多个相关（如一个userServlet,这个servlet可以做添加、删除等与user相关的操作）的请求。

第一种方式：使用过滤器。思路：通关访问的url入手，如http://localhost:8080/a/b/adduser?a=123,这个adduser就是要执行的方法。
若有web上下文，则a为上下文路径，b为servlet访问路径

1、定义拦截器拦截所有请求，获取url。
2、截取url中最后一个/与第一个？（判断有没有参数）之间的值获取要执行的方法的名称。返回方法名称
3、在servlet中获取方法名，然后执行方法。（servlet之间有重名的方法名也不影响，因为一次请求只会进到一个servlet）


UrlFilter:拦截指定url

UrlFilter使用：
@Scanner("employee.controller")扫描该包下的WebServlet注解，http://localhost:8080/上下文路径/要调用那个类上的WebServlet的value值/方法名

第一种方式：定义一个baseServlet，重写service方法，来判断执行那个方法，其他要使用这种功能的servlet继承这个baseServlet。

扩展：使用自定义注解对应servlet下面的方法，访问的路径中的方法名就为自定义注解的值。没有使用自定义注解则默认为方法名与访问的路径中的方法名相同(使用抛方法没找到是使用注解APT)
@UrlMapping：用于方法上，值为访问的路径中的方法名
使用示例

1、过滤器
```java
@WebServlet("/uf/operationEmployee")
public class OperationEmployee extends HttpServlet {
    @UrlMapping("updateEmp")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write("哈哈");
    }
}
```

2、baseServlet
```java
@WebServlet("/test/*")
public class TestBaseServlet extends BaseServlet {
    @UrlMapping("testDelete")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletResponse.getWriter().write("删除");
    }
}
```