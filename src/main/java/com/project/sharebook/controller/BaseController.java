package com.project.sharebook.controller;

import com.project.sharebook.error.BusinessException;
import com.project.sharebook.error.EmBusinessError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//对异常进行捕获
@Controller
public class BaseController {
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handlerException(HttpServletRequest request , Exception ex, Model model){
        Map<String,Object> responseData= new HashMap<String,Object>();//errCode  errMsg
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode",businessException.getErrorCode());  //获取封装在ex 中的自定义异常
            responseData.put("errMsg",businessException.getErrorMsg());
        }else {
            System.out.println("捕获异常");
            //未知错误
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrorMsg());

        }
        model.addAttribute("message",responseData);
       return "error/error";
    }
}
