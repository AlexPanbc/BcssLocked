package com.pbc.service;

import com.pbc.po.Goods;

import java.util.List;

/**
 * Created by LiuHuiChao on 2016/10/7.
 */
public interface GoodsService {
    /**
     * 查询所有商品
     * @return
     */
    List<Goods> getAllGoods();
}
