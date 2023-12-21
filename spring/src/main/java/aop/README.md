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

