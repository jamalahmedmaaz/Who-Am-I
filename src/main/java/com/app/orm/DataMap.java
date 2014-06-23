package com.app.orm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by cassandra on 6/22/14.
 */
public class DataMap {
    private Class domainClass;
    private String tableName;
    private List<ColumnMap> columnMaps = Lists.newArrayList();

    public DataMap(Class<?> domainClass, String tableName) {
        this.domainClass = domainClass;
        this.tableName = tableName;
    }

    public DataMap() {
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public void setDomainClass(Class domainClass) {
        this.domainClass = domainClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnMap> getColumnMaps() {
        return columnMaps;
    }

    public void setColumnMaps(List<ColumnMap> columnMaps) {
        this.columnMaps = columnMaps;
    }

    public void addColumn(String nameOfField, String dataType, String nameOfColumn, DataMap dataMap) {
        columnMaps.add(new ColumnMap(nameOfField, dataType, nameOfColumn, dataMap));
    }

    public void addColumn(String nameOfField, String dataType, String nameOfColumn, DataMap dataMap, boolean primaryKey) {
        columnMaps.add(new ColumnMap(nameOfField, dataType, nameOfColumn, dataMap, primaryKey));
    }

    public String columnList() {
        StringBuffer stringBuffer = new StringBuffer();
        int count = 0;
        for (ColumnMap columnMap : columnMaps) {
            stringBuffer.append(columnMap.getColumnName());
            count = count + 1;
            if (count != columnMaps.size()) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }

    public String updateList() {
        StringBuffer stringBuffer = new StringBuffer(" SET ");
        for (ColumnMap columnMap : columnMaps) {
            stringBuffer.append(columnMap.getColumnName());
            stringBuffer.append("=?");
        }
        stringBuffer.setLength(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }
}
