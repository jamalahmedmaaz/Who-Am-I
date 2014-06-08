package com.app.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;

/**
 * Created by cassandra on 6/9/14.
 */
public abstract class AbstractEntity {

    protected int id;

    @PrimaryKey
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
