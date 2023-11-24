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
①、@Table：用于实体类的类上，用于标注实体类在数据库中的表名。未使用注解，则默认为表名为驼峰转换后的名称
②、@Id：用于实体类的字段上，用于标注在数据库中主键的字段名。未使用注解，则默认为主键字段名为驼峰转换后的名称
③、@Column：用于标注实体类除id之外的字段，用于标注在数据库中相应的字段名，则默认为字段名为驼峰转换后的名称
封装的添加数据、修改数据、删除数据方法在ExecutionDML
封装的查询方法在ExecutionDQL

