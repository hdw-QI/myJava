package utils.db.annotation.apt;

import utils.db.annotation.Column;
import utils.db.annotation.Id;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: utils
 * @package: utils.db.annotation.apt
 * @className: ColumnAPT
 * @author: 胡代伟
 * @description: 处理Column注解
 * @date: 2023/11/24 9:17
 * @version: 1.0
 */
public class ColumnAPT {
    /**
     * @param targetClass: 实体类的class
     * @return Map<String,String> 键为实体类属性的名称(不包括@id对应的属性名)，值为注解的值，没有用注解则值为null
     * @author 胡代伟
     * @description 获取实体类中Column注解的值
     * @date 2023/12/1 15:00
     */
    public static Map<String,String> getColValue(Class<?> targetClass){
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Column annotation = declaredField.getAnnotation(Column.class);
            String name = declaredField.getName();
            if (annotation == null) {
                Id annotation1 = declaredField.getAnnotation(Id.class);
                if (annotation1 == null) {
                    map.put(name, null);
                }
            }else {
                String value = annotation.value();
                map.put(name, value);
            }
        }
        return map;
    }
}
