package utils.db.execution.impl;

import utils.db.ConnectionManager;
import utils.db.SQLExecutorManager;
import utils.db.execution.ExecutionDQL;
import utils.db.model.PageParams;
import utils.db.model.PageResult;
import utils.db.utils.TransformHump;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @projectName: scond_stage
 * @package: utils.db.execution.impl
 * @className: ExecutionDQLImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/9 12:43
 * @version: 1.0
 */
public class ExecutionDQLImpl<T> implements ExecutionDQL<T> {
    @Override
    public T queryBean(Connection connection, String sql, Class<T> beanClass, Object... parameters) {
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        ResultSet resultSet = getResultSet(preparedStatement, parameters);
        if (resultSet == null) {
            throw new RuntimeException("获取结果集异常");
        }
        T bean = getBean(beanClass, resultSet);
        ConnectionManager.closeConnection(null,preparedStatement,null);
        return bean;
    }

    @Override
    public List<T> queryListBean(Connection connection, String sql, Class<T> beanClass, Object... parameters) {
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        ResultSet resultSet = getResultSet(preparedStatement, parameters);
        if (resultSet == null) {
            throw new RuntimeException("获取结果集异常");
        }
        List<T> listBean = getListBean(beanClass, resultSet);
        ConnectionManager.closeConnection(null,preparedStatement,null);
        return listBean;
    }

    @Override
    public List<T> queryListBean(Connection connection, String sql, Class<T> beanClass) {
        return queryListBean(connection, sql, beanClass, null);
    }

    @Override
    public Map<String, Object> queryMapFields(Connection connection, String sql, Object... parameters) {
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        ResultSet resultSet = getResultSet(preparedStatement, parameters);
        if (resultSet == null) {
            throw new RuntimeException("获取结果集异常");
        }
        Map<String, Object> map = getMap(resultSet);
        ConnectionManager.closeConnection(null,preparedStatement,null);
        return map;
    }

    @Override
    public List<Map<String, Object>> queryListMapFields(Connection connection, String sql, Object... parameters) {
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        ResultSet resultSet = getResultSet(preparedStatement, parameters);
        if (resultSet == null) {
            throw new RuntimeException("获取结果集异常");
        }
        List<Map<String, Object>> listMap = getListMap(resultSet);
        ConnectionManager.closeConnection(null,preparedStatement,null);
        return listMap;
    }

    @Override
    public List<Map<String, Object>> queryListMapFields(Connection connection, String sql) {
        return queryListMapFields(connection, sql, null);
    }

    @Override
    public PageResult<T> queryPageBean(Connection connection, Class<T> beanClass, String sql, PageParams pageParams, Object... parameters) {
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        ResultSet resultSet = getPageResultSet(preparedStatement,pageParams, parameters);
        if (resultSet == null) {
            throw new RuntimeException("获取结果集异常");
        }
        // 结果数据
        List<T> listBean = getListBean(beanClass, resultSet);
        // 满足条件的所有数据
        long pageTotal = getTotal(connection, sql, parameters);
        ConnectionManager.closeConnection(null,preparedStatement,null);
        return getPageResultByList(listBean,pageTotal,pageParams);
    }

    @Override
    public PageResult<T> queryPageBean(Connection connection, Class<T> beanClass, String sql, PageParams pageParams) {
        return queryPageBean(connection,beanClass,sql,pageParams,null);
    }

    @Override
    public PageResult<Map<String, Object>> queryPageMapFields(Connection connection, String sql, PageParams pageParams, Object... parameters) {
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        ResultSet resultSet = getPageResultSet(preparedStatement,pageParams,parameters);
        if (resultSet == null) {
            throw new RuntimeException("获取结果集异常");
        }
        List<Map<String, Object>> listMap = getListMap(resultSet);
        // 满足条件的所有数据
        long pageTotal = getTotal(connection, sql, parameters);
        ConnectionManager.closeConnection(null,preparedStatement,null);
        return getPageResultByListMap(listMap,pageTotal,pageParams);
    }

    @Override
    public PageResult<Map<String, Object>> queryPageMapFields(Connection connection, String sql, PageParams pageParams) {
        return queryPageMapFields(connection,sql,pageParams,null);
    }

