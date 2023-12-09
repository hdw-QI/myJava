package domain.vo;

import domain.entity.Company;
import lombok.Data;

import java.util.Date;

/**
 * @projectName: git
 * @package: domain.vo
 * @className: DepartmentCompanyVO
 * @author: 胡代伟
 * @description: 部门详细实体类
 * @date: 2023/12/8 19:20
 * @version: 1.0
 */
@Data
public class DepartmentCompanyVO {
    private int id;
    private String name;
    private int companyId;
    private Date buildDate;
    private int number;
    private Company company;
}
