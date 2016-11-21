package com.pbc.service.impl;

import com.pbc.domainentity.Dto.UserLoginDto;
import com.pbc.mapper.UserInfoMapper;
import com.pbc.po.UserInfo;
import com.pbc.po.UserInfoExample;
import com.pbc.service.UserInfoService;
import com.pbc.utils.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 2016/10/5.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo get(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> getAll() {
        return userInfoMapper.selectByExample(null);
    }

    @Override
    public int add(UserInfo u) {
        u.setCreatedon(new Date());
        u.setModifiedon(new Date());
        return userInfoMapper.insert(u);
    }

    @Override
    public int upd(UserInfo u) {
        return userInfoMapper.updateByPrimaryKey(u);
    }

    @Override
    public int del(int id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserInfo login(UserLoginDto userLoginDto) {
        if(StringUtils.isEmpty(userLoginDto.getName())){
            throw new ServiceException("2000","用户名不能为空！");
        }
        if(StringUtils.isEmpty(userLoginDto.getPass())){
            throw new ServiceException("2000","密码不能为空！");
        }
        UserInfoExample example=new UserInfoExample();
        example.createCriteria().andNameEqualTo(userLoginDto.getName());
        List<UserInfo> userInfo=userInfoMapper.selectByExample(example);
        if(userInfo==null || userInfo.size()==0){
            throw new ServiceException("2000","用户不存在!");
        }
        if(!userInfo.get(0).getPass().equals(userLoginDto.getPass())){
            throw new ServiceException("2000","密码不正确!");
        }
        return userInfo.get(0);
    }
}
