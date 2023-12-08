package controller;

import domain.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import service.spring.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @projectName: git
 * @package: controller
 * @className: Department
 * @author: 胡代伟
 * @description: Department
 * @date: 2023/12/7 19:59
 * @version: 1.0
 */
@Controller
@WebServlet("/test")
public class DepartmentController extends HttpServlet {
    @Autowired
    private DepartmentService departmentService;

    public void test() {
        List<Department> all = departmentService.getAll();
        System.out.println(all);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        加载文件放在监听器里面
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
        ClassPathXmlApplicationContext classPathXmlApplicationContext = (ClassPathXmlApplicationContext)this.getServletContext().getAttribute("spring");
        DepartmentService departmentService = (DepartmentService)classPathXmlApplicationContext.getBean("departmentService");
        List<Department> all = departmentService.getAll();
        System.out.println(all);
    }




}
