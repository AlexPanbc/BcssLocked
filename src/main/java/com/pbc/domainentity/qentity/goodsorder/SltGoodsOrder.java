package com.pbc.domainentity.qentity.goodsorder;

import java.util.Date;

/**
 * Created by Alex on 2016/10/9.
 */
public class SltGoodsOrder extends AllGoodsOrder {


    private Date createdon;
    private Date modifiedon;

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Date getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(Date modifiedon) {
        this.modifiedon = modifiedon;
    }

}
