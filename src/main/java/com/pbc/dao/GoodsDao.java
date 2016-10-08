package com.pbc.dao;

import com.pbc.dao.impl.GoodsDaoImpl;
import com.pbc.po.Goods;
import com.pbc.domainentity.penetity.GoodsListResponse;
import org.apache.ibatis.annotations.*;

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

    /**
     * 根据ID查询商品
     * @param id
     * @return
     */
    @SelectProvider(type = GoodsDaoImpl.class, method = "getGoodsByIdSQL")
    GoodsListResponse getGoodsById(String id);

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @InsertProvider(type = GoodsDaoImpl.class, method = "addGoodsSQL")
    int addGoods(Goods goods);

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    @UpdateProvider(type = GoodsDaoImpl.class, method = "updateGoodsByPK")
    int updateGoodsByPK(Goods goods);

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @DeleteProvider(type = GoodsDaoImpl.class, method = "deleteGoodsByPK")
    int  deleteGoodsByPK(final String id);

}
