package com.pbc.controller;

import com.alibaba.fastjson.JSON;
import com.pbc.po.GoodsType;
import com.pbc.service.GoodsTypeService;
import com.pbc.utils.BaseController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alex on 2016/10/6.
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController extends BaseController {
    private static Logger log = LogManager.getLogger(GoodsTypeController.class);

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 添加商品类型
     *
     * @param g
     * @param map
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody GoodsType g, ModelMap map) {
        log.debug("创建商品类型，接口参数：" + JSON.toJSONString(g));
        if (goodsTypeService.add(g) == 1)
            map.put("allGoodsType", goodsTypeService.getAll());
        return "AllGoodsType";
    }

    /**
     * 删除商品类型
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(@RequestParam int id, ModelMap map) {
        log.debug("删除商品类型，接口参数：" + JSON.toJSONString(id));
        if (goodsTypeService.del(id) == 1)
            map.put("allGoodsType", goodsTypeService.getAll());
        return "AllGoodsType";
    }

    /**
     * 修改商品类型
     *
     * @param g
     * @param map
     * @return
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public String upd(@RequestBody GoodsType g, ModelMap map) {
        log.debug("修改商品类型，接口参数：" + JSON.toJSONString(g));
        if (goodsTypeService.upd(g) == 1)
            map.put("allGoodsType", goodsTypeService.getAll());
        return "AllGoodsType";
    }

    /**
     * 获取商品类型
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") int id, ModelMap map) {
        log.debug("根据序号获取商品类型，接口参数：" + id);
        GoodsType goodsType = goodsTypeService.get(id);
        map.put("goodsType", goodsType);
        map.put("goodsTypejson", JSON.toJSONString(goodsType));
        return "GoodsType";
    }

    /**
     * 获取所有商品类型
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAll(ModelMap map) {
        log.debug("查询所有商品类型");
        map.put("allGoodsType", goodsTypeService.getAll());
        return "AllGoodsType";
    }
}
