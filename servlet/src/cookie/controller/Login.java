package cookie.controller;

import cookie.service.ServletUserService;
import cookie.service.impl.ServletUserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @projectName: git
 * @package: cookie.controller
 * @className: Login
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:50
 * @version: 1.0
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    ServletUserService servletUserService = new ServletUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean login = servletUserService.login(username, password);
        if (login){
            Cookie cookie = new Cookie("username",username);
            cookie.setPath("/");
            resp.addCookie(cookie);
            HttpSession session = req.getSession();//从请求对象中获得与之对应的session
            session.setAttribute("account", username);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("ucenter.jsp");
            requestDispatcher.forward(req,resp);
        }else {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("登陆失败");
        }
    }
}
