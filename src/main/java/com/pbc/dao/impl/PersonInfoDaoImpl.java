package com.pbc.dao.impl;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by LiuHuiChao on 2016/10/1.
 */
public class PersonInfoDaoImpl {
    public String getAllPersonInfoSQL(){
        return new SQL(){{
                SELECT("id,name,sex,phoneNum,address");
                FROM("personinfo");
            }
        }.toString();
    }
}
