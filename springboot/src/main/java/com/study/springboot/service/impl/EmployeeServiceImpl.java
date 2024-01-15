package com.study.springboot.service.impl;

import com.study.springboot.dao.EmployeeMapper;
import com.study.springboot.domain.entity.Employee;
import com.study.springboot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 胡代伟
 * @description EmployeeServiceImpl
 * @date 2024-01-15 18:17
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee getById(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id.longValue());
        log.info(employee.toString());
        return employee;
    }
}
