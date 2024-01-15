package com.study.springboot.controller;

import com.study.springboot.domain.entity.Employee;
import com.study.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡代伟
 * @description EmployeeController
 * @date 2024-01-15 18:15
 */
@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("getById")
    public Employee getById(Integer id) {
        return employeeService.getById(id);
    }
}
