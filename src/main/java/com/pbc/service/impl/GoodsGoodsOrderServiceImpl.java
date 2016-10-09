package com.pbc.service.impl;

import com.pbc.dao.GoodsOrderDao;
import com.pbc.domainentity.qentity.goodsorder.*;
import com.pbc.service.GoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 2016/10/9.
 */
@Service
public class GoodsGoodsOrderServiceImpl implements GoodsOrderService {

    @Autowired
    private GoodsOrderDao orderDao;

    @Override
    public SltGoodsOrder get(int id) {
        return orderDao.get(id);
    }

    @Override
    public List<AllGoodsOrder> getAll() {
        return orderDao.getAll();
    }

    @Override
    public int add(AddGoodsOrder o) {
        return orderDao.add(o);
    }

    @Override
    public int del(int id) {
        return orderDao.del(id);
    }
}
