package utils.db.execution;

import utils.db.model.PageParams;
import utils.db.model.PageResult;
import utils.db.pool.ConnectionPoolManager;

import java.util.List;
import java.util.Map;

/**
 * @projectName: myUtils
 * @package: utils.db.execution
 * @className: NoSQLDML
 * @author: 胡代伟
 * @description: 封装一些无需sql即可执行增删改查
 * @date: 2023/12/1 19:28
 * @version: 1.0
 */
public interface NoSQL<T> {
    /**
     * @param connectionPoolManager: 数据库连接（从连接池中获取）
     * @param bean: 实体类对象
     * @return int
     * @author 胡代伟
     * @description 添加一行数据到数据库
     * @date 2023/12/2 10:37
     */
    public int insertOne(ConnectionPoolManager connectionPoolManager, T bean);

    /**
     * @param connectionPoolManager: 数据库连接（从连接池中获取）
     * @param aClass:                实体类的类模板
     * @param idList:                id集合
     * @return int
     * @author 胡代伟
     * @description 根据多个id删除多行数据
     * @date 2023/12/2 10:55
     */
    public int deleteWhereIdIn(ConnectionPoolManager connectionPoolManager, Class<T> aClass, List<Object> idList);

    /**
     * @param connectionPoolManager: 数据库连接（从连接池中获取）
     * @param bean:                  实体类对象
     * @return int
     * @author 胡代伟
     * @description 根据实体类对象修改数据
     * @date 2023/12/2 12:46
     */
    public int updateWhereEqId(ConnectionPoolManager connectionPoolManager, T bean);

    /**
     * @param connectionPoolManager: 数据库连接（从连接池中获取）
     * @param aClass:                实体类的类模板
     * @param idList:                id集合
     * @return List<T>返回实体对象集合
     * @author 胡代伟
     * @description 根据多个id查询多行数据
     * @date 2023/12/2 13:03
     */
    public List<T> queryBeanWhereInId(ConnectionPoolManager connectionPoolManager, Class<T> aClass, List<Object> idList);

    /**
     * @param connectionPoolManager: 数据库连接（从连接池中获取）
     * @param aClass:                实体类的类模板
     * @param idList:                id集合
     * @return List<Map<String, Object>> 返回map集合的list集合
     * @author 胡代伟
     * @description 根据多个id查询多行数据
     * @date 2023/12/2 13:17
     */
    public List<Map<String, Object>> queryMapWhereInId(ConnectionPoolManager connectionPoolManager, Class<T> aClass, List<Object> idList);

    /**
     * @param connectionPoolManager: 数据库连接（从连接池中获取）
     * @param aClass:                实体类的类模板
     * @return List<T>
     * @author 胡代伟
     * @description 查询所有
     * @date 2023/12/2 13:39
     */
    public List<T> queryAll(ConnectionPoolManager connectionPoolManager, Class<T> aClass);

    /**
     * @param connectionPoolManager: 数据库连接（从连接池中获取）
     * @param aClass:                实体类的类模板
     * @param pageParams:            分页参数
     * @return PageResult<T>
     * @author 胡代伟
     * @description 分页查询所有
     * @date 2023/12/2 14:10
     */
    public PageResult<T> queryPageAll(ConnectionPoolManager connectionPoolManager, Class<T> aClass, PageParams pageParams);
}
