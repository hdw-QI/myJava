package utils.db.utils;

import test.Bean;
import utils.db.annotation.apt.ColumnAPT;
import utils.db.annotation.apt.IdAPT;
import utils.db.annotation.apt.TableAPT;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: myUtils
 * @package: utils.db.utils
 * @className: GetSQL
 * @author: 胡代伟
 * @description: 获取sql语句
 * @date: 2023/12/1 20:33
 * @version: 1.0
 */
public class GetSQL<T>{
    /**
     * @param bean: 实体类对象
     * @return Map<String,Object>
     * @author 胡代伟
     * @description 获取sql语句和与sql语句字段对应的值的数组
     * @date 2023/12/2 10:11
     */
    public Map<String,Object> insertOne(T bean){
        Map<String, Object> map = new HashMap<>();
        Class<?> aClass = bean.getClass();
        // 得到表名
        String tableValue = TableAPT.getTableValue(aClass);
        Map<String, String> idValue = IdAPT.getIdValue(aClass);
        // 这个值是一定有的，因为@id是必须要有的
        String id = idValue.get("id");
        String idField = idValue.get("idField");
        Map<String, String> colValue = ColumnAPT.getColValue(aClass);
        // 字段
        StringBuilder fieldListString=new StringBuilder();
        // 占位符
        StringBuilder placeholderString= new StringBuilder();

        Field[] declaredFields = aClass.getDeclaredFields();
        // 占位符对应的值
        Object[] objects = new Object[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            // 得到占位符
            if (placeholderString.length()==0){
                placeholderString.append("?");
            }else {
                placeholderString.append(",?");
            }
            // 得到数据库中的字段名
            if (declaredField.getName().equals(idField)){
                if (fieldListString.length()==0){
                    fieldListString.append(id);
                }else {
                    fieldListString.append(",").append(id);
                }
                objects[i] = getFieldValue(bean, idField);
            }
            for (Map.Entry<String, String> stringStringEntry : colValue.entrySet()) {
                if (stringStringEntry.getKey().equals(declaredField.getName())) {
                    if (stringStringEntry.getValue()!=null){
                        // 使用了@column注解
                        if (fieldListString.length()==0){
                            fieldListString.append(stringStringEntry.getValue());
                        }else {
                            fieldListString.append(",").append(stringStringEntry.getValue());
                        }
                    }else {
                        // 没使用@column注解
                        if (fieldListString.length()==0){
                            fieldListString.append(TransformHump.humpToLine(declaredField.getName()));
                        }else {
                            fieldListString.append(",").append(TransformHump.humpToLine(declaredField.getName()));
                        }
                    }
                    objects[i] = getFieldValue(bean, declaredField.getName());
                }
            }
        }

        String sql = GetSQLTemplate.insertOne(tableValue, fieldListString.toString(), placeholderString.toString());
        map.put("sql", sql);
        map.put("fieldValue", objects);
        return map;

    }

    /**
     * @param aClass: 实体类类模板
     * @param idList: id集合
     * @return String
     * @author 胡代伟
     * @description 根据id集合删除数据
     * @date 2023/12/2 11:15
     */
    public String deleteWhereIdIn(Class<T> aClass,List<Object> idList){
        // 得到表名
        String tableValue = TableAPT.getTableValue(aClass);
        // 占位符
        StringBuilder placeholderString= new StringBuilder();
        for (Object id : idList) {
            // 得到占位符
            if (placeholderString.length()==0){
                placeholderString.append("?");
            }else {
                placeholderString.append(",?");
            }
        }

        Map<String, String> idValue = IdAPT.getIdValue(aClass);
        // 这个值是一定有的，因为@id是必须要有的
        String id = idValue.get("id");
        return GetSQLTemplate.deleteWhereIdIn(tableValue, id, placeholderString.toString());
    }



