package imitationSpringMVC.annotation.apt;

import imitationSpringMVC.annotation.UrlMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @projectName: git
 * @package: imitationSpringMVC.annotation.apt
 * @className: UrlMappingAPT
 * @author: 胡代伟
 * @description: 处理UrlMapping注解
 * @date: 2023/12/1 10:45
 * @version: 1.0
 */
public class UrlMappingAPT {
    public static void isMapping(Class<?> targetClass, Object targetObject, String urlMethodName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        boolean flag=true;
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            UrlMapping annotation = declaredMethod.getAnnotation(UrlMapping.class);
            if (annotation!=null){
                String value = annotation.value();
                if (value.equals(urlMethodName)){
                    flag=false;
                    try {
                        declaredMethod.invoke(targetObject, httpServletRequest, httpServletResponse);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        if (flag){
            try {
                httpServletResponse.getWriter().write("method not find");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
