package md5.domain.vo;

/**
 * @projectName: git
 * @package: md5.domain.vo
 * @className: RestResponse
 * @author: 胡代伟
 * @description: 统一响应
 * @date: 2023/12/5 13:21
 * @version: 1.0
 */
public class RestResponse<T> {
    private int code;
    private String msg;

    private T data;

    public RestResponse() {
    }

    public RestResponse(int code, String msg, T data) {
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
    public static  <T>  RestResponse<T> success(String msg,T data){
        return new RestResponse<>(0, msg, data);
    }
    public static  <T> RestResponse<T> fail(String msg,T data){
        return new RestResponse<>(1, msg, data);
    }
}
