package dao.spring.mapper;

import domain.entity.Department;

import java.util.List;

/**
 * @projectName: git
 * @package: dao.spring.mapper
 * @className: Department
 * @author: 胡代伟
 * @description: Department
 * @date: 2023/12/7 13:24
 * @version: 1.0
 */
public interface DepartmentMapper {
    public List<Department> getAll();

    public int addOne(Department department);
}
