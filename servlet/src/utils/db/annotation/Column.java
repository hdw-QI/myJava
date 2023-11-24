package utils.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: utils
 * @package: utils.db.annotation
 * @className: Column
 * @author: 胡代伟
 * @description: 用于标注实体类除id之外的字段，用于标注在数据库中相应的字段名
 * @date: 2023/11/24 8:53
 * @version: 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    public String value();
}
