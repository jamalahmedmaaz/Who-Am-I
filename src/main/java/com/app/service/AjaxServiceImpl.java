package com.app.service;

import com.app.repository.AjaxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cassandra on 6/9/14.
 */
@Service
public class AjaxServiceImpl implements AjaxService {

    @Autowired
    private AjaxDao ajaxDao;

    @Override
    public boolean valiateUserNameExists(String userName) {
        return false;
    }
}
