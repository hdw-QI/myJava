package utils.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: utils
 * @package: utils.db.annotation
 * @className: Table
 * @author: 胡代伟
 * @description: 用于实体类的类上，用于标注实体类在数据库中的表名
 * @date: 2023/11/24 8:52
 * @version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    public String value();
}
