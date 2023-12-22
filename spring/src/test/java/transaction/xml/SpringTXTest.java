package transaction.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import transaction.xml.service.MoneyService;

/**
 * @projectName: git
 * @package: annotation.xml
 * @className: SpringTXTest
 * @author: 胡代伟
 * @description: SpringTXTest
 * @date: 2023/12/22 16:42
 * @version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:transaction/xml/springConfig.xml")
public class SpringTXTest {
    @Autowired
    MoneyService moneyService;

    @Test
    public void test1() {
        moneyService.accountSalary(1,2,100);
    }
}
