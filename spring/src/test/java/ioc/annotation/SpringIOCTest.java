package ioc.annotation;

import ioc.annotation.controller.TestController;
import ioc.xml.domain.TestProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.spring.SpringUtil;

/**
 * @projectName: git
 * @package: ioc.annotation
 * @className: SpringIOCTest
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 19:16
 * @version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ioc/annotation/springConfig.xml")
public class SpringIOCTest {
    @Test
    public void test1() {
        TestController bean = SpringUtil.getBean(TestController.class);
        System.out.println(bean);
    }
}
