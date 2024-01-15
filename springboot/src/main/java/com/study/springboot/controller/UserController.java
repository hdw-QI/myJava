package com.study.springboot.controller;

import com.study.springboot.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡代伟
 * @description UserController
 * @date 2024-01-15 11:34
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @GetMapping("/getById")
    public User getById(Integer id) {
        log.info(id.toString());
        User user = new User();
        user.setUserId(id);
        user.setUserName("琪");
        return user;
    }
}
