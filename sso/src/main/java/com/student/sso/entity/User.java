package com.student.sso.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 胡代伟
 * @description TODO
 * @date 2024-03-11 15:16
 */
@Entity
@Data
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;

    @Column
    private String userName;

    @Column
    private String userPhone;

    @Column
    private String userPassword;
}
