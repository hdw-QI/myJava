package com.study.springboot.exception;


import com.study.springboot.domain.vo.RestResponseVo;
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
@RestControllerAdvice
public class GlobeExceptionResolver {
    @ExceptionHandler(SysException.class)
    public RestResponseVo<String> back(SysException sysException) {
        return RestResponseVo.backException(sysException.getCode(), sysException.getMsg(), null);
    }


    //处理hibernate-validator框架参数校验抛出的异常
    @ExceptionHandler(BindException.class)
    public RestResponseVo<String> methodArgumentNotValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> msgList = new ArrayList<>();
        //将错误信息放在msgList
        bindingResult.getFieldErrors().stream().forEach(item->msgList.add(item.getDefaultMessage()));
        //拼接错误信息
        StringBuilder msg= new StringBuilder();
        for (String s : msgList) {
            msg.append(s).append(",");
        }
        return RestResponseVo.backException(ExceptionEnum.SYS_EXCEPTION.getCode(), msg.toString(), ExceptionEnum.SYS_EXCEPTION.getMsg());
    }

}
