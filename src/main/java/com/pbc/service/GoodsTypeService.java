package com.pbc.service;

import com.pbc.po.GoodsType;

import java.util.List;

/**
 * Created by Alex on 2016/10/6.
 */
public interface GoodsTypeService {
    GoodsType get(int id);
    int add(GoodsType g);
    int del(int id);
    int upd(GoodsType g);
    List<GoodsType> getAll();
}
