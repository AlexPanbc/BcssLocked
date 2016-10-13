package com.pbc.domainentity.qentity.userInfo;

import javax.validation.constraints.Min;

/**
 * Created by Alex on 2016/10/9.
 */
public class UpdUserInfo extends AddUserInfo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Min(value = 1, message = "序号不能小于1")
    private int id;
}
