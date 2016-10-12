package com.pbc.controller;

import com.pbc.domainentity.qentity.goodsorder.AddGoodsOrder;
import com.pbc.service.GoodsOrderService;
import com.pbc.utils.Tools.BaseController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tools.ant.types.resources.comparators.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

/**
 * Created by Alex on 2016/10/9.
 */
@Controller
@RequestMapping("goodsorder")
public class GoodsOrderController extends BaseController {
    private static Logger log = LogManager.getLogger(GoodsOrderController.class);

    @Autowired
    private GoodsOrderService goodsOrderService;

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/getAll
     * 获取所有订单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String getAll() {
        return toJSONString(goodsOrderService.getAll());
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/get
     * 根据id查询订单信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String get(@PathVariable("id") int id) {
        log.debug("根据id查询订单信息，订单id为：" + toJSONString(id));
        return toJSONString(goodsOrderService.get(id));
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/add
     * 创建订单
     *
     * @param o
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String add(@Valid @RequestBody AddGoodsOrder o) { //呵呵 验证不顶用
        log.debug("添加订单，订单参数为：" + toJSONString(o));
        return goodsOrderService.add(o) == 1 ? SUCCESS : ERROR;
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/goodsorder/del
     * 删除订单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String del(@PathVariable("id") int id) {
        log.debug("删除商品信息：" + toJSONString(id));
        return goodsOrderService.del(id) == 1 ? SUCCESS : ERROR;
    }
}
