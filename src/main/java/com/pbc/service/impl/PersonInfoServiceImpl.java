package com.pbc.service.impl;

import com.pbc.dao.PersonInfoDao;
import com.pbc.mapper.PersonInfoMapper;
import com.pbc.po.PersonInfo;
import com.pbc.po.PersonInfoExample;
import com.pbc.service.PersonInfoService;
import com.pbc.domainentity.qentity.PersonInfoQueryPrams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiuHuiChao on 2016/9/25.
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(int personId) {
        return personInfoMapper.selectByPrimaryKey(personId);
    }

    @Override
    public int addPerson(PersonInfo personInfo) {
        return personInfoMapper.insert(personInfo);
    }

    @Override
    public int deletePerson(int personId) {
        return personInfoMapper.deleteByPrimaryKey(personId);
    }

    @Override
    public int modifyPerson(PersonInfo personInfo) {
        return personInfoMapper.updateByPrimaryKeySelective(personInfo);
    }

    @Override
    public List<PersonInfo> getAllPersonInfo() {
        return personInfoDao.getAllUsers();
    }

    @Override
    public List<PersonInfo> getPersonBySexAndPhoneNum(PersonInfoQueryPrams personInfoQueryPrams) {
        /**
         * 使用example类来加入查询条件
         */
        PersonInfoExample example=new PersonInfoExample();
        example.createCriteria().
                andSexEqualTo(personInfoQueryPrams.getSex())./*性别查询条件*/
                andPhonenumLike(personInfoQueryPrams.getPhonenum());/*phoneNum查询条件*/
        return personInfoMapper.selectByExample(example);/*根据查询条件进行查询*/

    }

    /**
     * 查询所有personInfo信息
     * @return
     */
    @Override
    public List<PersonInfo> getAllUsers() {
        return personInfoDao.getAllUsers();
    }
}
