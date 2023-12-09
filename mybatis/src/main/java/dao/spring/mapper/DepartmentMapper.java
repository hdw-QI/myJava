package dao.spring.mapper;

import domain.entity.Department;
import domain.vo.DepartmentCompanyVO;
import domain.vo.DepartmentVO;

import java.util.List;

/**
 * @projectName: git
 * @package: dao.spring.mapper
 * @className: Department
 * @author: 胡代伟
 * @description: Department
 * @date: 2023/12/7 13:24
 * @version: 1.0
 */
public interface DepartmentMapper {
    public List<Department> getAll();

    public int addOne(Department department);

    public List<Department> getByCompanyId();

    /*条件查询。特殊字符处理*/
    public List<Department> getManyById(Integer id);

    /*模糊查询。${}与#{}*/
    public List<Department> getManyByName(String name);

    /*获取插入数据后自动增长的id*/
    public int insertOne(Department department);

    /*动态sql。if*/
    public List<Department> getByWhereMany(Department department);

    /*动态sql。where*/
    public List<Department> getByWhere(Department department);

    /*动态sql。set*/
    public int update(Department department);

    /*动态sql。choose、when、otherwise*/
    /*对应sql的含义为：部门id<=5时用模糊查询；部门id>5且<=10时用精准查询；否则根据id查询*/
    public List<Department> getByCondition(Department department);

    /*动态sql。foreach 可以遍历数组、list集合、map集合*/
    /*通过遍历数组一次删除多个。 */
    public int deleteByArray(int[] ins);

    /*通过遍历集合一次添加多个。 */
    public int addByList(List<Object> list);

    /*resultMap的使用*/
    /*1、使用id、result标签的property、column属性完成自定义映射*/
    /*需求：根据id获取部门的完整信息*/
    public DepartmentVO getNameById(int id);

    /*2、POJO中的属性可能会是一个对象，我们可以使用联合查询，并以级联属性的方式封装对象。使用association标签定义对象的封装规则*/
    public DepartmentCompanyVO getAllByDeptId(int id);

    /*3、association 分步查询
     * 在分步查询的基础上，可以使用延迟加载来提升查询的效率*/
    public DepartmentCompanyVO getDCById(int id);

    /*4、POJO中的属性可能会是一个集合对象，我们可以使用联合查询，并以级联属性的方式封装对象，使用collection标签定义对象的封装规则。*/
    /*见CompanyMapper.java*/

    /*5、collection分步查询*/
    /*见CompanyMapper.java*/

}
