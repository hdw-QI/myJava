package cookie.dao.impl;

import cookie.dao.ServletUserDao;
import register.bean.ServletUser;
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
 * @package: cookie.dao.impl
 * @className: ServletUserDaoImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:51
 * @version: 1.0
 */
@ConfigFilePath("db.properties")
public class ServletUserDaoImpl implements ServletUserDao {
    private static final ConnectionPoolManager connectionPoolManager=new ConnectionPoolManagerImpl();
    private static final ExecutionDQL<ServletUser> executionDQL= new ExecutionDQLImpl<>();
    private static final ExecutionDML executionDML=new ExecutionDMLImpl();
    @Override
    public ServletUser getServletUser(String username,String password) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="select * from servlet_user where username=? and password=?";
        ServletUser servletUser = executionDQL.queryBean(connectionFromPool, sql, ServletUser.class, username,password);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return servletUser;
    }
}
