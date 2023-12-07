package domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: git
 * @package: domain.entity
 * @className: Department
 * @author: 胡代伟
 * @description: Department
 * @date: 2023/12/7 13:58
 * @version: 1.0
 */
@Data
public class Department {
    private int id;
    private String name;
    private int companyId;
    private Date buildDate;
    private int number;
}
