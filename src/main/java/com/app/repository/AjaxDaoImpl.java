package com.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by cassandra on 6/9/14.
 */
@Repository
public class AjaxDaoImpl implements AjaxDao {

    @Autowired
    private CassandraRespository cassandraRespository;

    @Override
    public boolean findValueExists(String value) {
        return false;
    }
}
