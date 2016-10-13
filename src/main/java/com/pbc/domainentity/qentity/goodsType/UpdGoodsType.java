package com.pbc.domainentity.qentity.goodsType;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Alex on 2016/10/8.
 */
public class UpdGoodsType {
    /**
     * 商品类型序号
     */
    private int id;
    /**
     * 商品类型名称
     */
    @NotBlank(message = "商品类型名称不能为空")
    private String name;

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

}
