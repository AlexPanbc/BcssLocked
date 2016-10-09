package com.pbc.dao.impl;

import com.pbc.domainentity.qentity.order.AddOrder;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Alex on 2016/10/9.
 */
public class OrderDaoImpl {

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
                FROM("`order`");
                if (id > 0)
                    WHERE("id = #{id}");
            }
        }.toString();
    }


    public String getAll() {
        return new SQL() {{
            SELECT("id,userid,username,goodsid,goodsname,money");
            FROM("order");
        }}.toString();
    }


    public String add(final AddOrder a) {
        return new SQL() {{
            INSERT_INTO("order");
            VALUES("userid", "#{a.userid}");
            VALUES("username", "#{a.username}");
            VALUES("goodsid", "#{a.goodsid}");
            VALUES("goodsname", "#{a.goodsname}");
            VALUES("money", "#{a.money}");
            VALUES("createdon", "NOW()");
            VALUES("modifiedon", "now()");
        }}.toString();
    }


    public String del(int id) {
        return new SQL() {
            {
                DELETE_FROM("order");
                WHERE("ID = #{id}");
            }
        }.toString();
    }
}
