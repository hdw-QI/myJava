package domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @projectName: git
 * @package: domain.entity
 * @className: Company
 * @author: 胡代伟
 * @description: 公司实体类
 * @date: 2023/12/8 17:54
 * @version: 1.0
 */
@Data
public class Company {
    private int id;
    private String name;
    private String location;
    private Date buildDate;
}
