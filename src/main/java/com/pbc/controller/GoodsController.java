package com.pbc.controller;

import com.alibaba.fastjson.JSON;
import com.pbc.po.Goods;
import com.pbc.service.GoodsService;
import com.pbc.utils.BaseController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：商品Controller
 * Created by LiuHuiChao on 2016/10/7.
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {

    private static Logger logger = LogManager.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    /**
     * 接口地址示例：http://localhost:8089/BcssLocked/goods/allGoods
     * 获取所有商品
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "allGoods", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String allGoods() {
        return toJSONString(goodsService.getAllGoods());
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="singleGoods/{id}", method= RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String getGoodsById(@PathVariable("id") String id){
        logger.debug("根据id查询商品信息，商品id为："+ JSON.toJSONString(id));
        return  toJSONString(goodsService.getGoodsById(id));
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping(value="addGoods", method= RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String addGoods(@RequestBody Goods goods){
        logger.debug("添加商品，商品参数为："+ JSON.toJSONString(goods));
        return goodsService.addGoods(goods)==1 ? SUCCESS : ERROR;
    }

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updateGoodsByPK", method= RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String updateGoodsByPK(@RequestBody Goods goods){
        logger.debug("更新商品信息："+JSON.toJSONString(goods));
        return goodsService.updateGoodsByPK(goods)==1 ? SUCCESS : ERROR;
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="deleteGoods/{id}", method= RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String deleteGoodsByPK(@PathVariable("id") String id){
        logger.debug("删除商品信息："+JSON.toJSONString(id));
        return goodsService.deleteGoodsByPK(id)==1 ? SUCCESS : ERROR;
    }

}
