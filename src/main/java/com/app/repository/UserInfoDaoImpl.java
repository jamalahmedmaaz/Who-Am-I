package com.app.repository;

import com.app.model.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by cassandra on 6/9/14.
 */
@Repository
public class UserInfoDaoImpl extends AbstractCassandraDaoImpl<UserInfo, Integer> {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserInfoDaoImpl.class);

    @Override
    protected org.apache.log4j.Logger getLogger() {
        return logger;
    }
}
