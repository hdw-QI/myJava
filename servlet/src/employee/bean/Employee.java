package employee.bean;

import java.util.Date;

/**
 * @projectName: git
 * @package: employee.bean
 * @className: Employee
 * @author: 胡代伟
 * @description: 员工实体类
 * @date: 2023/11/24 18:33
 * @version: 1.0
 */
public class Employee {
    private int id;
    private String name;
    private String location;
    private String isMale;
    private Date joinDate;
    private int salary;
    private int deptId;
    private String photo;



    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setIsMale(String ismale) {
        this.isMale = ismale;
    }
    public String getIsMale() {
        return isMale;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public Date getJoinDate() {
        return joinDate;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public int getDeptId() {
        return deptId;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", isMale='" + isMale + '\'' +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                ", deptId=" + deptId +
                ", photo='" + photo + '\'' +
                '}';
    }
}
