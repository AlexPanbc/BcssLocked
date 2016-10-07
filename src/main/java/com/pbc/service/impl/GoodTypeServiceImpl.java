package com.pbc.service.impl;

import com.pbc.mapper.GoodsTypeMapper;
import com.pbc.po.GoodsType;
import com.pbc.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 2016/10/6.
 */
@Service
public class GoodTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public GoodsType get(int id) {
        return goodsTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(GoodsType g) {
        return goodsTypeMapper.insert(g);
    }

    @Override
    public int del(int id) {
        return goodsTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int upd(GoodsType g) {
        return goodsTypeMapper.updateByPrimaryKey(g);
    }

    @Override
    public List<GoodsType> getAll() {
        return goodsTypeMapper.selectByExample(null);
    }
}
