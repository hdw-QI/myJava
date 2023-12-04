package employee.service.impl;

import employee.bean.dto.EmployeeListDto;
import employee.bean.po.Employee;
import employee.dao.EmployeeDao;
import employee.dao.impl.EmployeeDaoImpl;
import employee.service.EmployeeService;
import utils.db.model.PageParams;
import utils.db.model.PageResult;

/**
 * @projectName: git
 * @package: employee.service.impl
 * @className: EmployeeServiceImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/29 17:40
 * @version: 1.0
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDao.getEmpById(id);
    }

    @Override
    public PageResult<Employee> getPageEmployee(PageParams pageParams) {
        return employeeDao.getEmpListByPage(pageParams);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        int i = employeeDao.addEmp(employee);
        return i>0;
    }

    @Override
    public boolean delEmployee(Integer id) {
        int i = employeeDao.delEmpById(id);
        return i>0;
    }

    @Override
    public PageResult<EmployeeListDto> getPageEmployeeInManyTable(PageParams pageParams) {
        return employeeDao.getEmpListByPageInManyTable(pageParams);
    }
}
