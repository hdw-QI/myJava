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
        req.getSession().invalidate();
        resp.sendRedirect("ucenter.jsp");

    }
}
