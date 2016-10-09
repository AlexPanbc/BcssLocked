package com.pbc.utils.Tools;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;

/**
 * 功能：Controller基类，包含controller公共方法
 * Created by LiuHuiChao on 2016/10/7.
 */
@Controller
public class BaseController {

    protected  static final String SUCCESS="success";
    protected  static final String ERROR="error";

    /**
     * 将对象转换为jsonString
     * @param object
     * @return
     */
    protected String toJSONString(Object object) {
        return JSONObject.toJSONString(object);
    }

}
