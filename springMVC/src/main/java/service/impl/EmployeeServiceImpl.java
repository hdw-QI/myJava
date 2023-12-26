package service.impl;

import dao.EmployeeMapper;
import domain.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.EmployeeService;

/**
 * @projectName: git
 * @package: service.impl
 * @className: EmployeeServiceImpl
 * @author: 胡代伟
 * @description: EmployeeService
 * @date: 2023/12/25 17:52
 * @version: 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public Employee getById(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }
}
