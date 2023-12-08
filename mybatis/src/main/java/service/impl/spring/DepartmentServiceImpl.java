package service.impl.spring;

import dao.spring.mapper.DepartmentMapper;
import domain.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.spring.DepartmentService;

import java.util.List;

/**
 * @projectName: git
 * @package: service.impl.spring
 * @className: DepartmentServiceImpl
 * @author: 胡代伟
 * @description: DepartmentServiceImpl
 * @date: 2023/12/7 15:15
 * @version: 1.0
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getAll() {
        return departmentMapper.getAll();
    }

    @Override
    public int addOne(Department department) {
        return departmentMapper.addOne(department);
    }
}
