package employee.dao.impl;

import employee.bean.Employee;
import employee.dao.EmployeeDao;
import utils.db.annotation.ConfigFilePath;
import utils.db.execution.ExecutionDML;
import utils.db.execution.ExecutionDQL;
import utils.db.execution.impl.ExecutionDMLImpl;
import utils.db.execution.impl.ExecutionDQLImpl;
import utils.db.model.PageParams;
import utils.db.model.PageResult;
import utils.db.pool.ConnectionPoolManager;
import utils.db.pool.impl.ConnectionPoolManagerImpl;

import java.sql.Connection;

/**
 * @projectName: git
 * @package: employee.dao
 * @className: EmployeeDaoImpl
 * @author: 胡代伟
 * @description: EmployeeDao实现类
 * @date: 2023/11/24 18:30
 * @version: 1.0
 */
@ConfigFilePath("db.properties")
public class EmployeeDaoImpl implements EmployeeDao {
    private static final ConnectionPoolManager connectionPoolManager=new ConnectionPoolManagerImpl();
    private static final ExecutionDQL<Employee> executionDQL= new ExecutionDQLImpl<>();
    private static final ExecutionDML executionDML=new ExecutionDMLImpl();
    @Override
    public Employee getEmpById(Integer id) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="select * from employee where id = ?";
        Employee employee = executionDQL.queryBean(connectionFromPool, sql, Employee.class, id);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return employee;
    }

    @Override
    public PageResult<Employee> getEmpListByPage(PageParams pageParams) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="select * from employee limit ?,?";
        PageResult<Employee> employeePageResult = executionDQL.queryPageBean(connectionFromPool, Employee.class, sql, pageParams);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return employeePageResult;
    }

    @Override
    public int addEmp(Employee employee) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="insert into employee values(?,?,?,?,?,?,?,?)";
        int insert = executionDML.insert(connectionFromPool, sql, employee.getId(), employee.getName(), employee.getLocation(), employee.getIsMale(), employee.getJoinDate(), employee.getSalary(), employee.getDeptId(), employee.getPhoto());
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return insert;
    }

    @Override
    public int delEmpById(Integer id) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="delete from employee where id=?";
        int delete = executionDML.delete(connectionFromPool, sql, id);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return delete;
    }

}
