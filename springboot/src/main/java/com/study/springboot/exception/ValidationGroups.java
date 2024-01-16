package com.study.springboot.exception;

/**
 * @author 胡代伟
 * @description 用于hibernate-validator分组校验的分组
 * @date 2023-12-27 15:50
 */
public class ValidationGroups {
    // 插入
    public interface insert{};
    // 更新
    public interface update{};
    // 删除
    public interface delete{};
}
