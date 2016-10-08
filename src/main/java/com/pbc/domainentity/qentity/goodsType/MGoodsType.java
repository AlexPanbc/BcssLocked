package com.pbc.domainentity.qentity.goodsType;

import java.util.Date;

public class MGoodsType {
    /**
     * 商品类型序号
     */
    private int id;
    /**
     * 商品类型名称
     */
    private String name;

    /**
     * 商品类型创建时间
     */
    private Date createdOn;

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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

}
