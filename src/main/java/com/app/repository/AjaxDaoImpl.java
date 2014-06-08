package com.app.repository;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cassandra on 6/9/14.
 */
@Repository
public class AjaxDaoImpl implements AjaxDao {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public boolean findValueExists(String value) {
        Map map = Maps.newHashMapWithExpectedSize(1);
        map.put("username", value);
        List list = userInfoDao.findByProperties(map);
        return false;
    }
}
