package domain.vo;

import lombok.Data;

/**
 * @author 胡代伟
 * @description 统一相应
 * @date 2023-12-27 15:02
 */
@Data
public class ResponseVo<T> {
    private  int code;
    private  T data;
    private  String msg;

    public static <T> ResponseVo<T> success(T data,String msg){
        ResponseVo<T> tResponseVo = new ResponseVo<>();
        tResponseVo.setCode(0);
        tResponseVo.setData(data);
        tResponseVo.setMsg(msg);
        return tResponseVo;
    }

    public static <T> ResponseVo<T> success(T data){
        ResponseVo<T> tResponseVo = new ResponseVo<>();
        tResponseVo.setCode(0);
        tResponseVo.setData(data);
        tResponseVo.setMsg(null);
        return tResponseVo;
    }

    public static <T> ResponseVo<T> fail(T data,String msg){
        ResponseVo<T> tResponseVo = new ResponseVo<>();
        tResponseVo.setCode(1);
        tResponseVo.setData(data);
        tResponseVo.setMsg(msg);
        return tResponseVo;
    }

    public static <T> ResponseVo<T> fail(T data){
        ResponseVo<T> tResponseVo = new ResponseVo<>();
        tResponseVo.setCode(1);
        tResponseVo.setData(data);
        tResponseVo.setMsg(null);
        return tResponseVo;
    }

    public static <T> ResponseVo<T> backException(int code,T data,String msg){
        ResponseVo<T> tResponseVo = new ResponseVo<>();
        tResponseVo.setCode(code);
        tResponseVo.setData(data);
        tResponseVo.setMsg(msg);
        return tResponseVo;
    }
}
