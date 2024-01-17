package com.study.springboot.service;

import com.study.springboot.domain.entity.Employee;

/**
 * @author 胡代伟
 * @description EmployeeService
 * @date 2024-01-15 18:16
 */
public interface EmployeeService {
    /**
     * 根据id回去员工信息
     * @param id
     * @return
     */
    Employee getById(Integer id);

    void removeById(Integer id);
}
