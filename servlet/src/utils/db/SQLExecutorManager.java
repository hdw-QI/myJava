package utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @projectName: scond_stage
 * @package: utils.db
 * @className: SQLExecutorManager
 * @author: 胡代伟
 * @description: 执行sql对象管理器
 * @date: 2023/11/9 10:17
 * @version: 1.0
 */
public class SQLExecutorManager {
    /**
     * @param connection: 数据库连接对象
     * @param sql: SQL语句
     * @return PreparedStatement
     * @author 胡代伟
     * @description 获取PreparedStatement对象
     * @date 2023/11/9 10:20
     */
    public static PreparedStatement getPreparedStatement(Connection connection,String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
