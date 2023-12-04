package employee.bean.dto;

import java.util.Date;

/**
 * @projectName: git
 * @package: employee.bean.dto
 * @className: EmployeeListDto
 * @author: 胡代伟
 * @description: 返回给前端的员工列表实体类
 * @date: 2023/12/4 17:33
 * @version: 1.0
 */
public class EmployeeListDto {
    private int id;
    private String name;
    private String location;
    private String isMale;
    private Date joinDate;
    private int salary;
    private int deptId;
    private String photo;
    private String deptName;
    private String companyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsMale() {
        return isMale;
    }

    public void setIsMale(String isMale) {
        this.isMale = isMale;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "EmployeeListDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", isMale='" + isMale + '\'' +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                ", deptId=" + deptId +
                ", photo='" + photo + '\'' +
                ", deptName='" + deptName + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
