package employee;

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
 * @description: 查询员工列表
 * @date: 2023/11/24 15:16
 * @version: 1.0
 */
@WebServlet("/queryEmpById")
public class QueryEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doGet(req, resp);
    }
}
