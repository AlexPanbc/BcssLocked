package com.pbc.controller;

import com.alibaba.fastjson.JSON;
import com.pbc.po.UserInfo;
import com.pbc.service.UserInfoService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

/**
 * Created by Alex on 2016/10/5.
 * 功能：用户controller
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController  {
    private static Logger log = LogManager.getLogger(UserInfoController.class);

    @Autowired/*Autowired用来标记此属性字段为自动装配类，使用时候，不用new,它的生命周期由spring接管，只需配置即可；*/
    private UserInfoService userInfoService;

    /**
     * 创建用户
     *
     * @param u
     * @param map 返回参数用 为什么传入参数时也需要这样传参数？？？？？？？？？？？？？？？？？？？？？？
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody UserInfo u, ModelMap map) {
        log.debug("创建用户信息，接口参数为：" + JSON.toJSONString(u));//发布到服务器之后，供调试时候查看log使用
        if (userInfoService.add(u) != 1)
        throw new Exception("咩有创建数据成功u！");  //还有这个抛异常 也很奇怪 居然不好使？？？？？？？？？？？？？？？？？？？？？？？？？？
        map.put("allUser", userInfoService.getAll());
        return "AllUserInfo";
    }

    /**
     * 修改用户信息
     *
     * @param u
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public String upd(@RequestBody UserInfo u, ModelMap map) {
        log.debug("更新用户信息，接口参数为：" + JSON.toJSONString(u));//发布到服务器之后，供调试时候查看log使用
        if (userInfoService.upd(u) != 1)
            throw Exception("咩有更新数据u！");
        map.put("allUser", userInfoService.getAll());
        return "AllUserInfo";
    }

    /**
     * 删除用户信息
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable("id") int id, ModelMap map) {
        log.debug("根据用户ID查询用户，接口参数为：" + JSON.toJSONString(id));//发布到服务器之后，供调试时候查看log使用
        if (userInfoService.del(u) != 1)
            throw Exception("咩有更新数据u！");
        return "AllUserInfo";
    }

    /**
     * 根据用户序号查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") int id, ModelMap map) {
        log.debug("根据用户ID查询用户，接口参数为：" + JSON.toJSONString(id));//发布到服务器之后，供调试时候查看log使用
        map.put("user", userInfoService.get(id));
        return "UserInfo";
    }

    /**
     * 用户展示列表
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAll(ModelMap map) {
        log.debug("用户展示列表");//发布到服务器之后，供调试时候查看log使用
        map.put("allUser", userInfoService.getAll());
        return "AllUserInfo";
    }
}
