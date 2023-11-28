package cookie.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @projectName: git
 * @package: cookie.controller
 * @className: Logout
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 19:41
 * @version: 1.0
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        用cookie方式的退出
//        Cookie[] cookies = req.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("account")){
//                cookie.setMaxAge(0);
//                resp.getWriter().write("已退出");
//            }
//        }
        req.getSession().invalidate();
        resp.sendRedirect("ucenter.jsp");

    }
}
