package com.study.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springboot.service.DepartmentService;
import com.study.springboot.domain.entity.Department;
import com.study.springboot.dao.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author hdw
* @description 针对表【department】的数据库操作Service实现
* @createDate 2024-01-17 12:32:30
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService {

}




