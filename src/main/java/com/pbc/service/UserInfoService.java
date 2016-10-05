package com.pbc.service;

import com.pbc.po.UserInfo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by Alex on 2016/10/5.
 */
public interface UserInfoService {
    UserInfo get(int id );
    List<UserInfo> getAll();
    int add(UserInfo u);
    int upd(UserInfo u);
    int del(int id);
}

/**
 * 操作接口
 */
interface Operation {
    /**
     * 获取实体根据序号
     *
     * @param id
     * @return
     */
    <T> T get(int id);

    /**
     * 创建操作
     *
     * @param t
     * @return
     */
    int add(Class<T> t );

    /**
     * 删除操作
     *
     * @param id
     * @return
     */
    int del(int id);

    /**
     * 更新操作
     *
     * @param t
     * @return
     */
    int upd(T t);

    /**
     * 获取所有
     *
     * @return
     */
    <T> List<T> getAll();
}
