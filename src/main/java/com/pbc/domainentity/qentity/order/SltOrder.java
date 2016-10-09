package com.pbc.domainentity.qentity.order;

import java.util.Date;

/**
 * Created by Alex on 2016/10/9.
 */
public class SltOrder extends AllOrder {


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
