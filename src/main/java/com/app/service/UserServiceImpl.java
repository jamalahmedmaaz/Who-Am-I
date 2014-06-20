package com.app.service;

import com.app.model.UserInfo;
import com.app.repository.UserInfoDao;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cassandra on 6/5/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public void login(UserInfo userInfo) {
        UserInfo userInfo1 = userInfoDao.findById(userInfo.getId());
    }

    @Override
    public UserInfo findUser(UserInfo userInfo) {
        Clause clause = QueryBuilder.eq("userName", userInfo.getUserName());
        UserInfo one = userInfoDao.findOne(Lists.newArrayList(clause));
        return one;
    }
}
