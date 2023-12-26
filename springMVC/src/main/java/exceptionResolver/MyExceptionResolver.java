package exceptionResolver;

import exception.MyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: exceptionResolver
 * @className: MyExceptionResolver
 * @author: 胡代伟
 * @description: 自定义异常处理器
 * @date: 2023/12/26 14:37
 * @version: 1.0
 */
@Component
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MyException myEx = null;
        //如果是自己定义的异常
        if (ex instanceof MyException) {
            myEx = (MyException) ex;
        }
        //如果是系统的异常,仍转换为自己的异常
        else {
            myEx = new MyException("您好,系统正在维护,请联系管理员!");
        }
        //将信息绑定到request作用域
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", myEx.getMsg());
        //转发到异常页面
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
