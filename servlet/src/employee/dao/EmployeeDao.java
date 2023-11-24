package employee.dao;

import employee.bean.Employee;

/**
 * @projectName: git
 * @package: employee.dao
 * @className: EmployeeDao
 * @author: 胡代伟
 * @description: 员工类dao层
 * @date: 2023/11/24 18:30
 * @version: 1.0
 */
public interface EmployeeDao {
    /**
     * @param id:
     * @return Employee
     * @author 胡代伟
     * @description 根据id查找员工
     * @date 2023/11/24 18:38
     */
    public Employee getEmpById(Integer id);
}
