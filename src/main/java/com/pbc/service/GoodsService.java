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

    /**
     * 根据ID查询商品
     * @param id
     * @return
     */
    GoodsListResponse getGoodsById(String id);

    /**
     * 添加商品信息
     * @param goods
     * @return
     */
    int addGoods(Goods goods);

    /**
     * 更新商品信息by primary key
     * @param goods
     * @return
     */
    int updateGoodsByPK(Goods goods);

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    int  deleteGoodsByPK(final String id);
}
