package employee.dao;

import employee.bean.dto.EmployeeListDto;
import employee.bean.po.Employee;
import utils.db.model.PageParams;
import utils.db.model.PageResult;

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
    /**
     * @param pageParams:
     * @return PageResult<Employee>
     * @author 胡代伟
     * @description 分页获取员工列表
     * @date 2023/11/29 18:39
     */
    public PageResult<Employee> getEmpListByPage(PageParams pageParams);

    /**
     * @param employee:
     * @return int
     * @author 胡代伟
     * @description 添加员工
     * @date 2023/11/30 19:08
     */
    public int addEmp(Employee employee);

    /**
     * @param id:
     * @return int
     * @author 胡代伟
     * @description 根据id删除员工
     * @date 2023/11/30 19:14
     */
    public int delEmpById(Integer id);

    /**
     * @param pageParams:
     * @return PageResult<EmployeeListDto>
     * @author 胡代伟
     * @description 多表分页查询员工列表
     * @date 2023/12/4 17:37
     */
    public PageResult<EmployeeListDto> getEmpListByPageInManyTable(PageParams pageParams);
}
