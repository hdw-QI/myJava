mybatis框架的学习


一、spring与mybatis整合
1、spring
(1)IOC和AOP是Spring的核心。
(2)IOC
控制反转，它是一个技术思想，核心就是将new对象的操作交由IOC容器去帮助我们完成，包括创建实例化对象并管理它， 我们需要使用哪个对象，去IOC容器获取即可。控制指的是对象创建（实例化、管理）的权力，反转指的是控制权交给了外部环境（IOC容器）。它解决了对象之间的耦合问题。
(3)AOP
面向切面编程，是OOP（面向对象）的延续，OOP解决不了以下问题：
    代码重复问题
    逻辑代码和业务代码混杂在一起，代码臃肿，维护不方便
AOP提出横向抽取机制，将横切逻辑代码和业务逻辑代码分离。
它解决了在不改变原有业务逻辑情况下，增强横切逻辑代码的问题，根本上解耦合，避免横切逻辑代码重复。
spring与mybatis整合思路
(1)将MyBatis的DataSource交给Spring IoC容器创建并管理，使用第三方数据库连接池(Druid，C3P0等)代替MyBatis内置的数据库连接池

(2)将MyBatis的SqlSessionFactory交给Spring IoC容器创建并管理，使用spring-mybatis整合jar包中提供的SqlSessionFactoryBean类代替项目中的MyBatisUtil工具类

(3)将MyBatis的接口代理方式生成的实现类，交给Spring IoC容器创建并管理

(4)数据库的连接以及数据库连接池事务管理（声明式事务管理基于AOP实现）都交给spring容器来完成。
