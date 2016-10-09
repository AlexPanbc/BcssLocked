package com.pbc.dao;

import com.pbc.dao.impl.OrderDaoImpl;
import com.pbc.domainentity.qentity.order.AddOrder;
import com.pbc.domainentity.qentity.order.AllOrder;
import com.pbc.domainentity.qentity.order.SltOrder;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Alex on 2016/10/9.
 */
@Resource
public interface OrderDao {


    /**
     * 根据序号查找订单
     *
     * @param id
     * @return
     */
    @SelectProvider(type = OrderDaoImpl.class, method = "get")
    SltOrder get(int id);

    /**
     * 查询所有订单
     *
     * @return
     */
    @SelectProvider(type = OrderDaoImpl.class, method = "getAll")
    List<AllOrder> getAll();

    /**
     * 创建订单
     *
     * @param o
     * @return
     */
    @InsertProvider(type = OrderDaoImpl.class, method = "add")
    int add(AddOrder o);

    /**
     * 取消订单
     *
     * @param id
     * @return
     */
    @DeleteProvider(type = OrderDaoImpl.class, method = "del")
    int del(int id);

}
