package com.pbc.controller;

import com.pbc.domainentity.qentity.order.AddOrder;
import com.pbc.service.OrderService;
import com.pbc.utils.Tools.BaseController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alex on 2016/10/9.
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {
    private static Logger log = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/order/getAll
     * 获取所有订单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String getAll() {
        return toJSONString(orderService.getAll());
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/order/get
     * 根据id查询订单信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String get(@PathVariable("id") int id) {
        log.debug("根据id查询订单信息，订单id为：" + toJSONString(id));
        return toJSONString(orderService.get(id));
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/order/add
     * 创建订单
     *
     * @param o
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String add(@RequestBody AddOrder o) {
        log.debug("添加订单，订单参数为：" + toJSONString(o));
        return orderService.add(o) == 1 ? SUCCESS : ERROR;
    }

    /**
     * 接口地址示例：http://localhost:8080/BcssLocked/order/del
     * 删除订单信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String del(@PathVariable("id") int id) {
        log.debug("删除商品信息：" + toJSONString(id));
        return orderService.del(id) == 1 ? SUCCESS : ERROR;
    }
}
