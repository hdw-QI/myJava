package employee.controller;

import employee.bean.Employee;
import employee.service.EmployeeService;
import employee.service.impl.EmployeeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @projectName: git
 * @package: employee.controller
 * @className: OperationEmployee
 * @author: 胡代伟
 * @description: 员工的增删改操作
 * @date: 2023/11/30 17:29
 * @version: 1.0
 */
@WebServlet("/uf/operationEmployee")
public class OperationEmployee extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    public void addition(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        String sex = request.getParameter("sex");
        String join = request.getParameter("join");
        String salary = request.getParameter("salary");
        String dept = request.getParameter("dept");
        String photo = request.getParameter("photo");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date joinDate;
        try {
            joinDate = simpleDateFormat.parse(join);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Employee employee = new Employee();
        employee.setName(name);
        employee.setLocation(location);
        employee.setIsMale(sex);
        employee.setSalary(Integer.parseInt(salary));
        employee.setDeptId(Integer.parseInt(dept));
        employee.setPhoto(photo);
        employee.setJoinDate(joinDate);
        boolean b = employeeService.addEmployee(employee);

        try {
            if (b) {
//                response.getWriter().write("添加成功");

                response.sendRedirect("../../employeeList.jsp");
            }else {
                response.getWriter().write("添加失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void delete(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String id = request.getParameter("id");
        boolean b = employeeService.delEmployee(Integer.parseInt(id));
        try {
            if (b) {
                response.sendRedirect("../../employeeList.jsp");
            }else {
                response.getWriter().write("添加失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write("哈哈");
    }
}
