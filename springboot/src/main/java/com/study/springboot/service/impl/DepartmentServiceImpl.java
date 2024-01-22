package com.study.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springboot.service.DepartmentService;
import com.study.springboot.domain.entity.Department;
import com.study.springboot.dao.DepartmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
* @author hdw
* @description 针对表【department】的数据库操作Service实现
* @createDate 2024-01-17 12:32:30
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService {

    @Override
    public List<Department> getPage(Integer pageNo, Integer pageSize, String deptName) {
        Page<Department> departmentPage = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Department> departmentLambdaQueryWrapper = new LambdaQueryWrapper<Department>().like(!StringUtils.isEmpty(deptName), Department::getName, deptName);
        page(departmentPage, departmentLambdaQueryWrapper);
        return departmentPage.getRecords();
    }

    @Override
    public boolean updateByCondition(String deptName, Integer deptNumber) {
        LambdaUpdateWrapper<Department> departmentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        departmentLambdaUpdateWrapper
                .set(StringUtils.isEmpty(deptName), Department::getName, deptName)
                .set(deptNumber != null, Department::getNumber, deptNumber)
                .and(e -> e.lt(Department::getNumber, 43).or(l -> l.eq(Department::getCompanyid, 4)));
        return update(departmentLambdaUpdateWrapper);
    }

    @Override
    public List<Department> getBatchById(List<Integer> ids) {
        return baseMapper.selectBatchIds(ids);
    }

    @Override
    public boolean removeBatchById(List<Integer> ids) {
        return removeByIds(ids);
    }

    @Override
    public boolean addBatch(List<Department> departmentList) {
        return saveBatch(departmentList);
    }

    @Override
    public List<Department> getByBuildDate(Date begin, Date end) {
        LambdaQueryWrapper<Department> departmentLambdaQueryWrapper = Wrappers.lambdaQuery(Department.class);
        departmentLambdaQueryWrapper.between(Department::getBuilddate, begin, end);
        return list(departmentLambdaQueryWrapper);
    }

    @Override
    public List<Department> getByLikeName(String name) {
        LambdaQueryWrapper<Department> departmentLambdaQueryWrapper = Wrappers.lambdaQuery(Department.class);
        departmentLambdaQueryWrapper.like(Department::getName, name);
        return list(departmentLambdaQueryWrapper);
    }

    @Override
    public List<Department> getByOrder() {
        LambdaQueryWrapper<Department> departmentLambdaQueryWrapper = Wrappers.lambdaQuery(Department.class);
        departmentLambdaQueryWrapper.isNotNull(Department::getNumber).orderByAsc(Department::getNumber);
        return list(departmentLambdaQueryWrapper);
    }

    @Override
    public List<Department> getByLikeNameAndNumber(String name, Integer number) {
        LambdaQueryWrapper<Department> departmentLambdaQueryWrapper = Wrappers.lambdaQuery(Department.class);
        departmentLambdaQueryWrapper.like(Department::getName, name).or().like(Department::getNumber, number);
        return list(departmentLambdaQueryWrapper);
    }
}




