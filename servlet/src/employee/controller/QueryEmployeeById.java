package employee.controller;

import employee.bean.po.Employee;
import employee.service.EmployeeService;
import employee.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: PACKAGE_NAME
 * @className: employee.controller.QueryEmployee
 * @author: 胡代伟
 * @description: 查询员工
 * @date: 2023/11/24 15:16
 * @version: 1.0
 */
@WebServlet("/queryEmpById")
public class QueryEmployeeById extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Employee empById = employeeService.getEmployeeById(Integer.parseInt(id));
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(empById.toString());
    }


}
