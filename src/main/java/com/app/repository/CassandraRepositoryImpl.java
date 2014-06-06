package com.app.repository;

import com.app.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CassandraRepositoryImpl implements CassandraRespository {
    private static final Logger LOG = LoggerFactory.getLogger(CassandraRepositoryImpl.class);

    @Resource(name = "cassandraTemplate")
    private CassandraTemplate cassandraTemplate;

    public CassandraRepositoryImpl() {
    }

    @Override
    public UserInfo findByUserName(String userName) {
        return null;
    }


}