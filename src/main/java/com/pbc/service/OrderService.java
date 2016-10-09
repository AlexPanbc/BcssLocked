package com.pbc.service;

import com.pbc.domainentity.qentity.order.AddOrder;
import com.pbc.domainentity.qentity.order.AllOrder;
import com.pbc.domainentity.qentity.order.SltOrder;

import java.util.List;

/**
 * Created by Alex on 2016/10/9.
 */
public interface OrderService {

    /**
     * 根据序号查找订单
     *
     * @param id
     * @return
     */
    SltOrder get(int id);

    /**
     * 查询所有订单
     *
     * @return
     */
    List<AllOrder> getAll();

    /**
     * 创建订单
     *
     * @param o
     * @return
     */
    int add(AddOrder o);

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    int del(int id);
}
