package aop.annotation;

import org.springframework.stereotype.Component;

/**
 * @projectName: git
 * @package: aop.xml
 * @className: My
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 19:30
 * @version: 1.0
 */
@Component
public class MyTarget {
    public void cutPoint() {
//        int a = 1/0;
        System.out.println("增强我");
    }
}
