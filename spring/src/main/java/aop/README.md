使用AOP

1,引入aop以及切面的依赖

```xml
<!--   aspectj 切面依赖-->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjtools</artifactId>
      <version>1.8.13</version>
    </dependency>

```

2,在核心配置文件中引入aop的命名空间

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">
```

3,基于xml实现AOP+AspectJ

切入点配置:

```properties
切入点的表达式:
execution(* cn.codeartist.spring.aop.advice.*.*(..))


execution(返回值类型  方法路径（方法名） 参数列表)


execution(* com.service.UserServiceImpl.*(..))

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">

<!--    基于注解实现对象的创建-->

<!--    开启组件的扫描-->
<!--        <context:component-scan base-package="com.controller"></context:component-scan>-->

       <context:component-scan base-package="com.service"></context:component-scan>

    <bean id="myAspect" class="com.aspect.MyAspect"></bean>

    <bean id="logAspect" class="com.aspect.LogAspect"></bean>

    <aop:config>
        
        <aop:pointcut id="myPointCut" expression="execution(void com.service.UserServiceImpl.delete(..))"/>

        <aop:aspect ref="myAspect">

<!--            前置通知-->
<!--            <aop:before method="beforeMethod" pointcut-ref="myPointCut"></aop:before>-->
<!--            最后通知-->
<!--            <aop:after method="afterMethod" pointcut-ref="myPointCut"></aop:after>-->
<!--            异常通知-->
<!--            <aop:after-throwing method="throwingMethod"  pointcut-ref="myPointCut"></aop:after-throwing>-->
            <aop:around method="around" pointcut-ref="myPointCut"></aop:around>


        </aop:aspect>

        <aop:aspect ref="logAspect" >
            <aop:before method="xxx" pointcut-ref="myPointCut"></aop:before>
        </aop:aspect>
    </aop:config>


</beans>
```

4,切面类

```java
public class MyAspect {

    public void beforeMethod(){
        System.out.println("MyAspect前置通知");
    }


    public void afterMethod(){
        System.out.println("最后通知");
    }

    public void throwingMethod(){
        System.out.println("异常通知");
    }

    public void returningMethod(){
        System.out.println("后置通知");
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint){
        boolean flag = false;
       if(flag){

           beforeMethod();

           System.out.println("有权限");

           try {
               proceedingJoinPoint.proceed(); //放行
           } catch (Throwable e) {
               throwingMethod();
           }finally {
               afterMethod();
           }
           returningMethod();
       }else{
           System.out.println("没有权限");
       }

    }

}
```



基于注解AOP+AspectJ

```java
@Component
@Aspect
public class NewAspect {

//    定义切入点

    @Pointcut("execution(* com.service.UserServiceImpl.*(..))")
    //myPoint  方法名  就是切入点的名字
    public void myPoint(){

    }
    @Pointcut("execution(void com.service.UserServiceImpl.delete(..))")
    public void myPoint1(){

    }

    @Before("myPoint1()")
    public void beforeMethod(){
        System.out.println("前置通知");
    }

    @After("myPoint()")
    public void after(){
        System.out.println("最后通知");
    }


}

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">

       <context:component-scan base-package="com.service"></context:component-scan>
     <context:component-scan base-package="com.aspect"></context:component-scan>

<!--    开启AOP的注解功能-->
        <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>
```
aop:aspectj-autoproxy标签属性详解

proxy-target-class:
Spring AOP 部分使用JDK动态代理或者CGLIB来为目标对象创建代理.(建议尽量使用JDK的动态代理),如果被代理的目标对象实现了至少一个接口,则会使用JDK动态代理,所以该目标类型实现的接口都将被代理.
也就是说当目标对象实现了接口,则默认使用JDK的动态代理,若该目标对象没有实现任何接口,则创建一个CGLIB代理,如果你希望强制使用CGLIB代理,则需要将该参数显示的设置成true.
注意:
1.使用JDK动态代理,只能代理该目标对象实现接口的方法,并不能代理实现类中自己定义的方法.
2.如果你希望代理目标对象的所有方法,而不只是实现自接口的方式,可以使用CGLIB代理,本质是扩展目标对象类,但是需要考虑一下两个问题:
1)无法通知(advise)Final方法,因为他们不能被覆盖写
2)你需要将CGLIB的jar包放在classpath下面
3.当需要使用CGLIB代理和@AspectJ自动代理支持,使用该代码设置<aop:aspectj-autoproxy proxy-target-class="true" expose-proxy="false" />

expose-proxy :
expose-proxy。为是否暴露当前代理对象为ThreadLocal模式。
有时候目标对象内部的自我调用将无法实施切面中的增强
我们先来看一个问题: @Transacitonal注解的方法被另外一个方法调用的时候，事务是不生效的。具体不生效代码如下

```java
public interface UserService{
public void a();
public void b();
}

public class UserServiceImpl implements UserService{
@Transactional(propagation = Propagation.REQUIRED)
public void a(){
this.b();
}

@Transactional(propagation = Propagation.REQUIRED_NEW)
public void b(){
System.out.println("b has been called");
}
}
```

分析:
SpringAOP对于最外层的函数只拦截public方法，不拦截protected和private方法，另外不会对最外层的public方法内部调用的其他方法也进行拦截，即只停留于代理对象所调用的方法。所以我们会发现如果调用a方法,在b方法中出现异常时,事务不生效.
解决办法:
答案就是在<aop:aspectj-autoproxy />中设置expose-proxy属性为true暴露代理.<aop:aspectj-autoproxy expose-proxy=“true”> ，然后使用AopContext.currentProxy()获取当前代理，将this.b()改为((UserService)AopContext.currentProxy()).b()，这样就生效了。
分析参考: https://www.cnblogs.com/chihirotan/p/7356683.html

关于JDK和CGLIB方式的总结:
如果目标对象实现了接口,默认情况会采用JDK自动代理实现AOP
如果目标对象实现了接口,可以强制使用CGLIB实现AOP
如果目标对象没有实现接口,必须采用CGLIB库,Spring会在JDK动态代理和CGLIB之间转换.

如何强制使用CGLIB实现AOP?
1.添加CGLIB依赖库
2.在Spring配置文件中加入<aop:aspectj-autoproxy proxy-target-class="true" />

JDK动态代理和CGLIB字节码生成的区别?
JDK动态代理只能对实现了接口的类生成代理,而不能针对类.
CGLIB是针对类实现代理,主要是对指定的类生成一个子类,覆盖其中的方法,因为是继承,所以该类或方法最好不要声明成final.

