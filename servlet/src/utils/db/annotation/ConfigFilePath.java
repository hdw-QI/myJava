package utils.db.annotation;

import java.lang.annotation.*;

/**
 * @projectName: scond_stage
 * @package: utils.db.annotation
 * @className: PropertiesConfigPath
 * @author: 胡代伟
 * @description: 用于类、方法上，标明配置文件的路径,在apt中自己判断文件的后缀名（properties、xml）
 * @date: 2023/11/8 19:46
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ConfigFilePath {
    String value();
}
