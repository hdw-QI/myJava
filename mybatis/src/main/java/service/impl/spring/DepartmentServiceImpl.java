package service.impl.spring;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        // 使用分页插件
        PageHelper.startPage(1, 5);
        List<Department> all = departmentMapper.getAll();
        PageInfo<Department> departmentPageInfo = new PageInfo<>(all);
        System.out.println(departmentPageInfo);
        return all;
    }

    @Override
    public int addOne(Department department) {
        return departmentMapper.addOne(department);
    }
}
