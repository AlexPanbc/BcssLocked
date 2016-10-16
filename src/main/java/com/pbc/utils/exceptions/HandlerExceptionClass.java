package com.pbc.utils.exceptions;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：统一处理service跟controller层抛出的异常
 * Created by LiuHuiChao on 2016/10/16.
 *
 *  如果与业务功能相关的异常，建议在service中抛出异常。
    与业务功能没有关系的异常，建议在controller中抛出。

 */
public class HandlerExceptionClass implements HandlerExceptionResolver {

    /**
     * 解析异常
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CustomException customException=null;
        ServiceException serviceException=null;
        if(ex instanceof CustomException){
            customException=(CustomException) ex;
            //错误信息
            String message = customException.getMessage();
            ModelAndView modelAndView = new ModelAndView();
            //将错误信息传到页面
            modelAndView.addObject("message", message);
            //指向错误页面
            modelAndView.setViewName("error");
            return modelAndView;

        }else if(ex instanceof ServiceException){
            serviceException=(ServiceException)ex;
            //错误信息
            String message = serviceException.getMessage();
            ModelAndView modelAndView = new ModelAndView();
            //将错误信息传到页面
            modelAndView.addObject("message", message);
            //指向错误页面
            modelAndView.setViewName("error");
            return modelAndView;

        }
        else{
            customException=new CustomException("未知异常！");
            //错误信息
            String message = customException.getMessage();
            ModelAndView modelAndView = new ModelAndView();
            //将错误信息传到页面
            modelAndView.addObject("message", message);
            //指向错误页面
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }
}
