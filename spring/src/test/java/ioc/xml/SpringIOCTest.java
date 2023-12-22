package ioc.xml;

import ioc.xml.domain.TestProperty;
import ioc.xml.domain.TestXML;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.spring.SpringUtil;

import javax.annotation.Resource;

/**
 * @projectName: git
 * @package: ioc.xml
 * @className: SpringIOCTest
 * @author: 胡代伟
 * @description: 测试
 * @date: 2023/12/21 18:03
 * @version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ioc/xml/springConfig.xml")
public class SpringIOCTest {
    // 实例化对象
    @Autowired
    @Qualifier("t1")
    TestProperty beanT1;
    @Test
    public void test1() {
//        TestProperty bean = SpringUtil.getBean("t1",TestProperty.class);
        System.out.println(beanT1);
    }

    // 构造方法注入
    @Resource(name = "t2")
    TestProperty beanT2;
    @Test
    public void test2() {
//        TestProperty bean = SpringUtil.getBean("t2",TestProperty.class);
        System.out.println(beanT2);
    }

    // 注入对象属性为基本类型
    @Test
    public void test3() {
        TestXML bean = SpringUtil.getBean("t3",TestXML.class);
        System.out.println(bean);
    }

    // 注入对象属性为引用类型
    @Test
    public void test4() {
        TestXML bean = SpringUtil.getBean("t4",TestXML.class);
        System.out.println(bean);

        TestXML bean1 = SpringUtil.getBean("t5",TestXML.class);
        System.out.println(bean1);
    }

    // 注入对象属性(注入基本、引用类型数组)
    @Test
    public void test5() {
        TestXML bean = SpringUtil.getBean("t6",TestXML.class);
        System.out.println(bean);
        TestXML bean1 = SpringUtil.getBean("t7",TestXML.class);
        System.out.println(bean1);
    }

    // 注入对象属性(注入基本、引用类型list集合)
    @Test
    public void test6() {
        TestXML bean = SpringUtil.getBean("t8",TestXML.class);
        System.out.println(bean);
        TestXML bean1 = SpringUtil.getBean("t9",TestXML.class);
        System.out.println(bean1);
    }

    // 注入对象属性(注入基本、引用类型map集合)
    @Test
    public void test7() {
        TestXML bean = SpringUtil.getBean("t10",TestXML.class);
        System.out.println(bean);
        TestXML bean1 = SpringUtil.getBean("t11",TestXML.class);
        System.out.println(bean1);
    }
}

