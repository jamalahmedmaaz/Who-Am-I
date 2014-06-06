package com.app.service;

import com.app.model.UserInfo;
import com.app.repository.CassandraRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cassandra on 6/5/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CassandraRespository cassandraRespository;

    @Override
    public void login(UserInfo userInfo) {
        cassandraRespository.findByUserName(userInfo.getUserName());
    }
}
