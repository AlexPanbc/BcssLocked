package com.pbc.controller;

import com.alibaba.fastjson.JSON;
import com.pbc.po.UserInfo;
import com.pbc.service.UserInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Alex on 2016/10/5.
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    private static Logger log = LogManager.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据用户序号查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") int id) {
        log.debug("根据用户ID查询用户，接口参数为：" + JSON.toJSONString(id));//发布到服务器之后，供调试时候查看log使用
        userInfoService.get(id);
        return "index";
    }
}
