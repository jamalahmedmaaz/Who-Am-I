package com.app.service;

import com.app.model.UserInfo;

/**
 * Created by cassandra on 6/5/14.
 */
public interface UserService {
    public void login(UserInfo userInfo);

    public Object findUser(UserInfo userInfo);
}
