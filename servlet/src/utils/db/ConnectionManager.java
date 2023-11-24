package utils.db;

import utils.db.annotation.ConfigFilePath;
import utils.db.annotation.apt.ConfigFilePathAPT;
import utils.db.annotation.bean.ConfigConnectionParameters;

import java.lang.reflect.Method;
import java.sql.*;

/**
 * @projectName: scond_stage
 * @package: utils.db
 * @className: DriverManager
 * @author: 胡代伟
 * @description: 连接管理器
 * @date: 2023/11/8 20:29
 * @version: 1.0
 */
public class ConnectionManager {
    /**
     * @param :
     * @return Connection
     * @author 胡代伟
     * @description 获取数据库连接
     * @date 2023/11/9 8:40
     */
    public static Connection getConnection(){
        String path=null;
        // 获取当前方法在那个类中被调用，返回的是全类名
        String className = new Throwable().getStackTrace()[1].getClassName();
        Class<?> invokeClass=null;
        try {
            invokeClass = Class.forName(className);
            ConfigFilePath annotationClass = invokeClass.getAnnotation(ConfigFilePath.class);
            //如果有注解，获取路径
            if (annotationClass!=null){
                path = annotationClass.value();
            }else {
                String methodName = new Throwable().getStackTrace()[1].getMethodName();
                Method declaredMethod = invokeClass.getDeclaredMethod(methodName);
                ConfigFilePath annotationMethods = declaredMethod.getAnnotation(ConfigFilePath.class);
                if (annotationMethods!=null){
                    path = annotationMethods.value();
                }
            }

            if (path==null){
                throw new RuntimeException("无数据库连接参数");
            }

            ConfigConnectionParameters connectionParameters = ConfigFilePathAPT.getPropertiesConfig(path);

            Class.forName(connectionParameters.getDriver());
            Connection connection = DriverManager.getConnection(connectionParameters.getUrl(), connectionParameters.getUsername(), connectionParameters.getPassword());
            if (connection==null){
                throw new RuntimeException("数据库连接失败");
            }
            System.out.println("数据库连接成功");
            return connection;
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param connection:
     * @param preparedStatement:
     * @param resultSet:
     * @return void
     * @author 胡代伟
     * @description 释放资源
     * @date 2023/11/9 8:41
     */
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
