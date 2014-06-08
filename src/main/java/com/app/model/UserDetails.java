package com.app.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by cassandra on 6/9/14.
 */
@Table
public class UserDetails {

    @PrimaryKey
    private int id;
    private String userName;
    private String email;
    private String password;


}
