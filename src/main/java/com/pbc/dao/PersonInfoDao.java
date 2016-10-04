package com.pbc.dao;

import com.pbc.dao.impl.PersonInfoDaoImpl;
import com.pbc.po.PersonInfo;
import org.apache.ibatis.annotations.SelectProvider;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LiuHuiChao on 2016/10/1.
 */

@Resource
public interface PersonInfoDao {
    /**
     * 查询全部用户
     * @return
     */
    @SelectProvider(type = PersonInfoDaoImpl.class, method = "getAllPersonInfoSQL")
    List<PersonInfo> getAllUsers();

}
