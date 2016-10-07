package com.pbc.dao.impl;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by LiuHuiChao on 2016/10/7.
 */
public class GoodsDaoImpl {

    public String getAllGoodsListSQL(){
        return new SQL(){{
            SELECT("id,name,(SELECT gtype.NAME FROM goodstype gtype WHERE gtype.id = Type) as typeName, price, Number as number,createdon,modifiedon");
            FROM("goods");
        }
        }.toString();
    }
}
