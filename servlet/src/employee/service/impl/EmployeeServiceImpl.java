package employee.service.impl;

import employee.bean.Employee;
import employee.dao.EmployeeDao;
import employee.dao.impl.EmployeeDaoImpl;
import employee.service.EmployeeService;

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
}
