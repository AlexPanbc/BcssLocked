package com.pbc.controller;

import com.pbc.service.GoodsService;
import com.pbc.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：商品Controller
 * Created by LiuHuiChao on 2016/10/7.
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 接口地址示例：http://localhost:8089/BcssLocked/goods/allGoods
     * 获取所有商品
     * @return
     */
    @ResponseBody
    @RequestMapping(value="allGoods", method= RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String allGoods(){
        return toJSONString(goodsService.getAllGoods());
    }

}
