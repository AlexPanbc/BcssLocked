package com.pbc.domainentity.qentity.goodsorder;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by Alex on 2016/10/9.
 */
public class AddGoodsOrder {
    @Min(value = 1, message = "用户序号不能小于1")
    private int userid;
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @Min(value = 1, message = "商品序号不能小于1")
    private int goodsid;

    @NotBlank(message = "商品名称不能为空")
    private String goodsname;

    private Float money;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
}
