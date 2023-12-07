package service.spring;

import domain.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: git
 * @package: service.spring
 * @className: DepartmentService
 * @author: 胡代伟
 * @description: DepartmentService
 * @date: 2023/12/7 15:16
 * @version: 1.0
 */

public interface DepartmentService {
    public List<Department> getAll();

    public int addOne(Department department);
}
