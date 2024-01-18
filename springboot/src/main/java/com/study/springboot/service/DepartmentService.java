package com.study.springboot.service;

import com.study.springboot.domain.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
* @author hdw
* @description 针对表【department】的数据库操作Service
* @createDate 2024-01-17 12:32:30
*/
public interface DepartmentService extends IService<Department> {

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param deptName
     * @return
     */
    List<Department> getPage(Integer pageNo, Integer pageSize, String deptName);

    /**
     * 根据条件更新
     * @param deptName
     * @param deptNumber
     * @return
     */
    boolean updateByCondition(String deptName, Integer deptNumber);

    /**
     * 根据id批量查询
     * @param ids
     * @return
     */
    List<Department> getBatchById(List<Integer> ids);

    /**
     * 根据id批量删除
     *
     * @param ids
     * @return
     */
    boolean removeBatchById(List<Integer> ids);

    /**
     * 批量添加
     * @param departmentList
     * @return
     */
    boolean addBatch(List<Department> departmentList);

    /**
     * 根据创建时间查询
     * @param begin
     * @param end
     * @return
     */
    List<Department> getByBuildDate(Date begin, Date end);

    /**
     * 模糊查询
     * @param name
     * @return
     */
    List<Department> getByLikeName(String name);

    /**
     * 排序查询
     * @return
     */
    List<Department> getByOrder();

    /**
     * 根据两个字段进行模糊查询
     * @param name
     * @param number
     * @return
     */
    List<Department> getByLikeNameAndNumber(String name, Integer number);
}
