package com.pbc.dao.impl;

import com.pbc.po.Goods;
import org.apache.ibatis.jdbc.SQL;

/**
 * 功能：商品类操作的sql语句构建类
 * Created by LiuHuiChao on 2016/10/7.
 */
public class GoodsDaoImpl {

    public String getAllGoodsListSQL(){
        return new SQL(){{
            SELECT("id,name,(SELECT gtype.NAME FROM goodstype gtype WHERE gtype.id = Type) as typeName, price, Number as number,DATE_FORMAT(createdon,'%Y-%m-%d %h:%i:%s') as createdon,DATE_FORMAT(modifiedon,'%Y-%m-%d %h:%i:%s') as modifiedon");
            FROM("goods");
        }
        }.toString();
    }

    public String getGoodsByIdSQL(final String id){
        return new SQL(){{
            SELECT("id,name,(SELECT gtype.NAME FROM goodstype gtype WHERE gtype.id = Type) as typeName, price, Number as number,DATE_FORMAT(createdon,'%Y-%m-%d %h:%i:%s') as createdon,DATE_FORMAT(modifiedon,'%Y-%m-%d %h:%i:%s') as modifiedon");
            FROM("goods");
            if(id!=null){
                WHERE("id= #{id}");
            }
        }
        }.toString();
    }

    public String addGoodsSQL(final Goods goods){
        return new SQL(){
            {
                INSERT_INTO("goods");
                VALUES("NAME", "#{name}");
                VALUES("type","#{type}");
                VALUES("price","#{price}");
                VALUES("Number","#{number}");
                VALUES("CreatedOn","now()");
                VALUES("ModifiedOn","now()");
            }
        }.toString();
    }

    public String updateGoodsByPK(final Goods goods){
        return new SQL(){
            {
                UPDATE("goods");
                SET("NAME = #{name}");
                SET("type = #{type}");
                SET("price = #{price}");
                SET("Number = #{number}");
                SET("ModifiedOn = NOW()");
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String deleteGoodsByPK(final String id){
        return new SQL(){
            {
                DELETE_FROM("goods");
                WHERE("ID = #{id}");
            }
        }.toString();
    }
}
