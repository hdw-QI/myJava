package service;

import domain.entity.Employee;

import java.util.List;

/**
 * @projectName: git
 * @package: service
 * @className: EmployeeService
 * @author: 胡代伟
 * @description: EmployeeService
 * @date: 2023/12/7 11:15
 * @version: 1.0
 */
public interface EmployeeService {
    /**
     * @param :
     * @return List<Employee>
     * @author 胡代伟
     * @description 获取所有
     * @date 2023/12/7 11:16
     */
    public List<Employee> getAll();

    /**
     * @param id:
     * @return int
     * @author 胡代伟
     * @description 根据id删除
     * @date 2023/12/7 11:17
     */
    public int deleteById(int id);
}
