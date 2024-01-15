package com.study.springboot.dao;


import com.study.springboot.domain.entity.Employee;

/**
 * @author hdw
 * @description 针对表【employee】的数据库操作Mapper
 * @createDate 2024-01-15 18:12:43
 * @Entity generator.domain.Employee
 */
public interface EmployeeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

}





