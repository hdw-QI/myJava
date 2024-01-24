package com.study.springboot.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 胡代伟
 * @description 用于测试LocalDateTime 类型需要指定序列化类、反序列化类
 * @date 2024-01-24 11:39
 */
@Data
public class EmployeeVo {
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

    //指定序列化类
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    //指定反序列化类
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;
}
