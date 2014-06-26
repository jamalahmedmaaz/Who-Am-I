package com.app.orm.helpers;

import com.app.orm.annotation.JCassandraColumn;
import com.app.orm.annotation.JCassandraEntity;
import com.app.orm.annotation.JPrimaryKey;

/**
 * Created by cassandra on 6/23/14.
 */
@JCassandraEntity(name = "userinfo")
public class User {

    @JPrimaryKey(name = "id")
    private Long id;
    @JCassandraColumn(name = "userName")
    private String name;

    @JCassandraColumn(name = "email")
    private String email;

    @JCassandraColumn(name = "address")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
