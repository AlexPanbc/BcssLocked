package com.pbc.domainentity.qentity.order;

import java.util.Date;

/**
 * Created by Alex on 2016/10/9.
 */
public class SltOrder extends AllOrder {


    private Date createon;
    private Date modifiedon;

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }

    public Date getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(Date modifiedon) {
        this.modifiedon = modifiedon;
    }

}
