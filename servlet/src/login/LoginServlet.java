package login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: login
 * @className: LoginServlet
 * @author: 胡代伟
 * @description: 结合验证码进行登录
 * @date: 2023/11/29 14:47
 * @version: 1.0
 */
@WebServlet("/loginCheckcode")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String vericode=req.getParameter("vericode");
        if (vericode == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("请先获取验证码");
            return;
        }
        String generatedCode= (String) req.getSession().getAttribute("verityCode");
        if (name.equals("qq")&&password.equals("123")&& vericode.equalsIgnoreCase(generatedCode)){
//            验证码只能用一次
            req.getSession().removeAttribute("verityCode");
            resp.getWriter().write("登录成功");
        }else {resp.getWriter().write("登录失败");}
    }
}
