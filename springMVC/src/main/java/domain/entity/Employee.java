package domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName employee
 */
@Data
@ApiModel("员工实体")
public class Employee implements Serializable {
    /**
     * 
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 
     */
    @ApiModelProperty("员工姓名")
    private String name;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private String isMale;

    /**
     * 
     */
    private Date joinDate;

    /**
     * 
     */
    private Integer salary;

    /**
     * 
     */
    private Integer deptId;

    /**
     * 
     */
    private String photo;

    private static final long serialVersionUID = 1L;
}