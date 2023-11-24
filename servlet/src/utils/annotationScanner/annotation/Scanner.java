package utils.annotationScanner.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: reflect
 * @package: utils.annotationScanner.annotation
 * @className: Scanner
 * @author: 胡代伟
 * @description: 标注扫描的包（必须在src下）
 * @date: 2023/11/23 17:42
 * @version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scanner {
    // 相对于src的路径(格式为用.分割，不用/分割。如test.util，不以.开头)
    public String value();
}
