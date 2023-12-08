package listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @projectName: git
 * @package: listener
 * @className: SpringLoadListener
 * @author: 胡代伟
 * @description: 加载spring配置文件
 * @date: 2023/12/8 19:01
 * @version: 1.0
 */
@WebListener
public class SpringLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
        sce.getServletContext().setAttribute("spring",classPathXmlApplicationContext);
    }
}
