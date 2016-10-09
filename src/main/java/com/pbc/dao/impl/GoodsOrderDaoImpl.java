package com.pbc.dao.impl;

import com.pbc.domainentity.qentity.goodsorder.AddGoodsOrder;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Alex on 2016/10/9.
 */
public class GoodsOrderDaoImpl {

    /**
     * 根据序号查询订单
     *
     * @param id 订单号
     * @return
     */
    public String get(final int id) {
        return new SQL() {
            {
                SELECT("id,userid,username,goodsid,goodsname,money,createdon,modifiedon");
                FROM("goodsorder");
                if (id > 0)
                    WHERE("id = #{id}");
            }
        }.toString();
    }


    public String getAll() {
        return new SQL() {{
            SELECT("id,userid,username,goodsid,goodsname,money");
            FROM("goodsorder");
        }}.toString();
    }


    public String add(final AddGoodsOrder a) {
        return new SQL() {{
            INSERT_INTO("goodsorder");
            VALUES("userid", "#{userid}");
            VALUES("username", "#{username}");
            VALUES("goodsid", "#{goodsid}");
            VALUES("goodsname", "#{goodsname}");
            VALUES("money", "#{money}");
            VALUES("createdon", "NOW()");
            VALUES("modifiedon", "NOW()");
        }}.toString();
    }


    public String del(int id) {
        return new SQL() {
            {
                DELETE_FROM("goodsorder");
                WHERE("ID = #{id}");
            }
        }.toString();
    }
}
