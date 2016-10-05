package com.pbc.controller;

import com.alibaba.fastjson.JSON;
import com.pbc.dao.PersonInfoDao;
import com.pbc.po.PersonInfo;
import com.pbc.service.PersonInfoService;
import com.pbc.webparams.requestparams.PersonInfoQueryPrams;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能：个人信息查询controller
 * 时间：2016年9月25日01:36:40
 * 作者：Alex
 */
@Controller
@RequestMapping("/personInfo")
public class PersonInfoController {

    /**
     * 打印日志使用
     */
    private static Logger logger = LogManager.getLogger(PersonInfoController.class);

    @Autowired
    private PersonInfoService personInfoService;


    /**
     * 访问地址：http://localhost:8080/BcssLocked/personInfo/getPersonInfoById
     * 示例：查找ID为1的person
     * 返回string为视图的名称
     *
     * @param map
     * @return
     */
    @RequestMapping("/getPersonInfoById")
    public String getPersonInfoById(ModelMap map) {
       /* PersonInfo personInfo=personInfoService.getPersonInfoById(1);
        map.put("person",personInfo);*/
        return "index";
    }


    /**
     * 访问地址：http://localhost:8089/ssmFrameWorkTest/personInfo/getAllPersonInfoList
     * 采用POST方式查询所有用户，返回json数据
     * （注：加入@ResponseBody注解之后，将方法的返回值就改成了返回json数据）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllPersonInfoList", method = RequestMethod.POST)
    public List<PersonInfo> getAllPersonInfoList() {
        logger.debug("访问查询所有用户接口");
        return personInfoService.getAllUsers();
    }

    /**
     * 访问地址：http://localhost:8089/ssmFrameWorkTest/personInfo/getPersonInfoById/1
     * 根据personInfo 的id查询PersonInfo，返回json数据
     *
     * @param personId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPersonInfoById/{personId}", method = RequestMethod.POST)
    public PersonInfo getPersonInfoById(@PathVariable("personId") int personId) {
        logger.debug("根据用户ID查询用户，接口参数为：" + JSON.toJSONString(personId));//发布到服务器之后，供调试时候查看log使用
        return personInfoService.getPersonInfoById(personId);
    }

    /**
     * 访问地址：http://localhost:8089/ssmFrameWorkTest/personInfo/getPersonBySexAndPhoneNum
     * 访问时候传给接口的json参数
     * 根据用户性别和用户电话号码查询用户
     * <p>
     * 接口请求参数：
     * *      {
     * "sex": 1,
     * "phonenum": "110"
     * }
     *
     * @param personInfoQueryPrams
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPersonBySexAndPhoneNum", method = RequestMethod.POST)
    public List<PersonInfo> getPersonBySexAndPhoneNum(@RequestBody PersonInfoQueryPrams personInfoQueryPrams) {
        logger.debug("根据用户ID查询用户，接口参数为：" + JSON.toJSONString(personInfoQueryPrams));//发布到服务器之后，供调试时候查看log使用
        return personInfoService.getPersonBySexAndPhoneNum(personInfoQueryPrams);
    }


}
