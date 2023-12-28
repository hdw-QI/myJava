package controller;

import domain.dto.EmployeeDto;
import domain.entity.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.EmployeeService;

import java.util.Arrays;
import java.util.List;

/**
 * @projectName: git
 * @package: controller
 * @className: EmployeeController
 * @author: 胡代伟
 * @description: Employee控制器
 * @date: 2023/12/25 17:41
 * @version: 1.0
 */
@Controller
@RequestMapping("/emp")
@CrossOrigin
@Api(tags = "员工管理")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /*springMVC的数据响应*/
    //1、页面跳转
    //1.1、直接返回字符串（要配置视图解析器）
    @GetMapping("/skipByString")
    public String skip1() {
        return "hello";
    }

    //1.2、通过ModelAndView对象返回
    @GetMapping("/skipByMV")
    public ModelAndView skip2() {
        ModelAndView modelAndView = new ModelAndView();
        Employee byId = employeeService.getById(1L);
        modelAndView.addObject("emp", byId);
        modelAndView.setViewName("empSkip2");
        return modelAndView;
    }

    //2、回显数据
    //2.1回显字符串
    //注意：
    //1、produces = {"text/html;charset=UTF-8;", "application/json;"}解决响应中的乱码
    @GetMapping(value = "/getString", produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    public String getString() {
        return "火火恍恍惚惚后";
    }

    //2.2返回对象或集合
    //注意:
    //1、要引入转json依赖。springmvc默认使用Json序列化工具jackson转换为json
    //2、springmvc可以配置json序列化工具
    @GetMapping("/getObject")
    @ResponseBody
    public Employee getEmployee() {
        return employeeService.getById(1L);
    }

    //3、接收请求数据(当获取的数据的类型是json时，对应的方法参数要加requestBody注解)
    //3.1、获取基本类型(方法参数名称与请求参数的name值相同)
    @PostMapping("/getBase")
    public void getBase(String string) {
        System.out.println(string);
    }

    //3.2、获取pojo(不是json格式)
    @PostMapping("/getPojo")
    public EmployeeDto getPojo(EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        return employeeDto;
    }

    //3.3、获取pojo(是json格式)
    @PostMapping("/getJsonPojo")
    public EmployeeDto getJsonPojo(@RequestBody EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        return employeeDto;
    }

    //3.4、获取数据类型参数
    @GetMapping("/getArray")
    public void getArray(int[] ins) {
        System.out.println(Arrays.toString(ins));
    }

    //3.5、获取集合数据类型(如果请求不为json则要放在pojo（要有构造函数和getter、setter）中才可以)
    @PostMapping("/getList")
    public void getList(@RequestBody List<String> list) {
        System.out.println(list);
    }

    //3.6、restful风格（url中的参数用pathVariable获取）
    @ApiOperation(value = "根据id获取员工")
    @GetMapping("/getEmp/{id}")
    @ResponseBody
    public Employee getEmpById(@ApiParam(name = "id",value = "员工id",required = true,type = "long") @PathVariable Long id) {
        return employeeService.getById(id);
    }

    //3.7、获取请求头（RequestHeader注解）
    @GetMapping("/getHead")
    @ResponseBody
    public String getHead(@RequestHeader("User-Agent") String string) {
        return string;
    }


}
