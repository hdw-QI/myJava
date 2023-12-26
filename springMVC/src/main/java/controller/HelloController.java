package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @projectName: git
 * @package: controller
 * @className: HelloController
 * @author: 胡代伟
 * @description: HelloController
 * @date: 2023/12/25 14:15
 * @version: 1.0
 */
@Controller
public class HelloController {
    //测试整合
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    //测试自定义类型转换器(当数据类型为json时，用fastjson来转日期（请求、响应皆可），不用自定义类型转换器)
    //步骤
    //1、定义转换器类实现Converter接口
    //2、在springmvc的配置文件中声明转换器
    //3、在mvc:annotation-driven标签中用conversion-service属性指定
    @PostMapping("/testConverter")
    @ResponseBody
    public Date testConverter(Date date) {
        System.out.println(date);
        return date;
    }


    //测试一个文件上传

    //测试多个文件上传


}
