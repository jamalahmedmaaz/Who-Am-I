package com.app.orm;

/**
 * Created by cassandra on 6/22/14.
 */
public class UnitOfWork {
    public boolean isLoaded(Long key) {
        return false;
    }

    public Object getObject(Long key) {
        return null;
    }

    public void registerClean(DomainObject domainObject) {

    }
}
