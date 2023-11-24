package utils.db.execution;

import java.sql.Connection;

/**
 * @projectName: scond_stage
 * @package: utils.db.execution
 * @className: ExecutionDML
 * @author: 胡代伟
 * @description: 数据操作语言操作父接口。添加数据、修改数据、删除数据
 * @date: 2023/11/9 9:28
 * @version: 1.0
 */
public interface ExecutionDML {
    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @param parameters: 占位符参数
     * @return int 影响行数
     * @author 胡代伟
     * @description 插入数据
     * @date 2023/11/9 9:39
     */
    public int insert(Connection connection,String sql, Object ...parameters);

    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @param parameters: 占位符参数
     * @return int 影响行数
     * @author 胡代伟
     * @description 添加数据
     * @date 2023/11/9 9:52
     */
    public int update(Connection connection,String sql,Object ...parameters);

    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @author 胡代伟
     * @description 删除数据
     * @date 2023/11/9 9:53
     */
    public int delete(Connection connection,String sql);

    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @param parameters: 占位符参数
     * @return int 影响行数
     * @author 胡代伟
     * @description 删除数据
     * @date 2023/11/9 9:53
     */
    public int delete(Connection connection,String sql,Object ...parameters);


}
