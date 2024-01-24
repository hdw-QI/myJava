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

    /*
    //LocalDateTime 类型需要指定序列化类、反序列化类
    //指定序列化类
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    //指定反序列化类
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime localTime;
    */

    private static final long serialVersionUID = 1L;
}