package aop.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @projectName: git
 * @package: aop.xml
 * @className: SpringAOPTest
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 19:46
 * @version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:aop/annotation/springConfig.xml")
public class SpringAOPTest {
    @Autowired
    MyTarget myTarget;

    @Test
    public void test1() {
        myTarget.cutPoint();
    }
}
