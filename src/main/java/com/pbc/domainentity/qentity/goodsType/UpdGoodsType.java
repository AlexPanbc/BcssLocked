package com.pbc.domainentity.qentity.goodsType;

/**
 * Created by Alex on 2016/10/8.
 */
 public class UpdGoodsType {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商品类型序号
     */
    private int id;
    /**
     * 商品类型名称
     */
    private String name;

}
