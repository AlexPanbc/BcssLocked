package com.pbc.service.impl;

import com.pbc.dao.GoodsOrderDao;
import com.pbc.domainentity.qentity.goodsorder.*;
import com.pbc.service.GoodsOrderService;
import com.pbc.utils.exceptions.CustomException;
import com.pbc.utils.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 2016/10/9.
 */
@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {

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
        if (o.getUsername().isEmpty()) {
            throw new ServiceException("用户名不能为空!");
            //controller 层使用CustomException这个异常类;
            //service层使用ServiceException异常类
        }
        return orderDao.add(o);
    }

    @Override
    public int del(int id) {
        return orderDao.del(id);
    }
}
