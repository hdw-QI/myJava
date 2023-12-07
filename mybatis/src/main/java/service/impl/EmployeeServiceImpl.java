package service.impl;

import dao.mapper.EmployeeMapper;
import domain.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import service.EmployeeService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @projectName: git
 * @package: service.impl
 * @className: EmployeeServiceImpl
 * @author: 胡代伟
 * @description: EmployeeServiceImpl
 * @date: 2023/12/7 11:16
 * @version: 1.0
 */
public class EmployeeServiceImpl implements EmployeeService {

    private final SqlSessionFactory sqlSessionFactory;
    {
        InputStream inputStream = null;
        try {
            //mybatis配置文件
            String resource = "mybatisConfig.xml";
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //使用SqlSessionFactoryBuilder创建sessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


    }
    @Override
    public List<Employee> getAll() {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        //调用代理对象方法
        List<Employee> all = employeeMapper.getAll();
        //关闭session
        session.close();
        return all;
    }

    @Override
    public int deleteById(int id) {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        //调用代理对象方法
        int deletedById = employeeMapper.deleteById(id);
        //提交事务
        session.commit();
        //关闭session
        session.close();
        return deletedById;
    }
}
