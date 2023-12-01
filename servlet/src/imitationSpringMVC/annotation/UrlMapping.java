package imitationSpringMVC.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: git
 * @package: imitationSpringMVC.annotation
 * @className: UrlMapping
 * @author: 胡代伟
 * @description: 用于方法上，值为访问的路径中的方法名
 * @date: 2023/12/1 10:43
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlMapping {
    String value();
}
