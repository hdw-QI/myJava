使用注解的方式使用ioc

## 基于注解

创建对象

引入context的命名空间:

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
```



1,开启spring的注解扫描

```xml
<!--    开启组件的扫描-->
        <context:component-scan base-package="com.controller"></context:component-scan>

       <context:component-scan base-package="com.service"></context:component-scan>
```

2,添加注解来创建对象

```properties
@Controller  用来创建控制器
@Service     用来创建Service的
@Repository  用来创建持久层的dao
@Component   当不知道你的类属于哪层结构时
```



使用注解注入



```properties
@Autowired  默认按照类型进行装配,如果有多个实例，就按照名字进行装配 

@Qualifier  和Autowired配合使用

@Resource  默认是按照名字进行装配,如果名字不存在，再按照类型装配

@Resouce是jdk自带的，@Autowired是spring提供的注解
```



在实际开发中

```properties
我们自己编写的类，可以用注解

如果你使用的第三方类，只能用xml
```

