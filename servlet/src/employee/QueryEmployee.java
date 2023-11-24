package employee;

import employee.bean.Employee;
import employee.dao.EmployeeDao;
import employee.dao.EmployeeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: PACKAGE_NAME
 * @className: employee.QueryEmployee
 * @author: 胡代伟
 * @description: 查询员工
 * @date: 2023/11/24 15:16
 * @version: 1.0
 */
@WebServlet("/queryEmpById")
public class QueryEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee empById = employeeDao.getEmpById(Integer.parseInt(id));
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(empById.toString());
    }

}
