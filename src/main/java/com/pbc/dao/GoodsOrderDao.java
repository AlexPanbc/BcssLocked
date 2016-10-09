package com.pbc.dao;

import com.pbc.dao.impl.GoodsOrderDaoImpl;
import com.pbc.domainentity.qentity.goodsorder.AddGoodsOrder;
import com.pbc.domainentity.qentity.goodsorder.AllGoodsOrder;
import com.pbc.domainentity.qentity.goodsorder.SltGoodsOrder;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Alex on 2016/10/9.
 */
@Resource
public interface GoodsOrderDao {


    /**
     * 根据序号查找订单
     *
     * @param id
     * @return
     */
    @SelectProvider(type = GoodsOrderDaoImpl.class, method = "get")
    SltGoodsOrder get(int id);

    /**
     * 查询所有订单
     *
     * @return
     */
    @SelectProvider(type = GoodsOrderDaoImpl.class, method = "getAll")
    List<AllGoodsOrder> getAll();

    /**
     * 创建订单
     *
     * @param o
     * @return
     */
    @InsertProvider(type = GoodsOrderDaoImpl.class, method = "add")
    int add(AddGoodsOrder o);

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    @DeleteProvider(type = GoodsOrderDaoImpl.class, method = "del")
    int del(int id);

}
