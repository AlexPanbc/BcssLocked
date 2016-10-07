package com.pbc.dao;

import com.pbc.dao.impl.GoodsDaoImpl;
import com.pbc.webparams.responseparams.GoodsListResponse;
import org.apache.ibatis.annotations.SelectProvider;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 功能：商城查询Dao
 * Created by LiuHuiChao on 2016/10/7.
 */
@Resource
public interface GoodsDao {

    /**
     * 获取所有商品列表
     * @return
     */
    @SelectProvider(type = GoodsDaoImpl.class, method = "getAllGoodsListSQL")
    List<GoodsListResponse> getAllGoodsList();
}
