package employee.controller;
/**
 * @projectName: git
 * @package: employee.controller
 * @className: QueryPageEmployeeList
 * @author: 胡代伟
 * @description: 分页查询员工列表
 * @date: 2023/11/29 17:47
 * @version: 1.0
 */

import com.alibaba.fastjson.JSON;
import employee.bean.Employee;
import employee.bean.LayUITableResponse;
import employee.service.EmployeeService;
import employee.service.impl.EmployeeServiceImpl;
import utils.db.model.PageParams;
import utils.db.model.PageResult;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "queryPageEmployeeList", value = "/queryPageEmployeeList")
public class QueryPageEmployeeList extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        PageParams pageParams = new PageParams(Long.parseLong(pageNo), Long.parseLong(pageSize));
        PageResult<Employee> pageEmployee = employeeService.getPageEmployee(pageParams);
        response.setCharacterEncoding("UTF-8");
        // 设置响应的内容类型为"application/json"
        response.setContentType("application/json");
        // 使用FastJson将数据转换为JSON格式

/*        将json字符串转换为对象：（分两种情况）
        类型 对象名= JSON.parseObject(JSON字符串, 类型.class);
        List<类型> list=JSON.parseArray(JSON字符串,类型.class);

        将对象转换为JSON字符串：
        String json=JSON.toJSONString(要转换的对象)*/
        LayUITableResponse<Employee> employeeLayUITableResponse = new LayUITableResponse<>();
        employeeLayUITableResponse.setCode(0);
        employeeLayUITableResponse.setCount(pageEmployee.getTotalCounts());
        employeeLayUITableResponse.setData(pageEmployee.getResult());
        String jsonString = JSON.toJSONString(employeeLayUITableResponse);
        System.out.println(jsonString);
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
