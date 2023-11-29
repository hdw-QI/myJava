package employee.service;

import employee.bean.Employee;
import utils.db.model.PageParams;
import utils.db.model.PageResult;

/**
 * @projectName: git
 * @package: employee.service
 * @className: EmployeeService
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/29 17:40
 * @version: 1.0
 */
public interface EmployeeService {
    /**
     * @param id:
     * @return Employee
     * @author 胡代伟
     * @description 根据id获取员工
     * @date 2023/11/29 18:44
     */
    public Employee getEmployeeById(Integer id);

    public PageResult<Employee> getPageEmployee(PageParams pageParams);
}
