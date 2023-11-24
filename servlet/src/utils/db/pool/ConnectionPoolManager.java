package utils.db.pool;

import java.sql.Connection;

/**
 * @projectName: scond_stage
 * @package: utils.db.pool
 * @className: ConnectionPoolManager
 * @author: 胡代伟
 * @description: 数据库连接池父接口
 * @date: 2023/11/10 16:14
 * @version: 1.0
 */
public interface ConnectionPoolManager {
    /**
     * @param :
     * @return Connection
     * @author 胡代伟
     * @description
     * @date 2023/11/10 16:41
     */
    public Connection getConnectionFromPool();

    /**
     * @param connection:
     * @return void
     * @author 胡代伟
     * @description 自定义断开连接方法,其实实现的是将使用完的connection放回连接池
     * @date 2023/11/10 16:41
     */
    public void backConnectionToPool(Connection connection);

    /**
     * @param :
     * @return void
     * @author 胡代伟
     * @description 自定义关闭所有连接方法,其实实现的是将连接池的所有连接关闭
     * @date 2023/11/10 16:42
     */
    public void closeAllConnectionFromPool();
}
