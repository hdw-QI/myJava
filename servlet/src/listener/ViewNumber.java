package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * @projectName: git
 * @package: listener
 * @className: ViewNumber
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/12/1 16:51
 * @version: 1.0
 */
@WebListener
public class ViewNumber implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String realPath = servletContext.getRealPath("viewNumber.properties");
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(realPath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {

        }
        String viewNumber = properties.getProperty("viewNumber");
        servletContext.setAttribute("viewNumber",Integer.parseInt(viewNumber));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        Object viewNumber = servletContext.getAttribute("viewNumber");
        System.out.println(viewNumber.toString());
        Properties properties = new Properties();
        properties.put("viewNumber", viewNumber.toString());
        String realPath = servletContext.getRealPath("viewNumber.properties");
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(realPath);
            properties.store(fileOutputStream,"");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
