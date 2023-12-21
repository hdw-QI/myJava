使用xml的方式使用ioc

以前创建对象要自己去new
控制反转： 是一种思想
DI:依赖注入   控制对象与对象之间的依赖关系   
实现
将对象的创建交给spring
目的：解耦

IOC容器的工作流程:

目前:

```properties
1，通过xml配置,将类的创建告知了spring容器(IOC容器)
2,通过启动加载spring的核心配置文件，便通过反射创建对象，交给容器管理
3,需要使用时就通过容器去获取对象
```

DI依赖注入:

```properties
控制对象与对象之间的依赖关系

给属性设置值，则是注入的过程
```

IOC容器的两种实现方式

```properties
1,ApplicationContext容器
2,BeanFactory容器
```

ApplicationContext是BeanFactory的子接口

BeanFactory是设计给spring框架本身使用的
BeanFactory默认使用了懒加载



ApplicationCotentext是给开发者用的 ，支持AOP，事务...等扩展功能
ApplicationCotentext默认使用了预加载



## DI(依赖注入)

```properties
控制对象与对象之间的依赖关系

给属性设置值，则是注入的过程
```



方式有哪些?

```properties
1,使用setter注入(常用)
2,使用构造器注入
```



1,setter注入(常用  更灵活)

```properties
属性必须有set方法
```

```xml
   <bean id="person1" class="com.bean.Person">

        <property name="personName" value="admin"></property>
    </bean>
```

2,使用构造器（构造函数）

要有对应的构造方法(constructor-arg的个数与构造方法参数的个数要一样)

```xml
    <bean id="person1" class="com.bean.Person">
        <constructor-arg name="personName" value="admin"></constructor-arg>
<!--        <constructor-arg name="age" value="18"></constructor-arg>-->
    </bean>
```
## 不同类型的注入

```properties
基础类型  value="值"


  <bean id="person1" class="com.bean.Person">
        <property name="personName" value="admin1"></property>
    </bean>
    <bean id="person2" class="com.bean.Person">
        <property name="personName" value="admin2"></property>
    </bean>
    <bean id="person3" class="com.bean.Person">
        <property name="personName" value="admin3"></property>
    </bean>

    <bean id="userService" class="com.service.UserServiceImpl"></bean>

    <bean id="userController1" class="com.controller.UserController">
<!--        对象-->
        <property name="userService" ref="userService">
        </property>

<!--        数组-->
        <property name="strs">
            <array>
                <value>a</value>
                <value>b</value>
                <value>c</value>
            </array>
        </property>

<!--        对象数组-->
        <property name="users">
            <array>
                <bean class="com.bean.User">
                    <property name="userName" value="admin1"></property>
                </bean>
                <bean class="com.bean.User">
                    <property name="userName" value="admin2"></property>
                </bean>
                <bean class="com.bean.User">
                    <property name="userName" value="admin3"></property>
                </bean>
            </array>
        </property>

<!--        List集合-->
        <property name="personList">
            <list>
<!--                <ref bean="person1"></ref>-->
<!--                <ref bean="person2"></ref>-->
<!--                <ref bean="person3"></ref>-->
<!--                <bean class="com"></bean>-->
            </list>

        </property>

<!--        map集合-->

        <property name="personMap">
            <map>
                <entry key="person1" >
                    <bean class="com.bean.Person">
                        <property name="personName" value="admin4"></property>
                    </bean>
                </entry>
                <entry key="person2" value-ref="person2"></entry>
                <entry key="person3" value-ref="person3"></entry>
            </map>
        </property>
    </bean>

```

最为常用的是对象和基础数据类型



```properties
1,基础数据类型  应用场景在第三方类上

2,对象  （应用场景  service mapper）
```



bean的作用域

```
scope="singleton" 单例

scope="prototype"  多例   获取一次创建一个实例(了解)
```

```properties
默认情况下,所有的 Spring Bean 都是单例的，大多数应用场景中都是单例
```

bean的生命周期

```properties
创建:

   默认情况下是xml被加载的时候，自动创建对象
   
 支持懒加载:     lazy-init="true"
 <bean id="personController1" class="com.controller.PersonController"  lazy-init="true"></bean>
 可以进入初始化:init-method="init"
销毁:
	默认情况对象的销毁是随着容器的销毁而销毁
	destroy-method="destory"
```




