package com.app.orm;

import java.lang.reflect.Field;

/**
 * Created by cassandra on 6/22/14.
 */
public class ColumnMap {

    private String columnName;
    private boolean isPrimaryKey;
    private String fieldName;
    private Field field;
    private DataMap dataMap;

    public ColumnMap(String nameOfField, String dataType, String nameOfColumn, DataMap dataMap) {
        this.columnName = nameOfColumn;
        this.fieldName = nameOfField;
        this.dataMap = dataMap;
        initializeField();
    }

    public ColumnMap(String nameOfField, String dataType, String nameOfColumn, DataMap dataMap, boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
        this.columnName = nameOfColumn;
        this.fieldName = nameOfField;
        this.dataMap = dataMap;
        initializeField();
    }

    private void initializeField() {
        try {
            field = dataMap.getDomainClass().getDeclaredField(getFieldName());
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    public boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public void setField(Object domainObject, Object columnValue) {
        try {
            field.set(domainObject, columnValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getValue(DomainObject domainObject) {
        try {
            return field.get(domainObject);
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "ColumnMap{" +
                "columnName='" + columnName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", field=" + field +
                ", dataMap=" + dataMap +
                '}';
    }
}
