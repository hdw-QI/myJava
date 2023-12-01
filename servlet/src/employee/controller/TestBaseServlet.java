package employee.controller;

import imitationSpringMVC.BaseServlet;
import imitationSpringMVC.annotation.UrlMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: employee.controller
 * @className: TestBaseServlet
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/1 10:21
 * @version: 1.0
 */
@WebServlet("/test/*")
public class TestBaseServlet extends BaseServlet {
    @UrlMapping("testDelete")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletResponse.getWriter().write("删除");
    }
}
