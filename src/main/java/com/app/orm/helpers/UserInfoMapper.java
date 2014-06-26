package com.app.orm.helpers;

import com.app.model.UserInfo;
import com.app.orm.mapper.DataMap;
import com.app.orm.mapper.Mapper;

/**
 * Created by cassandra on 6/22/14.
 */
public class UserInfoMapper extends Mapper {

    private DataMap dataMap = new DataMap();

    protected void loadDataMap() {
        dataMap = new DataMap(UserInfo.class, "userInfo");
        dataMap.addColumn("lastName", "varachar", "lastName", null);
    }

    public UserInfo find(Long key) {
        return (UserInfo) findObject(key);
    }

}
