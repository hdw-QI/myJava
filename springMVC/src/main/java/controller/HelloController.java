package controller;

import exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    //步骤
    //1、导入文件上传依赖
    //2、springmvc配置文件配置文件上传解析器
    @PostMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        try {
            file.transferTo(new File("D:\\Java_GuoXinAn\\git\\springMVC\\src\\main\\webapp\\uploadImg\\"+originalFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return originalFilename;

    }

    //测试多个文件上传(前端的多个文件的name值要相同)
    @PostMapping("/uploadManyImg")
    @ResponseBody
    public int uploadManyImg(MultipartFile[] file) {
        for (MultipartFile multipartFile : file) {
            String originalFilename = multipartFile.getOriginalFilename();
            System.out.println(originalFilename);
            try {
                multipartFile.transferTo(new File("D:\\Java_GuoXinAn\\git\\springMVC\\src\\main\\webapp\\uploadImg\\"+originalFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return file.length;
    }

    //测试自定义异常处理器
    //步骤：
    //1、自定义异常（可没有）
    //2、自定义异常处理器
    //3、在springmvc配置文件中配置自定义异常处理器
    @GetMapping("/testException")
    public void testException() throws MyException {
        if (true) {
            throw new MyException("自定义异常");
        }
    }




}
