package com.pbc.domainentity.qentity.goodsType;

import java.util.Date;

public class MGoodsType extends UpdGoodsType {

    /**
     * 商品类型创建时间
     */
    private String createdon;

    /**
     * 商品类型修改时间
     */
    private String modifiedon;

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(String modifiedon) {
        this.modifiedon = modifiedon;
    }
}
