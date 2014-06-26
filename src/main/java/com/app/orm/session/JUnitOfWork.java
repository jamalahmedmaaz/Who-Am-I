package com.app.orm.session;

import com.app.orm.helpers.DomainObject;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by cassandra on 6/22/14.
 */

/*
* Responsibilities
 * 1. When its time to commit. THe UOW will decide what to do.
 * 2. It opens a transaction.
 * 3. Does concurrency checking (POL or OOL).
 * 4. Write data to DB.
*/
public class JUnitOfWork {

    private List newObjects = Lists.newArrayList();
    private List dirtyObjects = Lists.newArrayList();
    private List removedObjects = Lists.newArrayList();

    private static ThreadLocal current = new ThreadLocal();

    public static void newCurrent() {
        setCurrent(new JUnitOfWork());
    }

    public static void setCurrent(JUnitOfWork juow) {
        current.set(juow);
    }

    public static JUnitOfWork getCurrent() {
        return (JUnitOfWork) current.get();
    }

    public void registerNew(Object object) {
        newObjects.add(object);
    }

    public void registerDirty(Object object) {
        if (!dirtyObjects.contains(object) && !newObjects.contains(object)) {
            dirtyObjects.add(object);
        }
    }

    public void registerRemoved(Object object) {
        if (newObjects.remove(object))
            return;
        dirtyObjects.remove(object);
        if (!removedObjects.contains(object)) {
            removedObjects.add(object);
        }
    }

    public void registerClean(DomainObject domainObject) {

    }

    public void commit() {
        insertNew();
        updateDirty();
        deleteRemoved();
    }

    private void deleteRemoved() {

    }

    private void updateDirty() {

    }

    private void insertNew() {

    }

    public boolean isLoaded(Long key) {
        return false;
    }

    public Object getObject(Long key) {
        return null;
    }

}
