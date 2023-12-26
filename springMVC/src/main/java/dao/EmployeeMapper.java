package dao;

import domain.entity.Employee;

/**
* @author asus
* @description 针对表【employee】的数据库操作Mapper
* @createDate 2023-12-25 17:38:10
* @Entity domain.entity.Employee
*/
public interface EmployeeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

}
