package dao.mapper;

import domain.entity.Employee;

import java.util.List;

/**
 * @projectName: git
 * @package: dao.mapper
 * @className: EmployeeMapper
 * @author: 胡代伟
 * @description: EmployeeMapper
 * @date: 2023/12/7 10:57
 * @version: 1.0
 */
public interface EmployeeMapper {
    public List<Employee> getAll();

    public int deleteById(Integer id);
}
