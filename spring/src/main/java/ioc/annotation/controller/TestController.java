package ioc.annotation.controller;

import ioc.annotation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @projectName: git
 * @package: ioc.annotation.controller
 * @className: TestController
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/21 19:11
 * @version: 1.0
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @Override
    public String toString() {
        return "TestController{" +
                "testService=" + testService +
                '}';
    }
}
