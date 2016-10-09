package com.pbc.service.impl;

import com.pbc.dao.OrderDao;
import com.pbc.domainentity.qentity.order.*;
import com.pbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 2016/10/9.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public SltOrder get(int id) {
        return orderDao.get(id);
    }

    @Override
    public List<AllOrder> getAll() {
        return orderDao.getAll();
    }

    @Override
    public int add(AddOrder o) {
        return orderDao.add(o);
    }

    @Override
    public int del(int id) {
        return orderDao.del(id);
    }
}
