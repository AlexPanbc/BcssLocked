package com.pbc.service.impl;


import com.pbc.dao.GoodsDao;
import com.pbc.dao.GoodsOrderDao;
import com.pbc.domainentity.penetity.GoodsListResponse;
import com.pbc.domainentity.qentity.goodsorder.*;
import com.pbc.po.Goods;
import com.pbc.service.GoodsOrderService;
import com.pbc.utils.Tools.BeanUtilsExtends;
import com.pbc.utils.Tools.RedisDao;
import com.pbc.utils.Tools.RedisLock;
import com.pbc.utils.exceptions.ServiceException;

import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Created by Alex on 2016/10/9.
 */
@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
    private static Logger log = LogManager.getLogger(GoodsOrderServiceImpl.class);
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsOrderDao orderDao;
    private RedisLock redisLock;
    @Autowired
    private RedisDao redisDao;

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
    public int inst(AddGoodsOrder o) {
        try {
            redisLock = new RedisLock("goods:" + o.getGoodsid(), redisDao.getShardedJedisPool());
            redisLock.lock(100000, 10);
            //获取缓存中商品信息，判断如果数量大于0则可以插入订单表，订单表插入成功之后商品数量减一
            String goods = redisDao.get("goods:" + o.getGoodsid());
            if (goods == null || goods.isEmpty()) return 0;//提示信息商品不存在1
            GoodsListResponse gr = (GoodsListResponse) JSONObject.toBean(JSONObject.fromObject(goods), GoodsListResponse.class);
            if (gr == null) return 0;//提示信息商品不存在2

            if (gr.getNumber() <= 0) {//商品售罄之后，修改数据库商品数量
                Goods g = new Goods();
                BeanUtilsExtends.copyProperties(g, gr);
                goodsDao.updateGoodsByPK(g);
                return 0;//提示信息商品已售罄
            }
            o.setCode(UUID.randomUUID().toString());
            if (orderDao.add(o) != 1) return 0;//创建订单失败
            gr.setNumber(gr.getNumber() - 1);
            redisDao.set("goods:" + o.getGoodsid(), toJSONString(gr));
            return 1;
        } catch (Exception e) {
            log.error(toJSONString(e));
            return 0;
        } finally {
            redisLock.unlock();
        }
    }

    @Override
    public int del(int id) {
        return orderDao.del(id);
    }
}
