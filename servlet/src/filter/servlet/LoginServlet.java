package filter.servlet;

import cookie.service.ServletUserService;
import cookie.service.impl.ServletUserServiceImpl;
import imitationSpringMVC.BaseServlet;
import imitationSpringMVC.annotation.UrlMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @projectName: git
 * @package: filter.servlet
 * @className: LoginServlet
 * @author: 胡代伟
 * @description: 系统登录
 * @date: 2023/12/1 11:49
 * @version: 1.0
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {
    ServletUserService servletUserService = new ServletUserServiceImpl();
    @UrlMapping("fl")
    public void filterLogin(HttpServletRequest req, HttpServletResponse resp){
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String vericode=req.getParameter("vericode");
        resp.setContentType("text/html");
        if (vericode == null) {
            try {
                resp.getWriter().write("请先获取验证码");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String generatedCode= (String) req.getSession().getAttribute("verityCode");
        if (vericode.equalsIgnoreCase(generatedCode)){
            req.getSession().removeAttribute("verityCode");
            boolean login = servletUserService.login(name, password);
            if (login){
                HttpSession session = req.getSession();//从请求对象中获得与之对应的session
                session.setAttribute("account", name);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("../ucenter.jsp");
                try {
                    requestDispatcher.forward(req,resp);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                resp.setCharacterEncoding("UTF-8");
                try {
                    resp.getWriter().write("登陆失败");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            try {
                resp.getWriter().write("验证码错误");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
