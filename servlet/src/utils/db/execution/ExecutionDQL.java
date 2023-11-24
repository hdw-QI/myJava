package utils.db.execution;

import utils.db.model.PageParams;
import utils.db.model.PageResult;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @projectName: scond_stage
 * @package: utils.db.execution
 * @className: ExecutionDQL
 * @author: 胡代伟
 * @description: 数据查询语言操作父接口
 * @date: 2023/11/9 9:28
 * @version: 1.0
 */
public interface ExecutionDQL<T> {
    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @param beanClass: 要获取的对象的字节码对象
     * @param parameters: 占位符参数
     * @return T ：要获取的对象
     * @author 胡代伟
     * @description 将一条记录封装为一个T类型的对象
     * @date 2023/11/9 11:08
     */
    public T queryBean(Connection connection,String sql,Class<T> beanClass,Object ...parameters);

    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @param beanClass: 要获取的对象的字节码对象
     * @param parameters: 占位符参数,可以为空
     * @return T ：要获取的对象
     * @author 胡代伟
     * @description 将多头条记录封装为一个装载T类型对象的List集合
     * @date 2023/11/9 11:11
     */
    public List<T> queryListBean(Connection connection, String sql, Class<T> beanClass, Object ...parameters);

    /**
     * @param connection:
     * @param sql:
     * @param beanClass:
     * @return List<T>
     * @author 胡代伟
     * @description 将多头条记录封装为一个装载T类型对象的List集合
     * @date 2023/11/9 15:23
     */
    public List<T> queryListBean(Connection connection, String sql, Class<T> beanClass);

    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @param parameters: 占位符参数
     * @return Map<String,Object> 键为字段名，值为对应的数据库在中的值
     * @author 胡代伟
     * @description 将一条记录封装为一个map集合
     * @date 2023/11/9 12:28
     */
    public Map<String,Object> queryMapFields(Connection connection, String sql,Object ...parameters);

    /**
     * @param connection: 数据库连接对象
     * @param sql: sql语句
     * @param parameters: 占位符参数,可以为空
     * @return List<Map<String,Object>> 键为字段名，值为对应的数据库在中的值
     * @author 胡代伟
     * @description 将多头条记录封装为一个装载map集合的List集合
     * @date 2023/11/9 12:41
     */
    public List<Map<String,Object>> queryListMapFields(Connection connection, String sql,Object ...parameters);

    /**
     * @param connection:
     * @param sql:
     * @return List<Map<String,Object>>
     * @author 胡代伟
     * @description 将多头条记录封装为一个装载map集合的List集合
     * @date 2023/11/9 15:24
     */
    public List<Map<String,Object>> queryListMapFields(Connection connection, String sql);

    /**
     * @param connection:
     * @param beanClass:
     * @param sql:
     * @param pageParams:
     * @param parameters:
     * @return PageResult<T>
     * @author 胡代伟
     * @description 增加分页功能，分页的参数单独传。 sql语句中的分页语句必须写在最后面且为：limit  ?,?
     * @date 2023/11/10 17:44
     */
    public PageResult<T> queryPageBean(Connection connection,Class<T> beanClass, String sql, PageParams pageParams,Object ...parameters);

    /**
     * @param connection:
     * @param sql:
     * @param pageParams:
     * @return PageResult<T>
     * @author 胡代伟
     * @description 增加分页功能，分页的参数单独传。 sql语句中的分页语句必须写在最后面且为：limit  ?,?
     * @date 2023/11/10 17:42
     */
    public PageResult<T> queryPageBean(Connection connection,Class<T> beanClass, String sql, PageParams pageParams);

    /**
     * @param connection:
     * @param sql:
     * @param pageParams:
     * @param parameters:
     * @return PageResult<Map<String,Object>>
     * @author 胡代伟
     * @description 增加分页功能，分页的参数单独传。 sql语句中的分页语句必须写在最后面且为：limit  ?,?
     * @date 2023/11/10 17:47
     */
    public PageResult<Map<String,Object>> queryPageMapFields(Connection connection, String sql, PageParams pageParams,Object ...parameters);

    /**
     * @param connection:
     * @param sql:
     * @param pageParams:
     * @return PageResult<Map<String,Object>>
     * @author 胡代伟
     * @description 增加分页功能，分页的参数单独传。 sql语句中的分页语句必须写在最后面且为：limit  ?,?
     * @date 2023/11/10 17:47
     */
    public PageResult<Map<String,Object>> queryPageMapFields(Connection connection, String sql, PageParams pageParams);

}
