package domain.vo;

import domain.entity.Department;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @projectName: git
 * @package: domain.vo
 * @className: CompanyVO
 * @author: 胡代伟
 * @description: 公司
 * @date: 2023/12/8 20:05
 * @version: 1.0
 */
@Data
public class CompanyVO {
    private int id;
    private String name;
    private String location;
    private Date buildDate;
    private List<Department> departmentList;
}
