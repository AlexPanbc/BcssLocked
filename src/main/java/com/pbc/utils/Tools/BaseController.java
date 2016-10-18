package com.pbc.utils.Tools;

import com.alibaba.fastjson.JSONObject;
import com.pbc.utils.exceptions.ExceptionTip;
import com.pbc.utils.exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：Controller基类，包含controller公共方法
 * Created by LiuHuiChao on 2016/10/7.
 */
@Controller
public class BaseController {

    private static Logger logger = LogManager.getLogger(BaseController.class);

    protected static final String SUCCESS = "success";
    protected static final String ERROR = "error";

    /**
     * 静态常量定义错误信息
     */
    public static final String TIP2000 = "这里写入错误信息";

    /**
     * 拦截RuntimeException，进行处理
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String noControlException(RuntimeException ex){
        logger.debug("未知异常",ex);
        return "error/errorpage";
    }

    /**
     * 拦截ServiceException
     * @param se
     * @param request
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public String operateExp(ServiceException se,HttpServletRequest request){
        String msg = se.getMessage();
        ExceptionTip tip = null;
        int msgCode = Integer.parseInt(msg);
        switch (msgCode) {
            case 1000:
                tip = new ExceptionTip(msg);
                break;
            case 2000:
                tip = new ExceptionTip(msg, TIP2000);
                break;
            default:
                break;
        }
        logger.debug("错误码 ："+msg , se);
        return toJSONString(tip);
    }



    /**
     * 将对象转换为jsonString
     *
     * @param object
     * @return
     */
    protected String toJSONString(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * 将数据转换为string
     * @param data
     * @param total
     * @return
     */
    public String toJSONString(Object data, Long total) {
        JSONObject obj = new JSONObject();
        obj.put("data", data);
        obj.put("total", total);
        return obj.toJSONString();
    }

    /**
     * 获取错误信息
     * @param result
     * @return
     */
    protected Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
            System.out.println("error.getField():" + error.getField());
            System.out.println("error.getDefaultMessage():" + error.getDefaultMessage());
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }
}
