package com.study.springboot.exception;

/**
 * @projectName: git
 * @package: exception
 * @className: MyException
 * @author: 胡代伟
 * @description:自定义异常
 * @date: 2023/12/26 14:33
 * @version: 1.0
 */
public class SysException extends Exception{
    private String msg;
    private int code;



    public SysException(ExceptionEnum exceptionEnum) {
        super();
        this.msg = exceptionEnum.getMsg();
        this.code = exceptionEnum.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
