package domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: git
 * @package: domain.vo
 * @className: DepartmentVO
 * @author: 胡代伟
 * @description: 部门的vo
 * @date: 2023/12/8 17:55
 * @version: 1.0
 */
@Data
public class DepartmentVO {
    private int id;
    private String name;
    private int companyId;
    private Date buildDate;
    private int number;
    private String companyName;
}
