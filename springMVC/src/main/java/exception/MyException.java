package exception;

/**
 * @projectName: git
 * @package: exception
 * @className: MyException
 * @author: 胡代伟
 * @description:自定义异常
 * @date: 2023/12/26 14:33
 * @version: 1.0
 */
public class MyException extends Exception{
    private String msg;
    public MyException(String message) {
        super();
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
