package utils.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: utils
 * @package: utils.db.annotation
 * @className: Id
 * @author: 胡代伟
 * @description: 用于实体类的字段上，用于标注在数据库中主键的字段名。必须用于实体上
 * @date: 2023/11/24 8:53
 * @version: 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    public String value();
}
