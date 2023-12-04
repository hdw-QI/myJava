package employee.service;

import employee.bean.dto.EmployeeListDto;
import employee.bean.po.Employee;
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

    /**
     * @param pageParams:
     * @return PageResult<Employee>
     * @author 胡代伟
     * @description 分页查询员工
     * @date 2023/11/30 19:16
     */
    public PageResult<Employee> getPageEmployee(PageParams pageParams);

    /**
     * @param employee:
     * @return boolean
     * @author 胡代伟
     * @description 添加员工
     * @date 2023/11/30 19:17
     */
    public boolean addEmployee(Employee employee);

    /**
     * @param id:
     * @return boolean
     * @author 胡代伟
     * @description 根据id删除员工
     * @date 2023/11/30 19:17
     */
    public boolean delEmployee(Integer id);

    /**
     * @param pageParams:
     * @return PageResult<EmployeeListDto>
     * @author 胡代伟
     * @description 多表分页查询员工列表
     * @date 2023/12/4 17:40
     */
    public PageResult<EmployeeListDto> getPageEmployeeInManyTable(PageParams pageParams);
}
