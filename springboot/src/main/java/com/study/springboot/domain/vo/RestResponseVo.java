package com.study.springboot.domain.vo;

import lombok.Data;

/**
 * @author 胡代伟
 * @description 统一响应
 * @date 2023-12-29 12:54
 */
//@ApiModel("统一响应")
@Data
public class RestResponseVo<T> {
//    @ApiModelProperty("响应码")
    private int code;
//    @ApiModelProperty("提示信息")
    private String msg;
//    @ApiModelProperty("响应数据")
    private T data;

    public RestResponseVo() {
    }

    public RestResponseVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //成功响应
    public static  <T> RestResponseVo<T> success(String msg, T data){
        return new RestResponseVo<>(0, msg, data);
    }

    //失败响应
    public static  <T> RestResponseVo<T> fail(String msg, T data){
        return new RestResponseVo<>(1, msg, data);
    }

    //异常响应
    public static <T> RestResponseVo<T> backException(int code, String msg, T data) {
        return new RestResponseVo<>(code, msg, data);
    }

}
