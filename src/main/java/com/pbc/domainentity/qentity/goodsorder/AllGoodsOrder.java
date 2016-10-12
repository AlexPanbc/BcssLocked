package com.pbc.domainentity.qentity.goodsorder;

import javax.validation.constraints.Min;

/**
 * Created by Alex on 2016/10/9.
 */
public class AllGoodsOrder extends AddGoodsOrder {
    @Min(value = 1, message = "商品序号不能小于1")
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
