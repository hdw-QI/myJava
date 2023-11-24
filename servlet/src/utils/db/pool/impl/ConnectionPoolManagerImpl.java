package utils.db.pool.impl;

import utils.db.ConnectionManager;
import utils.db.annotation.ConfigFilePath;
import utils.db.annotation.apt.ConfigFilePathAPT;
import utils.db.annotation.bean.ConfigConnectionParameters;
import utils.db.pool.ConnectionPoolManager;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @projectName: scond_stage
 * @package: utils.db.pool.impl
 * @className: ConnectionPoolManagerImpl
 * @author: 胡代伟
 * @description: 数据库连接池父接口实现类
 * @date: 2023/11/10 16:18
 * @version: 1.0
 */
public class ConnectionPoolManagerImpl implements ConnectionPoolManager {
    private static final int CONN_COUNT = 5;
    //创建连接的集合
    public static final List<Connection> connections;

    //初始化数据库连接池
    static {
        //使用安全集合才存储连接
        connections = Collections.synchronizedList(new LinkedList<>());

        String path=null;
        // 获取当前方法在那个类中被调用，返回的是全类名
        String className = new Throwable().getStackTrace()[1].getClassName();
        Class<?> invokeClass=null;
        try {
            invokeClass = Class.forName(className);
            ConfigFilePath annotationClass = invokeClass.getAnnotation(ConfigFilePath.class);
            //如果有注解，获取路径
            if (annotationClass != null) {
                path = annotationClass.value();
            } else {
                String methodName = new Throwable().getStackTrace()[1].getMethodName();
                Method declaredMethod = invokeClass.getDeclaredMethod(methodName);
                ConfigFilePath annotationMethods = declaredMethod.getAnnotation(ConfigFilePath.class);
                if (annotationMethods != null) {
                    path = annotationMethods.value();
                }
            }

            if (path == null) {
                throw new RuntimeException("无数据库连接参数");
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            //初始化Connection对象
            for (int i = 0; i < CONN_COUNT; i++) {
                ConfigConnectionParameters connectionParameters = ConfigFilePathAPT.getPropertiesConfig(path);

                Class.forName(connectionParameters.getDriver());
                Connection connection = DriverManager.getConnection(connectionParameters.getUrl(), connectionParameters.getUsername(), connectionParameters.getPassword());
                if (connection==null){
                    throw new RuntimeException("数据库连接失败");
                }
                System.out.println("数据库连接成功");
                connections.add(connection);
            }
            System.out.println("数据库连接池初始化完成");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化失败");
        }
    }

    @Override
    public Connection getConnectionFromPool() {
        synchronized (connections) {
            if (connections.size() > 0) {
                //测试一下,是将哪个链接取出
                Connection connection = connections.remove(0);
                System.out.println("使用了Connection:"+connection.toString());
                return connection;
            }
        }
        return null;
    }

    @Override
    public void backConnectionToPool(Connection connection) {
        //测试一下,是将哪个链接返回了数据库
        System.out.println("归还了Connection:"+connection.toString());
        connections.add(connection);
    }

    @Override
    public void closeAllConnectionFromPool() {
        for (int i = 0; i < CONN_COUNT; i++) {
            Connection connection = connections.remove(0);
            ConnectionManager.closeConnection(connection,null,null);
            System.out.println("关闭了Connection:"+connection.toString());

        }
    }
}
