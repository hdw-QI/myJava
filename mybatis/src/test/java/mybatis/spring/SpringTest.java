package mybatis.spring;

import controller.DepartmentController;
import dao.spring.mapper.DepartmentMapper;
import domain.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @projectName: git
 * @package: mybatis.spring
 * @className: SpringTest
 * @author: 胡代伟
 * @description: 测试spring与mybatis整合
 * @date: 2023/12/7 16:41
 * @version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springConfig.xml")
public class SpringTest {
    /*@Autowired
    private DepartmentMapper departmentMapper;
    @Test
    public void testFindUserList(){
        List<Department> all = departmentMapper.getAll();
        System.out.println(all);
    }*/

    @Autowired
    private DepartmentController departmentController;
    @Test
    public void testFindUserList(){
        departmentController.test();
    }

}
