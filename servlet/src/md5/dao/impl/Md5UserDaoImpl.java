package md5.dao.impl;

import md5.dao.Md5UserDao;
import md5.domain.dto.LoginDto;
import md5.domain.entity.Md5User;
import utils.db.annotation.ConfigFilePath;
import utils.db.execution.ExecutionDQL;
import utils.db.execution.NoSQL;
import utils.db.execution.impl.ExecutionDQLImpl;
import utils.db.execution.impl.NoSQLImpl;
import utils.db.pool.ConnectionPoolManager;
import utils.db.pool.impl.ConnectionPoolManagerImpl;

import java.sql.Connection;
import java.util.Map;

/**
 * @projectName: git
 * @package: md5.dao.impl
 * @className: Md5UserDaoImpl
 * @author: 胡代伟
 * @description: Md5UserDaoImpl
 * @date: 2023/12/5 13:09
 * @version: 1.0
 */
@ConfigFilePath("db.properties")
public class Md5UserDaoImpl implements Md5UserDao {
    private static final ConnectionPoolManager connectionPoolManager=new ConnectionPoolManagerImpl();
    NoSQL<Md5User> md5UserNoSQL = new NoSQLImpl<>();
    private static final ExecutionDQL<Md5User> executionDQL= new ExecutionDQLImpl<>();

    @Override
    public int addUser(Md5User md5User) {
        return md5UserNoSQL.insertOne(connectionPoolManager, md5User);
    }

    @Override
    public Md5User getLoginUser(LoginDto loginDto) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="select * from md5_user where user_name = ? and password = ?";
        Md5User md5User = executionDQL.queryBean(connectionFromPool, sql, Md5User.class, loginDto.getUsername(), loginDto.getPassword());
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return md5User;
    }

    @Override
    public String getUserSalt(String username) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="select * from md5_user where user_name = ? ";
        Map<String, Object> map = executionDQL.queryMapFields(connectionFromPool, sql, username);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return map.get("salt").toString();
    }
}
