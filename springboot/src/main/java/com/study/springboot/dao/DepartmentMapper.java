package com.study.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.springboot.domain.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author hdw
* @description 针对表【department】的数据库操作Mapper
* @createDate 2024-01-17 12:32:30
* @Entity com.study.springboot.domain.entity.Department
*/
public interface DepartmentMapper extends BaseMapper<Department> {
    List<Department> getByName(@Param("name") String name);
}




