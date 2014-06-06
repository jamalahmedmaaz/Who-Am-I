package com.app.repository;

import com.app.model.UserInfo;

/**
 * Created by cassandra on 6/5/14.
 */
public interface CassandraRespository {
    public UserInfo findByUserName(String userName);
}
