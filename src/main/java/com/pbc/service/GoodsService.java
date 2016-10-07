package com.pbc.service;

import com.pbc.po.Goods;
import com.pbc.webparams.responseparams.GoodsListResponse;

import java.util.List;

/**
 * Created by LiuHuiChao on 2016/10/7.
 */
public interface GoodsService {
    /**
     * 查询所有商品列表
     * @return
     */
    List<GoodsListResponse> getAllGoods();
}
