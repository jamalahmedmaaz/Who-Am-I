package com.app.orm;

/**
 * Created by cassandra on 6/23/14.
 */
@JCassandraEntity(name = "userInfo")
public class User {

    @PrimaryKey
    private Long id;
    @JCassandraColumn(name = "userName")
    private String name;

    private String email;


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
}
