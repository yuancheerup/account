package com.account.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice // 标识该类为controller做增强
//public class ControllerExceptionHandler {
//    // 出现任何异常（可以换成具体地异常），交给该方法进行处理
//    @ExceptionHandler(Exception.class)
////    @ResponseBody
//    public String handleException(Exception e) {
//        return e.getMessage();
//    }
//}
