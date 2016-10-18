package com.pbc.utils.exceptions;

/**
 * 成功提示信息
 * Created by LiuHuiChao on 2016/10/18.
 */
public class SuccessTip  implements Tip  {
    private boolean success;

    public SuccessTip() {
        super();
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }
}
