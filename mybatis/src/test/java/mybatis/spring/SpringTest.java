package mybatis.spring;

import controller.DepartmentController;
import dao.spring.mapper.CompanyMapper;
import dao.spring.mapper.DepartmentMapper;
import domain.entity.Department;
import domain.vo.CompanyVO;
import domain.vo.DepartmentCompanyVO;
import domain.vo.DepartmentVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.spring.DepartmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private DepartmentService departmentService;
    /*
    @Test
    public void testFindUserList(){
        List<Department> all = departmentMapper.getAll();
        System.out.println(all);
    }*/


    /*@Test
    public void testFindUserList(){
        departmentController.test();
    }*/

    // 测试分页
    @Test
    public void testPageHelper(){
        List<Department> all = departmentService.getAll();
        System.out.println(all);
    }

    /*条件查询。特殊字符处理*/
    @Test
    public void getManyById() {
        List<Department> manyById = departmentMapper.getManyById(3);
        for (Department department : manyById) {
            System.out.println(department);
        }
    }

    /*模糊查询。${}与#{}*/
    @Test
    public void getManyByName() {
        List<Department> manyById = departmentMapper.getManyByName("发");
        for (Department department : manyById) {
            System.out.println(department);
        }
    }

    /*获取插入数据后自动增长的id*/
    @Test
    public void insertOne() {
        Department department1 = new Department();
        department1.setBuildDate(new Date());
        department1.setName("开发部");
        department1.setNumber(123);
        department1.setCompanyId(3);
        int i = departmentMapper.insertOne(department1);
        System.out.println(department1.getId());
    }

    /*动态sql。if*/
    @Test
    public void getByWhereMany() {
        Department department1 = new Department();
//        department1.setNumber(123);
        department1.setCompanyId(3);
        List<Department> byWhereMany = departmentMapper.getByWhereMany(department1);
        for (Department department : byWhereMany) {
            System.out.println(department);
        }
    }

    /*动态sql。where*/
    @Test
    public void getByWhere() {
        Department department1 = new Department();
        department1.setNumber(123);
        department1.setCompanyId(0);
        List<Department> byWhereMany = departmentMapper.getByWhere(department1);
        for (Department department : byWhereMany) {
            System.out.println(department);
        }
    }

    /*动态sql。set*/
    @Test
    public void update() {
        Department department1 = new Department();
        department1.setId(16);
        department1.setNumber(1314);
//        department1.setCompanyId(3);
        int update = departmentMapper.update(department1);
        System.out.println(update);
    }

    /*动态sql。choose、when、otherwise*/
    @Test
    public void getByCondition() {
        Department department1 = new Department();
        department1.setId(16);
        department1.setName("发");
        department1.setCompanyId(16);
        List<Department> byCondition = departmentMapper.getByCondition(department1);
        for (Department department : byCondition) {
            System.out.println(department);
        }
    }


    /*动态sql。foreach 可以遍历数组、list集合、map集合*/
    /*一次删除多个id。 遍历数组*/
    @Test
    public void deleteByArray() {
        int[] ins = {20, 21, 22};
        int i = departmentMapper.deleteByArray(ins);
        System.out.println(i);
    }

    /*通过遍历集合一次添加多个。 */
    @Test
    public void addByList() {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Department department1 = new Department();
            department1.setBuildDate(new Date());
            department1.setName("开发部");
            department1.setNumber(123);
            department1.setCompanyId(3);
            objects.add(department1);
        }
        int i = departmentMapper.addByList(objects);
        System.out.println(i);
    }

    /*resultMap的使用*/
    /*1、使用id、result标签的property、column属性完成自定义映射*/
    @Test
    public void getNameById() {
        DepartmentVO nameById = departmentMapper.getNameById(7);
        System.out.println(nameById);
    }

    /*2、POJO中的属性可能会是一个对象，我们可以使用联合查询，并以级联属性的方式封装对象。使用association标签定义对象的封装规则*/
    @Test
    public void getAllByDeptId() {
        DepartmentCompanyVO departmentCompanyVO = departmentMapper.getAllByDeptId(7);
        System.out.println(departmentCompanyVO);
    }

    /*3、association 分步查询
     * 在分步查询的基础上，可以使用延迟加载来提升查询的效率*/
    @Test
    public void getDCById() {
        DepartmentCompanyVO departmentCompanyVO = departmentMapper.getDCById(7);
        System.out.println(departmentCompanyVO);
    }

    /*4.POJO中的属性可能会是一个集合对象，我们可以使用联合查询，并以级联属性的方式封装对象，使用collection标签定义对象的封装规则。*/
    @Test
    public void getAllById() {
        List<CompanyVO> allById = companyMapper.getAllById(2);
        System.out.println(allById);
    }

    /*5、collection分步查询*/
    @Test
    public void getAllByIdCompanyVo() {
        CompanyVO allById = companyMapper.getAllByIdCompanyVo(2);
        System.out.println(allById);
    }
}