    /**
     * @param bean: 实体类对象
     * @return Map<String,Object>
     * @author 胡代伟
     * @description 根据实体类对象修改数据
     * @date 2023/12/2 13:06
     */
    public Map<String,Object> updateWhereEqId(T bean){
        Map<String, Object> map = new HashMap<>();
        Class<?> aClass = bean.getClass();
        // 得到表名
        String tableValue = TableAPT.getTableValue(aClass);
        Map<String, String> idValue = IdAPT.getIdValue(aClass);
        // 这个值是一定有的，因为@id是必须要有的
        String id = idValue.get("id");
        String idField = idValue.get("idField");
        Map<String, String> colValue = ColumnAPT.getColValue(aClass);
        // 字段与占位符
        StringBuilder fieldAndPlaceholderListString=new StringBuilder();

        Field[] declaredFields = aClass.getDeclaredFields();
        // 占位符对应的值
        Object[] objects = new Object[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            for (Map.Entry<String, String> stringStringEntry : colValue.entrySet()) {
                if (stringStringEntry.getKey().equals(declaredField.getName())) {
                    if (stringStringEntry.getValue()!=null){
                        // 使用了@column注解
                        if (fieldAndPlaceholderListString.length()==0){
                            fieldAndPlaceholderListString.append(stringStringEntry.getValue()).append("= ?");
                        }else {
                            fieldAndPlaceholderListString.append(",").append(stringStringEntry.getValue()).append("= ?");
                        }
                    }else {
                        // 没使用@column注解
                        if (fieldAndPlaceholderListString.length()==0){
                            fieldAndPlaceholderListString.append(TransformHump.humpToLine(declaredField.getName())).append("= ?");
                        }else {
                            fieldAndPlaceholderListString.append(",").append(TransformHump.humpToLine(declaredField.getName())).append("= ?");
                        }
                    }
                    objects[i] = getFieldValue(bean, declaredField.getName());
                }
            }
        }
//        objects[declaredFields.length - 1] = getFieldValue(bean, idField);
        // id对应的值会为null所以将null后面的数据向前移一位，剩下最后一个位置放id的值
        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];
            if (object==null & i!=objects.length-1){
                for (int j = 0; j < objects.length-i-1; j++) {
                    objects[j]=objects[j+1];
                }
            }
        }
        objects[declaredFields.length - 1] = getFieldValue(bean, idField);
        String sql = GetSQLTemplate.updateWhereEqId(tableValue, fieldAndPlaceholderListString.toString(),id);
        map.put("sql", sql);
        map.put("fieldValue", objects);
        return map;
    }



    /**
     * @param aClass: 实体类类模板
     * @return String
     * @author 胡代伟
     * @description 获取查询所有的sql语句
     * @date 2023/12/2 13:55
     */
    public String queryAll(Class<T> aClass) {
        // 得到表名
        String tableValue = TableAPT.getTableValue(aClass);
        return GetSQLTemplate.queryAll(tableValue);
    }

    /**
     * @param aClass: 实体类类模板
     * @return String
     * @author 胡代伟
     * @description 获取分页查询所有的sql语句
     * @date 2023/12/2 13:56
     */
    public String queryPageAll(Class<T> aClass){
        String tableValue = TableAPT.getTableValue(aClass);
        return GetSQLTemplate.queryPageAll(tableValue);
    }

    /**
     * @param aClass: 实体类类模板
     * @param idList: id集合
     * @return String
     * @author 胡代伟
     * @description 获取查询指定id的sql语句
     * @date 2023/12/2 13:36
     */
    public String queryWhereInId(Class<T> aClass,List<Object> idList){
        // 得到表名
        String tableValue = TableAPT.getTableValue(aClass);
        // 占位符
        StringBuilder placeholderString= new StringBuilder();
        for (Object id : idList) {
            // 得到占位符
            if (placeholderString.length()==0){
                placeholderString.append("?");
            }else {
                placeholderString.append(",?");
            }
        }

        Map<String, String> idValue = IdAPT.getIdValue(aClass);
        // 这个值是一定有的，因为@id是必须要有的
        String id = idValue.get("id");
        return GetSQLTemplate.queryWhereInId(tableValue, id, placeholderString.toString());
    }


    /**
     * @param bean: 实体类对象
     * @param beanField: 实体类属性名
     * @return Object
     * @author 胡代伟
     * @description 通过实体类属性的getter方法获取实体属性的值
     * @date 2023/12/2 9:40
     */
    public Object getFieldValue(T bean,String beanField){
        String getter = GetterAndSetter.getGetter(beanField);
        try {
            Class<?> beanClass = bean.getClass();
            Method declaredMethod = beanClass.getDeclaredMethod(getter);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(bean);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        GetSQL<Bean> beanGetSQL = new GetSQL<>();
        Bean bean = new Bean();
        bean.setId(23);
        bean.setAge(18);
        bean.setName("琪琪");
//        Map<String, Object> map = beanGetSQL.insertOne(bean);
//        System.out.println(map);
//        Object o = map.get("fieldValue");
//        Object[] objects=(Object[])o;
//        for (Object object : objects) {
//            System.out.println(object);
//        }

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
//        String s = beanGetSQL.deleteWhereIdIn(Bean.class, objects);
//        System.out.println(s);


//        Map<String, Object> map = beanGetSQL.updateWhereEqId(bean);
//        System.out.println(map);
//        Object o = map.get("fieldValue");
//        Object[] objects=(Object[])o;
//        for (Object object : objects) {
//            System.out.println(object);
//        }

//        String s = beanGetSQL.queryWhereInId(Bean.class, objects);
//        System.out.println(s);
    }
}
