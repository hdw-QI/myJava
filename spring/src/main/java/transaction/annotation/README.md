Spring的声明式事务的注解方式

基于注解方式来进行声明式事务的操作会更加简单，在实际开发中我们也会用的比较多,我们基于以上案例继续完成

1）配置事务管理器(同上1)
```xml
<!-- 1.配置事务的管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <!-- 指定要对哪个数据库进行事务操作 -->
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

2）注释掉事务的其他配置，开启事务注解

```xml
<!-- 2.开启事务的注解 -->
<tx:transaction-driven transaction-manager="transactionManager"></tx:transaction-driven>
```


完成之后的配置文件问：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:context="http://www.springframework.org/schema/context"
xmlns:aop="http://www.springframework.org/schema/aop"
xmlns:tx="http://www.springframework.org/schema/tx"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/aop
http://www.springframework.org/schema/aop/spring-aop-4.2.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-4.2.xsd
http://www.springframework.org/schema/tx
http://www.springframework.org/schema/tx/spring-tx.xsd">

   	<!--指定注解扫描包路径-->
	<context:component-scan base-package="com.oak"/>

   	<!-- 导入资源文件 -->
  	<context:property-placeholder location="classpath:dbutil.properties"/>
  	<!-- 配置dbcp连接池参数 -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
destroy-method="close">
<property name="driverClassName" value="${driver}" />
<property name="url"
value="${url}" />
<property name="username" value="${user}" />
<property name="password" value="${password}" />
<!-- 连接池启动时的初始值 -->
<property name="initialSize" value="${initsize}" />
<!-- 连接池的最大值 -->
<property name="maxActive" value="${maxsize}" />
<!-- 最大空闲值。当经过一个高峰时间后，连接池可以慢慢将已经用不到的连接慢慢释放一部分，一直减少到maxIdle为止 -->
<property name="maxIdle" value="${maxIdle}" />
<!-- 最小空闲值。当空闲的连接数少于阀值时，连接池就会预申请去一些连接，以免洪峰来时来不及申请 -->
<property name="minIdle" value="${minIdle}" />
</bean>
<!-- 配置 Spring 的 jdbcTemplate -->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
<property name="dataSource" ref="dataSource"/>    
</bean>

	<!-- 1.配置事务的管理器 -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	    <!-- 指定要对哪个数据库进行事务操作 -->
	    <property name="dataSource" ref="dataSource"></property>
	</bean>
	
	<!-- 2.开启事务的注解 -->
	<tx:transaction-driven transaction-manager="transactionManager">	</tx:transaction-driven>
</beans>
```

3）在具体使用事务的方法所在的类上面添加注解：@Transactional
在AccountServiceImpl上加上该注解

```java
@Service//业务层注解
@Transactional//事务控制注解
public class AccountServiceImpl implements AccountService{
    @Autowired//依赖注入
    AccountDao accountDao;
    @Override
    public void accountBalance(int lessenId, int addId, double balance) {
    //某个账号减少金额
        accountDao.lessenBalance(lessenId, balance);
        //模拟出现异常
        int a=5/0;
        //走个账号增加金额
        accountDao.addBalance(addId, balance);
        }
}
```