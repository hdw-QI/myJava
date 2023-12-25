package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: git
 * @package: controller
 * @className: HelloController
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/25 14:15
 * @version: 1.0
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
