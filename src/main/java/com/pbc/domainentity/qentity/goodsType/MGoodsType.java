package com.pbc.domainentity.qentity.goodsType;

import java.util.Date;

public class MGoodsType extends UpdGoodsType {

    /**
     * 商品类型创建时间
     */
    private Date createdon;

    /**
     * 商品类型修改时间
     */
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
