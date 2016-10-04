package com.pbc.service;

import com.pbc.po.PersonInfo;
import com.pbc.webparams.requestparams.PersonInfoQueryPrams;

import java.util.List;

/**
 * Created by LiuHuiChao on 2016/9/25.
 */
public interface PersonInfoService {
     PersonInfo getPersonInfoById(int personId);
     int addPerson(PersonInfo personInfo);
     int deletePerson(int personId);
     int modifyPerson(PersonInfo personInfo);
     List<PersonInfo> getAllPersonInfo();
     List<PersonInfo> getPersonBySexAndPhoneNum(PersonInfoQueryPrams personInfoQueryPrams);
     List<PersonInfo> getAllUsers();
}
