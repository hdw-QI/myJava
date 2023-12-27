package exception;

/**
 * @author 胡代伟
 * @description 异常枚举
 * @date 2023-12-27 15:09
 */
public enum ExceptionEnum {
    SYS_EXCEPTION(101,"系统异常"),
    EMAIL_FORMAT(201,"邮箱格式错误");
    private int code;
    private String msg;

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

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
