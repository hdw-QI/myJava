package controller;

import domain.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentController{
    @Autowired
    private DepartmentService departmentService;

    public void test() {
        List<Department> all = departmentService.getAll();
        System.out.println(all);
    }
}
