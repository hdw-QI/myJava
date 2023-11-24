package utils.db.execution.impl;

import utils.db.ConnectionManager;
import utils.db.SQLExecutorManager;
import utils.db.execution.ExecutionDML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @projectName: scond_stage
 * @package: utils.db.execution.impl
 * @className: ExecutionDMLImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/9 9:55
 * @version: 1.0
 */
public class ExecutionDMLImpl implements ExecutionDML {

    @Override
    public int insert(Connection connection, String sql, Object... parameters) {
        return execute(connection, sql, parameters);
    }

    @Override
    public int update(Connection connection, String sql, Object... parameters) {
        return execute(connection, sql, parameters);
    }

    @Override
    public int delete(Connection connection, String sql) {
        return execute(connection, sql,  null);
    }

    @Override
    public int delete(Connection connection, String sql, Object... parameters) {
        return execute(connection, sql, parameters);
    }

    public int execute(Connection connection, String sql, Object... parameters){
        try {
            // 开启事务控制
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement preparedStatement =null;
        if (connection!=null & sql!=null){
            preparedStatement = SQLExecutorManager.getPreparedStatement(connection, sql);
        }
        if (parameters!=null & preparedStatement!=null){
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
            if (preparedStatement != null) {
                int i = preparedStatement.executeUpdate();
                if (i>0){
                    connection.commit();
                }else {
                    connection.rollback();
                }
                return i;
            }else {
                throw new RuntimeException("参数错误");
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            ConnectionManager.closeConnection(null,preparedStatement,null);
        }
    }
}
