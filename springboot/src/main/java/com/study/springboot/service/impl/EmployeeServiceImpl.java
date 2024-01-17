package com.study.springboot.service.impl;

import com.study.springboot.dao.EmployeeMapper;
import com.study.springboot.domain.entity.Employee;
import com.study.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 胡代伟
 * @description EmployeeServiceImpl
 * @date 2024-01-15 18:17
 */
@Service
@Slf4j
@RequiredArgsConstructor //根据成员变量自动生成相应的构造方法
public class EmployeeServiceImpl implements EmployeeService {
//    @Autowired //构造方法会自动注入，因为EmployeeServiceImpl是由spring容器生成的
    //这种方式适用于service、controller层、不适用于单元测试
    private  final EmployeeMapper employeeMapper;

    @Override
    public Employee getById(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id.longValue());
        log.info(employee.toString());
        return employee;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class) //rollbackFor必须要有，表示什么异常进行回滚
    public void removeById(Integer id) {
        int i = 1 / 0;
    }
}
