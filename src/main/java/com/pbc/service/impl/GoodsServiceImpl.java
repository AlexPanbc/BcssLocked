package com.pbc.service.impl;

import com.pbc.mapper.GoodsMapper;
import com.pbc.po.Goods;
import com.pbc.service.GoodsService;
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

    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.selectByExample(null);
    }
}
