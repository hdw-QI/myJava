package utils.db.utils;

/**
 * @projectName: myUtils
 * @package: utils.db.utils
 * @className: GetterAndSetter
 * @author: 胡代伟
 * @description: 获取实体类的get方法和set方法的方法名
 * @date: 2023/12/2 9:27
 * @version: 1.0
 */
public class GetterAndSetter {
    /**
     * @param fieldName: 类属性名
     * @return String
     * @author 胡代伟
     * @description 获取实体类的get方法名
     * @date 2023/12/2 9:32
     */
    public static String getGetter(String fieldName){
        return "get" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
    }

    /**
     * @param fieldName: 类属性名
     * @return String
     * @author 胡代伟
     * @description 获取实体类的set方法名
     * @date 2023/12/2 9:32
     */
    public static String getSetter(String fieldName){
        return "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
    }

    public static void main(String[] args) {
        String getter = getGetter("name");
        System.out.println(getter);

        String setter = getSetter("name");
        System.out.println(setter);
    }
}
