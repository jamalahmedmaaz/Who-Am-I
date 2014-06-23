package com.app.orm;

import com.app.model.UserInfo;

/**
 * Created by cassandra on 6/22/14.
 */
public class UserInfoMapper extends Mapper {

    private DataMap dataMap = new DataMap();

    protected void loadDataMap() {
        dataMap = new DataMap(UserInfo.class, "userInfo");
        dataMap.addColumn("lastName", "varachar", "lastName");
    }

    public UserInfo find(Long key) {
        return (UserInfo) findObject(key);
    }

}
