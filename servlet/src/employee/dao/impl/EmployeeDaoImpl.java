package employee.dao.impl;

import employee.bean.dto.EmployeeListDto;
import employee.bean.po.Employee;
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
    private static final ExecutionDQL<EmployeeListDto> employeeListDtoExecutionDQL= new ExecutionDQLImpl<>();
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

    @Override
    public PageResult<EmployeeListDto> getEmpListByPageInManyTable(PageParams pageParams) {
        Connection connectionFromPool = connectionPoolManager.getConnectionFromPool();
        String sql="SELECT e.id,e.NAME 'name' ,e.location ,CASE e.is_male WHEN 0 THEN '女' ELSE '男' END 'is_male',e.join_date ,e.salary ,d.name 'dept_name',c.name 'company_name',e.dept_id,e.photo " +
                "FROM employee e " +
                "LEFT JOIN department d ON e.dept_id=d.id " +
                "LEFT JOIN company c ON d.companyId=c.id " +
                "LIMIT ?,?";
        PageResult<EmployeeListDto> employeePageResult = employeeListDtoExecutionDQL.queryPageBean(connectionFromPool, EmployeeListDto.class, sql, pageParams);
        connectionPoolManager.backConnectionToPool(connectionFromPool);
        return employeePageResult;
    }

}
