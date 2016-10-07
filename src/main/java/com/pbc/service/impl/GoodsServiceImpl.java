package com.pbc.service.impl;

import com.pbc.mapper.GoodsMapper;
import com.pbc.po.Goods;
import com.pbc.service.GoodsService;
import com.pbc.utils.Tools;
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
        List<Goods> goodsList=goodsMapper.selectByExample(null);
        if (goodsList==null || goodsList.size()==0){
            return null;
        }
        for(Goods goods: goodsList){
            Tools.format(Tools.YEAR_SECOND,goods.getCreatedon());
            Tools.format(Tools.YEAR_SECOND,goods.getModifiedon());
        }
        return goodsList;
    }
}
