package service;

import domain.entity.Employee;

/**
 * @projectName: git
 * @package: service
 * @className: EmployeeService
 * @author: 胡代伟
 * @description: EmployeeService
 * @date: 2023/12/25 17:51
 * @version: 1.0
 */
public interface EmployeeService {
    Employee getById(Long id);
}