    /**
     * @param connection:
     * @param sql:
     * @return PreparedStatement
     * @author 胡代伟
     * @description 获取PreparedStatement对象
     * @date 2023/11/9 14:50
     */
    public PreparedStatement getPreparedStatement(Connection connection, String sql){
        PreparedStatement preparedStatement =null;
        if (connection!=null & sql!=null){
            preparedStatement = SQLExecutorManager.getPreparedStatement(connection, sql);
        }
        if (preparedStatement==null){
            throw new RuntimeException("获取PreparedStatement对象异常");
        }
        return preparedStatement;
    }
    /**
     * @param preparedStatement:
     * @param parameters:
     * @return ResultSet
     * @author 胡代伟
     * @description 获取结果集
     * @date 2023/11/9 14:56
     */
    public ResultSet getResultSet(PreparedStatement preparedStatement, Object... parameters){
        if (parameters!=null){
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                try {
                    preparedStatement.setObject(i+1,parameter);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param preparedStatement:
     * @param pageParams:
     * @param parameters:
     * @return ResultSet
     * @author 胡代伟
     * @description 获取分页后的结果集
     * @date 2023/11/10 17:51
     */
    public ResultSet getPageResultSet(PreparedStatement preparedStatement,PageParams pageParams, Object... parameters){
        int i ;
        int parametersLength;
        if (parameters!=null){
            parametersLength = parameters.length;
            for (i = 0; i < parametersLength; i++) {
                Object parameter = parameters[i];
                try {
                    preparedStatement.setObject(i+1,parameter);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            parametersLength=0;
        }

        // 替换分页参数
        try {
            preparedStatement.setObject(parametersLength+1,(pageParams.getPageNo()-1)*pageParams.getPageSize());
            preparedStatement.setObject(parametersLength+2,pageParams.getPageSize());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long getTotal(Connection connection, String sql, Object... parameters){
        String limit = sql.split("limit")[0];
        if (limit.equals(sql)){
            limit = sql.split("LIMIT")[0];
        }
        List<Map<String, Object>> maps = queryListMapFields(connection, limit, parameters);
        return maps.size();
    }

    /**
     * @param list:
     * @param total:
     * @param pageParams:
     * @return PageResult<T>
     * @author 胡代伟
     * @description 通过list集合封装分页结果
     * @date 2023/11/10 18:16
     */
    public PageResult<T> getPageResultByList(List<T> list,long total, PageParams pageParams){
        PageResult<T> tPageResult = new PageResult<>();
        tPageResult.setResult(list);
        tPageResult.setTotalCounts(total);
        tPageResult.setPageNo(pageParams.getPageNo());
        tPageResult.setPageSize(pageParams.getPageSize());

        if (total % pageParams.getPageSize()==0){
            tPageResult.setTotalPages(total/pageParams.getPageSize());
        }else {
            tPageResult.setTotalPages((total/pageParams.getPageSize())+1);
        }
        return tPageResult;
    }

    /**
     * @param map:
     * @param total:
     * @param pageParams:
     * @return PageResult<T>
     * @author 胡代伟
     * @description 通过list<map>集合封装分页结果
     * @date 2023/11/10 19:26
     */
    public PageResult<Map<String,Object>> getPageResultByListMap(List<Map<String,Object>> map,long total,PageParams pageParams){
        PageResult<Map<String,Object>> tPageResult = new PageResult<>();
        tPageResult.setResult(map);
        tPageResult.setTotalCounts(total);
        tPageResult.setPageNo(pageParams.getPageNo());
        tPageResult.setPageSize(pageParams.getPageSize());

        if (total% pageParams.getPageSize()==0){
            tPageResult.setTotalPages(total/pageParams.getPageSize());
        }else {
            tPageResult.setTotalPages((total/pageParams.getPageSize())+1);
        }

        return tPageResult;
    }


    /**
     * @param beanClass:
     * @param resultSet:
     * @return T
     * @author 胡代伟
     * @description 获取指定的类型对象
     * @date 2023/11/9 12:57
     */
    public T getBean(Class<T> beanClass,ResultSet resultSet){
        List<T> listBean = getListBean(beanClass, resultSet);
        if (listBean.size()>0){
            return listBean.get(0);
        }
        return null;
    }

    /**
     * @param beanClass:
     * @param resultSet:
     * @return List<T>
     * @author 胡代伟
     * @description 获取指定的类型对象的list集合
     * @date 2023/11/9 12:58
     */
    public List<T> getListBean(Class<T> beanClass,ResultSet resultSet){
        if (beanClass == null) {
            throw new RuntimeException("类模板参数异常");
        }
        Constructor<T> declaredConstructor=null;
        T newInstance=null;
        List<T> ts = new ArrayList<>();
        try {
            declaredConstructor = beanClass.getDeclaredConstructor();
            Field[] declaredFields = beanClass.getDeclaredFields();

            Object dbValue =null;
            while (resultSet.next()){
                newInstance = declaredConstructor.newInstance();
                for (Field declaredField : declaredFields) {
                    // 驼峰转下划线
                    String dbField = TransformHump.humpToLine(declaredField.getName());
                    // 设置成员属性
                    String beanField = declaredField.getName();
                    String setMethodNameString = "set" + String.valueOf(beanField.charAt(0)).toUpperCase() + beanField.substring(1);
                    // 获取成员属性的类型
                    Class<?> type = declaredField.getType();
                    Method declaredMethod = beanClass.getDeclaredMethod(setMethodNameString,type);

                    dbValue = resultSet.getObject(dbField);
                    declaredMethod.invoke(newInstance,dbValue);
                }
                ts.add(newInstance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            // 防止有其他与数据库表列名无关的字段引起程序终止
            System.out.println(e.getMessage());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.closeConnection(null,null,resultSet);
        }
        return ts;
    }

    /**
     * @param resultSet:
     * @return Map<String,Object>
     * @author 胡代伟
     * @description 获取装载结果的map集合
     * @date 2023/11/9 13:01
     */
    public Map<String,Object> getMap(ResultSet resultSet){
        List<Map<String, Object>> listMap = getListMap(resultSet);
        if (listMap.size()>0){
            return listMap.get(0);
        }else {
            return null;
        }

    }

    /**
     * @param resultSet:
     * @return List<Map<String,Object>>
     * @author 胡代伟
     * @description 获取装载结果的map集合的List集合
     * @date 2023/11/9 13:03
     */
    public List<Map<String,Object>> getListMap(ResultSet resultSet){
        HashMap<String, Object> map = null;
        ArrayList<Map<String, Object>> mapArrayList = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                map=new HashMap<>();
                for (int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    Object object = resultSet.getObject(columnName);
                    map.put(columnName, object);
                }
                mapArrayList.add(map);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionManager.closeConnection(null,null,resultSet);
        }
        return mapArrayList;
    }


}
