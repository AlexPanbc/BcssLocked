package com.pbc.controller;

import com.alibaba.fastjson.JSON;
import com.pbc.domainentity.Dto.UserLoginDto;
import com.pbc.domainentity.qentity.userInfo.AddUserInfo;
import com.pbc.domainentity.qentity.userInfo.UpdUserInfo;
import com.pbc.po.UserInfo;
import com.pbc.service.UserInfoService;
import com.pbc.utils.Tools.BaseController;
import com.pbc.utils.Tools.BeanUtilsExtends;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * Created by Alex on 2016/10/5.
 * 功能：用户controller
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    private static Logger log = LogManager.getLogger(UserInfoController.class);

    @Autowired/*Autowired用来标记此属性字段为自动装配类，使用时候，不用new,它的生命周期由spring接管，只需配置即可；*/
    private UserInfoService userInfoService;

    /**
     * 创建用户
     *
     * @param u
     * @param map 返回参数用 这个参数是用来回传返回数据的，ModelMap里面封装了request，可以将要返回的数据放到里面
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Valid
    public String add(@RequestBody AddUserInfo u, ModelMap map, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> ap = getErrors(result);
            log.error(ap);
            return ERROR;
        }
        if (u == null) log.error("参数不能为空");
        log.debug("创建用户信息，接口参数为：" + JSON.toJSONString(u));//发布到服务器之后，供调试时候查看log使用
        UserInfo ui = new UserInfo();
        BeanUtilsExtends.copyProperties(ui, u);
        if (userInfoService.add(ui) == 1)
            map.put("allUser", userInfoService.getAll());
        return "AllUserInfo";
    }
    //todo http://localhost:8080/BcssLocked/userInfo/upd
    //// TODO: 2016/10/7 {"modifiedon":"2016-10-07","mailbox":"zhongwen@163.com","pass":"123456","phone":"13465423215","name":"终于可以插入中文了","id":13,"createdon":"2016-10-07"} 

    /**
     * 修改用户信息
     *
     * @param u
     * @param map
     * @return
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    @Valid
    public String upd(@RequestBody UpdUserInfo u, ModelMap map, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> mp = getErrors(result);
            log.error(mp);
            return ERROR;
        }
        if (u == null) log.error("参数不能为空");
        if (u.getId() == 0) log.error("序号必须大于0");
        log.debug("更新用户信息，接口参数为：" + JSON.toJSONString(u));//发布到服务器之后，供调试时候查看log使用
        UserInfo ui = userInfoService.get(u.getId());
        BeanUtilsExtends.copyProperties(ui, u);
        ui.setModifiedon(new Date());
        if (userInfoService.upd(ui) == 1)
            map.put("allUser", userInfoService.getAll());
        return "AllUserInfo";
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(@PathVariable("id") int id, ModelMap map) {
        log.debug("根据用户ID查询用户，接口参数为：" + JSON.toJSONString(id));//发布到服务器之后，供调试时候查看log使用
        if (userInfoService.del(id) == 1)
            map.put("allUser", userInfoService.getAll());
//            throw Exception("咩有更新数据u！"); //你好可爱呀，^_^炳灿叔叔~~
        return "AllUserInfo";
    }

//// TODO: 2016/10/7 http://localhost:8080/BcssLocked/userInfo/get/13

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
        map.put("userJson", JSON.toJSON(userInfoService.get(id)));
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

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String login(@RequestBody UserLoginDto userLoginDto){
        log.debug("登录用户：" + toJSONString(userLoginDto));
        return toJSONString(userInfoService.login(userLoginDto));
    }
}
