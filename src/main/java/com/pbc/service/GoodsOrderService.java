package com.pbc.service;

import com.pbc.domainentity.qentity.goodsorder.AddGoodsOrder;
import com.pbc.domainentity.qentity.goodsorder.AllGoodsOrder;
import com.pbc.domainentity.qentity.goodsorder.SltGoodsOrder;

import java.util.List;

/**
 * Created by Alex on 2016/10/9.
 */
public interface GoodsOrderService {

    /**
     * 根据序号查找订单
     *
     * @param id
     * @return
     */
    SltGoodsOrder get(int id);

    /**
     * 查询所有订单
     *
     * @return
     */
    List<AllGoodsOrder> getAll();

    /**
     * 创建订单
     *
     * @param o
     * @return
     */
    int add(AddGoodsOrder o);

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    int del(int id);

    /**
     * 高并发枪杀商品
     *
     * @param o
     */
    int inst(AddGoodsOrder o);
}
