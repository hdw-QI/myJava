db:

配置文件地址注解：ConfigFilePath
  用法：标注在类、方法上，值为配置文件（只支持properties、xml类型）的地址。
  功能：获取连接参数

连接管理器：ConnectionManager
  功能：
    1、连接数据库
    2、释放连接资源

执行sql对象管理器：
  功能：获取PreparedStatement对象

数据操作语言（添加数据、修改数据、删除数据）操作父接口：ExecutionDML

数据查询语言操作父接口：ExecutionDQL
  注意：要获取的bean必须有无参构造方法，成员属性名称要与数据库中查询出的表的列名一致，且都需要getter、setter方法
  功能：bean、listBean、map、ListMap、分页

ConnectionPoolManager：数据库连接池父接口


注解应用场景：mybatis-plus的实体类注解
模仿mybatis-plus：
这些注解在annotation包下
①、@Table：用于实体类的类上，用于标注实体类在数据库中的表名。必须使用
②、@Id：用于实体类的字段上，用于标注在数据库中主键的字段名。必须使用
③、@Column：用于标注实体类除id之外的字段，用于标注在数据库中相应的字段名，则默认为字段名为驼峰转换后的名称

NoSQL.java封装了不需要sql语句即可存在数据库的方法
使用示列
```java
@Table("employee")
public class Employee {
    @Id("id")
    private int id;
    private String name;
    private String location;
    private String isMale;
    private Date joinDate;
    private int salary;
    private int deptId;
    private String photo;



    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setIsMale(String ismale) {
        this.isMale = ismale;
    }
    public String getIsMale() {
        return isMale;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public Date getJoinDate() {
        return joinDate;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public int getDeptId() {
        return deptId;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", isMale='" + isMale + '\'' +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                ", deptId=" + deptId +
                ", photo='" + photo + '\'' +
                '}';
    }
}
```
```java
@ConfigFilePath("db.properties")
public class TestDb {
    public static void main(String[] args) {
        ConnectionPoolManager connectionPoolManager = new ConnectionPoolManagerImpl();
        Employee employee = new Employee();
        employee.setDeptId(2);
        employee.setIsMale("1");
        employee.setName("haha");
        employee.setJoinDate(new Date());
        employee.setLocation("das");
        employee.setSalary(12333);
        employee.setPhoto("dasdas");
        employee.setId(705);
        NoSQL<Employee> employeeNoSQL = new NoSQLImpl<>();
//        int i = employeeNoSQL.insertOne(connectionPoolManager,employee);
//        System.out.println(i);


        ArrayList<Object> objects = new ArrayList<>();
        objects.add(707);
        objects.add(513);
//        int i = employeeNoSQL.deleteWhereIdIn(connectionPoolManager, Employee.class, objects);
//        System.out.println(i);
//        int i = employeeNoSQL.updateWhereEqId(connectionPoolManager, employee);
//        System.out.println(i);

//        List<Employee> employees = employeeNoSQL.queryBeanWhereInId(connectionPoolManager, Employee.class, objects);
//        for (Employee employee1 : employees) {
//            System.out.println(employee1);
//        }


//        List<Map<String, Object>> maps = employeeNoSQL.queryMapWhereInId(connectionPoolManager, Employee.class, objects);
//        System.out.println(maps);

//        List<Employee> employees = employeeNoSQL.queryAll(connectionPoolManager, Employee.class);
//        for (Employee employee1 : employees) {
//            System.out.println(employee1);
//        }

        PageResult<Employee> employeePageResult = employeeNoSQL.queryPageAll(connectionPoolManager, Employee.class, new PageParams(1, 10));
        List<Employee> result = employeePageResult.getResult();
        for (Employee employee1 : result) {
            System.out.println(employee1);
        }
    }
}
```
