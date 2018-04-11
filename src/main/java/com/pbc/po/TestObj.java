package com.pbc.po;

import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/27
 * \* Time: 下午5:45
 * \* Description:
 * \
 */
public class TestObj {

    String mainbmsno;
    int committype;
    String errormsg;
    List<Item> datalist;

    public List<Item> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<Item> datalist) {
        this.datalist = datalist;
    }

    public String getMainbmsno() {
        return mainbmsno;
    }

    public void setMainbmsno(String mainbmsno) {
        this.mainbmsno = mainbmsno;
    }

    public int getCommittype() {
        return committype;
    }

    public void setCommittype(int committype) {
        this.committype = committype;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
}
