package register.dao.impl;

import register.bean.ServletUser;
import register.dao.ServletUserDao;
import utils.db.annotation.ConfigFilePath;
import utils.db.execution.ExecutionDML;
import utils.db.execution.ExecutionDQL;
import utils.db.execution.impl.ExecutionDMLImpl;
import utils.db.execution.impl.ExecutionDQLImpl;
import utils.db.pool.ConnectionPoolManager;
import utils.db.pool.impl.ConnectionPoolManagerImpl;

import java.sql.Connection;

/**
 * @projectName: git
 * @package: register.dao.impl
 * @className: ServletUserDaoImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:06
 * @version: 1.0
 */
@ConfigFilePath("db.properties")
public class ServletUserDaoImpl implements ServletUserDao {
    private static final ConnectionPoolManager connectionPoolManager=new ConnectionPoolManagerImpl();
    private static final ExecutionDQL<ServletUser> executionDQL= new ExecutionDQLImpl<>();
    private static final ExecutionDML executionDML=new ExecutionDMLImpl();
    @Override
    public int insertServletUser(ServletUser servletUser) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="insert into servlet_user values(?,?,?,?)";
        int insert = executionDML.insert(connectionFromPool, sql, servletUser.getId(), servletUser.getUsername(), servletUser.getPassword(), servletUser.getEmail());
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return insert;
    }
}
