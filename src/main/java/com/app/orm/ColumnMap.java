package com.app.orm;

import java.lang.reflect.Field;

/**
 * Created by cassandra on 6/22/14.
 */
public class ColumnMap {

    private String columnName;
    private String fieldName;
    private Field field;
    private DataMap dataMap;

    public ColumnMap(String nameOfColumn, String dataType, String nameOfField) {
        this.columnName = nameOfColumn;
        this.fieldName = nameOfField;
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
}
