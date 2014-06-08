package com.app.service;

import com.app.model.UserInfo;
import com.app.repository.UserInfoDao;
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
}
