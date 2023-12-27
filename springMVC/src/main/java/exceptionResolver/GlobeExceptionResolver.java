package exceptionResolver;

import domain.vo.ResponseVo;
import exception.ExceptionEnum;
import exception.MyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 胡代伟
 * @description 全局异常处理器
 * @date 2023-12-27 14:36
 */
//@ControllerAdvice //告诉springMVC这个是一个异常处理器
@RestControllerAdvice //ResponseBody注解加ControllerAdvice
public class GlobeExceptionResolver {
//    @ExceptionHandler(MyException.class) //指定对那个异常进行处理。方法的参数必须为ExceptionHandler注解中的异常对象
//    public ModelAndView forward(MyException e){
//        //将信息绑定到request作用域
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("message", e.getMsg());
//        //转发到异常页面
//        modelAndView.setViewName("error");
//        return modelAndView;
//    }
    @ExceptionHandler(MyException.class)
    public ResponseVo<String> back(MyException myException) {
        return ResponseVo.backException(ExceptionEnum.SYS_EXCEPTION.getCode(), myException.getMsg(), ExceptionEnum.SYS_EXCEPTION.getMsg());
    }


    //处理hibernate-validator框架参数校验抛出的异常
    @ExceptionHandler(BindException.class)
    public ResponseVo<String> methodArgumentNotValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> msgList = new ArrayList<>();
        //将错误信息放在msgList
        bindingResult.getFieldErrors().stream().forEach(item->msgList.add(item.getDefaultMessage()));
        //拼接错误信息
        StringBuilder msg= new StringBuilder();
        for (String s : msgList) {
            msg.append(s).append(",");
        }
        return ResponseVo.backException(ExceptionEnum.SYS_EXCEPTION.getCode(), msg.toString(), ExceptionEnum.SYS_EXCEPTION.getMsg());
    }

}
