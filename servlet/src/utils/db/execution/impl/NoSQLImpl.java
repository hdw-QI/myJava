package utils.db.execution.impl;

import utils.db.execution.ExecutionDML;
import utils.db.execution.ExecutionDQL;
import utils.db.execution.NoSQL;
import utils.db.model.PageParams;
import utils.db.model.PageResult;
import utils.db.pool.ConnectionPoolManager;
import utils.db.pool.impl.ConnectionPoolManagerImpl;
import utils.db.utils.GetSQL;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @projectName: myUtils
 * @package: utils.db.execution.impl
 * @className: NoSQLImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/1 19:37
 * @version: 1.0
 */
public class NoSQLImpl<T> implements NoSQL<T> {
    GetSQL<T> tGetSQL = new GetSQL<>();
    ExecutionDQL<T> tExecutionDQL = new ExecutionDQLImpl<>();
    ExecutionDML executionDML = new ExecutionDMLImpl();
    @Override
    public int insertOne(ConnectionPoolManager connectionPoolManager, T bean) {
        Map<String, Object> map = tGetSQL.insertOne(bean);
        Object sql = map.get("sql");
        Object fieldValue = map.get("fieldValue");
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        int insert = executionDML.insert(connectionFromPool, sql.toString(), (Object[]) fieldValue);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return insert;
    }

    @Override
    public int deleteWhereIdIn(ConnectionPoolManager connectionPoolManager, Class<T> aClass,List<Object> idList) {
        String sql = tGetSQL.deleteWhereIdIn(aClass, idList);
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        int delete = executionDML.delete(connectionFromPool, sql, idList.toArray());
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return delete;
    }

    @Override
    public int updateWhereEqId(ConnectionPoolManager connectionPoolManager, T bean) {
        Map<String, Object> map = tGetSQL.updateWhereEqId(bean);
        Object sql = map.get("sql");
        Object fieldValue = map.get("fieldValue");
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        int update = executionDML.update(connectionFromPool, sql.toString(), (Object[]) fieldValue);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return update;
    }

    @Override
    public List<T> queryBeanWhereInId(ConnectionPoolManager connectionPoolManager, Class<T> aClass, List<Object> idList) {
        String sql = tGetSQL.queryWhereInId(aClass, idList);
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        List<T> ts = tExecutionDQL.queryListBean(connectionFromPool, sql, aClass, idList.toArray());
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return ts;
    }

    @Override
    public List<Map<String, Object>> queryMapWhereInId(ConnectionPoolManager connectionPoolManager, Class<T> aClass, List<Object> idList) {
        String sql = tGetSQL.queryWhereInId(aClass, idList);
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        List<Map<String, Object>> maps = tExecutionDQL.queryListMapFields(connectionFromPool,sql,idList.toArray());
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return maps;
    }

    @Override
    public List<T> queryAll(ConnectionPoolManager connectionPoolManager, Class<T> aClass) {
        String sql = tGetSQL.queryAll(aClass);
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        List<T> ts = tExecutionDQL.queryListBean(connectionFromPool, sql, aClass);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return ts;
    }

    @Override
    public PageResult<T> queryPageAll(ConnectionPoolManager connectionPoolManager, Class<T> aClass, PageParams pageParams) {
        String sql = tGetSQL.queryPageAll(aClass);
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        PageResult<T> tPageResult = tExecutionDQL.queryPageBean(connectionFromPool, aClass, sql, pageParams);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return tPageResult;
    }


}
