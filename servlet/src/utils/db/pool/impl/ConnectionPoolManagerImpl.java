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
 * @author: ����ΰ
 * @description: ���ݿ����ӳظ��ӿ�ʵ����
 * @date: 2023/11/10 16:18
 * @version: 1.0
 */
public class ConnectionPoolManagerImpl implements ConnectionPoolManager {
    private static final int CONN_COUNT = 5;
    //�������ӵļ���
    public static final List<Connection> connections;

    //��ʼ�����ݿ����ӳ�
    static {
        //ʹ�ð�ȫ���ϲŴ洢����
        connections = Collections.synchronizedList(new LinkedList<>());

        String path=null;
        // ��ȡ��ǰ�������Ǹ����б����ã����ص���ȫ����
        String className = new Throwable().getStackTrace()[1].getClassName();
        Class<?> invokeClass=null;
        try {
            invokeClass = Class.forName(className);
            ConfigFilePath annotationClass = invokeClass.getAnnotation(ConfigFilePath.class);
            //�����ע�⣬��ȡ·��
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
                throw new RuntimeException("�����ݿ����Ӳ���");
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            //��ʼ��Connection����
            for (int i = 0; i < CONN_COUNT; i++) {
                ConfigConnectionParameters connectionParameters = ConfigFilePathAPT.getPropertiesConfig(path);

                Class.forName(connectionParameters.getDriver());
                Connection connection = DriverManager.getConnection(connectionParameters.getUrl(), connectionParameters.getUsername(), connectionParameters.getPassword());
                if (connection==null){
                    throw new RuntimeException("���ݿ�����ʧ��");
                }
                System.out.println("���ݿ����ӳɹ�");
                connections.add(connection);
            }
            System.out.println("���ݿ����ӳس�ʼ�����");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("��ʼ��ʧ��");
        }
    }

    @Override
    public Connection getConnectionFromPool() {
        synchronized (connections) {
            if (connections.size() > 0) {
                //����һ��,�ǽ��ĸ�����ȡ��
                Connection connection = connections.remove(0);
                System.out.println("ʹ����Connection:"+connection.toString());
                return connection;
            }
        }
        return null;
    }

    @Override
    public void backConnectionToPool(Connection connection) {
        //����һ��,�ǽ��ĸ����ӷ��������ݿ�
        System.out.println("�黹��Connection:"+connection.toString());
        connections.add(connection);
    }

    @Override
    public void closeAllConnectionFromPool() {
        for (int i = 0; i < CONN_COUNT; i++) {
            Connection connection = connections.remove(0);
            ConnectionManager.closeConnection(connection,null,null);
            System.out.println("�ر���Connection:"+connection.toString());

        }
    }
}
