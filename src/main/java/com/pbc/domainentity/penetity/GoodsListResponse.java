package com.pbc.domainentity.penetity;


import java.io.Serializable;


/**
 *
 * 功能：查询商品列表返回单个实体数据类型
 * Created by LiuHuiChao on 2016/10/7.
 */
public class GoodsListResponse  implements Serializable {

    private Integer id;

    private String name;

    private String typeName;

    private Float price;

    private Integer number;

    private String createdon;

    private String modifiedon;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

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
