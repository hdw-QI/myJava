package controller;

import domain.entity.Employee;
import service.EmployeeService;
import service.impl.EmployeeServiceImpl;

import java.util.List;

/**
 * @projectName: git
 * @package: controller
 * @className: EmployeeController
 * @author: 胡代伟
 * @description: EmployeeController
 * @date: 2023/12/7 11:14
 * @version: 1.0
 */
public class EmployeeController {

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> all = employeeService.getAll();
        System.out.println(all);

        /*int i = employeeService.deleteById(694);
        System.out.println(i);*/
    }
}
