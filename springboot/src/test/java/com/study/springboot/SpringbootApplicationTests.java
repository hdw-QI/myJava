package com.study.springboot;

import com.study.springboot.dao.DepartmentMapper;
import com.study.springboot.domain.entity.Department;
import com.study.springboot.domain.entity.Employee;
import com.study.springboot.domain.vo.EmployeeVo;
import com.study.springboot.service.DepartmentService;
import com.study.springboot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("成功");
    }

    //测试mybatis整合
    @Autowired
    EmployeeService employeeService;

    @Test
    void mybatisTest() {
        Employee employee = employeeService.getById(1);
        System.out.println(employee);
    }

    //测试mybatis-plus整合
    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    void mybatisPlusTest() {
        List<Department> byName = departmentMapper.getByName("销售部");
//        for (Department department : byName) {
//            System.out.println(department);
//        }
        byName.forEach(System.out::println);
    }

    @Autowired
    DepartmentService departmentService;
    //多个id的批量查询
    @Test
    void selectByIds() {
        int[] ints = {1, 3, 5, 6, 7};
        ArrayList<Integer> integers = new ArrayList<>();
        for (int anInt : ints) {
            integers.add(anInt);
        }
        List<Department> batchById = departmentService.getBatchById(integers);
        batchById.forEach(System.out::println);
    }

    //多个id的批量查询删除
    @Test
    void removeByIds() {
        int[] ints = {23,24,25,26};
        ArrayList<Integer> integers = new ArrayList<>();
        for (int anInt : ints) {
            integers.add(anInt);
        }
        boolean b = departmentService.removeBatchById(integers);
        System.out.println(b);
    }

    //批量查询添加
    @Test
    void addBatch() {
        Department department = new Department();
        department.setName("研发部");
        department.setCompanyid(4);
        department.setBuilddate(new Date());
        department.setNumber(77);
        Department department1 = new Department();
        department1.setName("后勤部");
        department1.setCompanyid(5);
        department1.setBuilddate(new Date());
        department1.setNumber(111);
        ArrayList<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department);
        boolean b = departmentService.addBatch(departments);
        System.out.println(b);
    }

    //查询注册时间为 xxx-xxx 之间的用户信息
    @Test
    void getByTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(2023,9,22);
        Date begin = instance.getTime();
        List<Department> byBuildDate = departmentService.getByBuildDate(begin, new Date());
        byBuildDate.forEach(System.out::println);
    }

    //查询姓名中带有"XX" 的用户信息
    @Test
    void getByName() {
        List<Department> departments = departmentService.getByLikeName("发");
        departments.forEach(System.out::println);
    }

    //查询所有积分不为空的用户信息, 且根据积分高低培训
    @Test
    void getByNumber() {
        List<Department> byOrder = departmentService.getByOrder();
        byOrder.forEach(System.out::println);
    }

    //模糊查询,条件可以覆盖name/phone两个字段
    @Test
    void getByLike() {
        List<Department> departments = departmentService.getByLikeNameAndNumber("发", 3);
        departments.forEach(System.out::println);
    }

    //测试redis连接
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate; //为什么是这个泛型？看源码（点注入图标查看）
//    @Test
//    void testStringRedis01() {
//        redisTemplate.opsForValue().set("name","qi");
//        //15秒
//        //redisTemplate.expire("name", 15, TimeUnit.SECONDS); //先设置值后设置过期时间没有原子性
//        redisTemplate.opsForValue().set("age",17,15, TimeUnit.SECONDS); //同时设置具有原子性
//        Object name = redisTemplate.opsForValue().get("name");
//        if (name != null) {
//            log.info("name-->{}",name.toString());
//        }
//
//        //相当于setex命令
//        redisTemplate.opsForValue().setIfAbsent("lock", "1");
//        //以上的存储都采用了jdk序列化
//    }

    //测试自定义序列化的template(spring会创建自定义的template，而boot只带的template不会被创建)
    @Autowired
    private RedisTemplate<String, Object> stringObjectRedisTemplate; //为什么是这个泛型？看自定义的redis配置类（点注入图标查看）
    @Test
    void testStringRedis02() {
        Employee employee = employeeService.getById(1);
        stringObjectRedisTemplate.opsForValue().set("emp1",employee);
        Object emp1 = stringObjectRedisTemplate.opsForValue().get("emp1");
        //{}是后面参数的占位符
        log.info("emp1-->{}",emp1);


        //localDateTime数据类型的序列化和反序列化需要@JsonSerialize与@JsonDeserialize指定序列化类与反序列化类
        EmployeeVo employeeVo = new EmployeeVo();
        BeanUtils.copyProperties(employee,employeeVo);
        employeeVo.setTime(LocalDateTime.now());
        stringObjectRedisTemplate.opsForValue().set("emp2",employeeVo);
        //stringObjectRedisTemplate.delete("emp2");
        Object emp2 = stringObjectRedisTemplate.opsForValue().get("emp2");
        log.info("emp2-->{}",emp2);
    }

    @Test
    void testBoundValueOpsRedis() {
        //boundXXXOps就是一个绑定key的对象，我们可以通过这个对象来进行与key相关的操作。与opsForXXX相比少了一个key的参数
        BoundValueOperations<String, Object> emp2 = stringObjectRedisTemplate.boundValueOps("emp2");
        log.info(Objects.requireNonNull(emp2.get()).toString());
        //给绑定键设置新值并设置过期时间
        emp2.set(1, 15, TimeUnit.SECONDS);
        log.info(Objects.requireNonNull(emp2.get()).toString());
        Boolean emp21 = emp2.getOperations().delete("emp2");
        if (emp21 != null) {
            log.info(emp21.toString());
        }
    }

    @Test
    void testBoundHashOpsRedis() {
        BoundHashOperations<String, Object, Object> name = stringObjectRedisTemplate.boundHashOps("name");
        name.put("qi","hu");
        name.put("qii","hu");
        Object qi = name.get("qi");
        log.info("qi:{}",qi);
        String key = name.getKey();
        log.info("key:{}",key);
        Long delete = name.delete("qi");
        log.info("delete:{}",delete);
        Boolean name1 = stringObjectRedisTemplate.delete("name");
        log.info("deleteKey:{}",name1);
    }

    @Test
    void testBoundListOpsRedis() {
        BoundListOperations<String, Object> name = stringObjectRedisTemplate.boundListOps("name");
        Long qi = name.leftPush("qi");
        Long hu = name.leftPush("hu");
        log.info("qi:{},{}",qi,hu);
        Object leftedPop = name.leftPop();
        log.info("leftedPop:{}",leftedPop);
        List<Object> range = name.range(0, -1);
        log.info("range:{}",range);
        Object index = name.index(1);
        log.info("index:{}",index);
        Boolean name1 = stringObjectRedisTemplate.delete("name");
        log.info("deleteKey:{}",name1);
    }

    @Test
    void testBoundSetOpsRedis() {
        BoundSetOperations<String, Object> age = stringObjectRedisTemplate.boundSetOps("age");
        Long add = age.add(1, 2, 3);
        log.info("add:{}",add);
        //获取全部值
        Set<Object> members = age.members();
        log.info("members:{}",members);
        //随机获取一个值、随机获取count个值
        Object o = age.randomMember();
        log.info("o:{}",o);
        List<Object> objects = age.randomMembers(2);
        log.info("objects:{}",objects);
        //弹出集合中的值
        Object pop = age.pop();
        log.info("pop:{}",pop);
        //批量移除元素，可以是集合、数组、多参数
        Long remove = age.remove(3);
        log.info("remove:{}",remove);
        Boolean name1 = stringObjectRedisTemplate.delete("age");
        log.info("deleteKey:{}",name1);
    }

    @Test
    void testBoundZSetOpsRedis() {
        BoundZSetOperations<String, Object> name = stringObjectRedisTemplate.boundZSetOps("name");
        //绑定键中添加值，同时指定值的分数。
        name.add("qi", 7);
        name.add("hu", 1);
        //获取绑定键的指定区间值。
        Set<Object> range = name.range(0, -1);
        log.info("range:{}",range);
        //按值批量删除绑定键中的元素，可以是集合、数组、多参数
        Long remove = name.remove("hu");
        log.info("remove:{}",remove);
        Boolean name1 = stringObjectRedisTemplate.delete("name");
        log.info("deleteKey:{}",name1);
    }


}
