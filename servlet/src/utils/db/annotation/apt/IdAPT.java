package utils.db.annotation.apt;

import utils.db.annotation.Column;
import utils.db.annotation.Id;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: utils
 * @package: utils.db.annotation.apt
 * @className: IdAPT
 * @author: 胡代伟
 * @description: 处理Id注解
 * @date: 2023/11/24 9:17
 * @version: 1.0
 */
public class IdAPT {
    /**
     * @param targetClass: 实体类的class
     * @return Map<String,String>键为实体类属性的名称，值为注解的值，没有用注解则值为null
     * @author 胡代伟
     * @description 获取实体类中Id注解的值
     * @date 2023/12/1 15:12
     */
    public static Map<String,String> getIdValue(Class<?> targetClass){
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Id annotation = declaredField.getAnnotation(Id.class);
            if (annotation != null) {
                String value = annotation.value();
                map.put("id", value);
                map.put("idField", declaredField.getName());
                break;

            }else {
                throw new RuntimeException("不知道主键字段");
            }
        }
        return map;
    }
}
