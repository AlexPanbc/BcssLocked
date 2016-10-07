package com.pbc.service.impl;

import com.pbc.dao.GoodsDao;
import com.pbc.mapper.GoodsMapper;
import com.pbc.po.Goods;
import com.pbc.service.GoodsService;
import com.pbc.utils.Tools;
import com.pbc.webparams.responseparams.GoodsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiuHuiChao on 2016/10/7.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<GoodsListResponse> getAllGoods() {
       return goodsDao.getAllGoodsList();
    }
}
