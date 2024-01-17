package com.study.springboot.controller;

import com.study.springboot.domain.entity.Department;
import com.study.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡代伟
 * @description DepartmentController
 * @date 2024-01-17 14:11
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/getById")
    public Department getById(Integer id) {
        return departmentService.getById(id);
    }
}
