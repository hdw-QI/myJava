package aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @projectName: git
 * @package: aop.xml
 * @className: MyAspect
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 19:28
 * @version: 1.0
 */
@Component
@Aspect
public class MyAspect {
    /*注意：要开启包扫描和aop注解功能*/

    /*前置通知会自动放行*/
//    @Before("execution(* aop.annotation.MyTarget.*(..))")
    public void beforeMethod(){
        System.out.println("MyAspect前置通知");
    }

//    @After("execution(* aop.annotation.MyTarget.*(..))")
    public void afterMethod(){
        System.out.println("最终通知");
    }

//    @Before("execution(* aop.annotation.MyTarget.*(..))")
    public void throwingMethod(){
        System.out.println("异常通知");
    }

//    @AfterReturning("execution(* aop.annotation.MyTarget.*(..))")
    public void returningMethod(){
        System.out.println("后置通知");
    }


    /*注意环绕通知需要ProceedingJoinPoint类型的对象*/
    @Around("execution(* aop.annotation.MyTarget.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint){
//        boolean flag = false;
        boolean flag = true;
        if(flag){

            beforeMethod();

            System.out.println("有权限");

            try {
                //放行。前置通知会自动放行。放行前可以看作前置通知，放行后可以看作后置通知，catch里的可以看作异常通知，finally里面的可以看作最终通知
                proceedingJoinPoint.proceed();
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
