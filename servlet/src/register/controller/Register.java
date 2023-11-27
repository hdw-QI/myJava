package register.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: register.controller
 * @className: Register
 * @author: 胡代伟
 * @description: 注册案例
 * @date: 2023/11/27 17:35
 * @version: 1.0
 */
@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.setAttribute("username",username);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("registerSuccess.jsp");
        requestDispatcher.forward(req,resp);
    }
}
