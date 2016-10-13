package com.pbc.utils.Tools;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：Controller基类，包含controller公共方法
 * Created by LiuHuiChao on 2016/10/7.
 */
@Controller
public class BaseController {

    protected static final String SUCCESS = "success";
    protected static final String ERROR = "error";

    /**
     * 将对象转换为jsonString
     *
     * @param object
     * @return
     */
    protected String toJSONString(Object object) {
        return JSONObject.toJSONString(object);
    }

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
