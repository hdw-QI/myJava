package application;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: application
 * @className: ServletContextServlet
 * @author: 胡代伟
 * @description: servletContext对象共享数据
 * @date: 2023/11/29 11:39
 * @version: 1.0
 */
@WebServlet("/servletContext")
public class ServletContextServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        Object viewNumber = servletContext.getAttribute("viewNumber");
        if (viewNumber == null) {
            servletContext.setAttribute("viewNumber",1);
        }else {
            servletContext.setAttribute("viewNumber",Integer.parseInt(viewNumber.toString())+1);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("servletContext.jsp");
        requestDispatcher.forward(req,resp);
    }
}
