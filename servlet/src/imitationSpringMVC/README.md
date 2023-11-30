模仿springmvc 一个servlet可以接收多个相关（如一个userServlet,这个servlet可以做添加、删除等与user相关的操作）的请求。

思路：通关访问的url入手，如http://localhost:8080/a/b/adduser?a=123,这个adduser就是要执行的方法。
若有web上下文，则a为上下文路径，b为servlet访问路径

1、定义拦截器拦截所有请求，获取url。
2、截取url中最后一个/与第一个？（判断有没有参数）之间的值获取要执行的方法的名称。返回方法名称
3、在servlet中获取方法名，然后执行方法。（servlet之间有重名的方法名也不影响，因为一次请求只会进到一个servlet）


UrlFilter:拦截指定url

UrlFilter使用：
@Scanner("employee.controller")扫描该包下的WebServlet注解，http://localhost:8080/上下文路径/要调用那个类上的WebServlet的value值/方法名
