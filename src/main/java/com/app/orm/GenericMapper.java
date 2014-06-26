package com.app.orm;

import java.util.Set;

/**
 * User: Jamal
 * Date: 6/23/14
 * Time: 7:54 PM
 */
public class GenericMapper {

    private DataMap dataMap;

    public GenericMapper() {
    }

    public GenericMapper(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    public Set findEntityByWhereClause(String whereClause) {
        String cql = "SELECT " + dataMap.columnList() + " FROM " + dataMap.getTableName() + " WHERE " + whereClause;
        return null;
    }
}
