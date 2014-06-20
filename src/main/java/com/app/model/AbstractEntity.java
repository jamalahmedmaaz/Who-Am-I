package com.app.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.io.Serializable;

/**
 * Created by cassandra on 6/9/14.
 */
public abstract class AbstractEntity implements Serializable {

    protected int id;

    @PrimaryKey
    public BigInt getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
