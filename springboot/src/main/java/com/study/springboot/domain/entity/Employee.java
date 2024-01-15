package com.study.springboot.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName employee
 */
@Data
public class Employee implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
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