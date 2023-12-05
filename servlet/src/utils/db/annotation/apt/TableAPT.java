package utils.db.annotation.apt;

import utils.db.annotation.Table;

/**
 * @projectName: utils
 * @package: utils.db.annotation.apt
 * @className: TableAPT
 * @author: 胡代伟
 * @description: 处理Table注解
 * @date: 2023/11/24 9:17
 * @version: 1.0
 */
public class TableAPT {
    /**
     * @param targetClass: 实体类的class
     * @return String Table注解的值,若没有使用注解则为类的名称
     * @author 胡代伟
     * @description 获取实体类中Table注解的值
     * @date 2023/12/1 15:17
     */
    public static String getTableValue(Class<?> targetClass){
        Table annotation = targetClass.getAnnotation(Table.class);
        String value = annotation.value();
        if (value == null) {
            return targetClass.getName();
        }
        return value;
    }
}
