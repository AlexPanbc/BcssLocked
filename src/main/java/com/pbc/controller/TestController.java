package com.pbc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pbc.po.TestObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/27
 * \* Time: 下午5:43
 * \* Description:
 * \
 */
@Controller
@RequestMapping("test")
public class TestController {
    @ResponseBody
    @RequestMapping(value = "console", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String getGoodsById(@RequestBody  TestObj body) {
        System.out.println(JSONObject.toJSONString(body));
        return  JSONObject.toJSONString(body);
    }
}
