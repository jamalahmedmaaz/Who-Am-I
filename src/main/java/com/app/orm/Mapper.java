package com.app.orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cassandra on 6/22/14.
 */
public class Mapper {

    private JUnitOfWork JUnitOfWork;
    private DataMap dataMap;

    public Object findObject(Long key) {
        if (JUnitOfWork.isLoaded(key)) return JUnitOfWork.getObject(key);
        String sql = "SELECT " + dataMap.columnList() + " FROM " + dataMap.getTableName() + " Where " +
                "ID =?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DomainObject domainObject = null;
        preparedStatement = DB.prepare(sql);
        try {
            preparedStatement.setLong(1, key.longValue());
            resultSet.next();
            domainObject = load(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            DB.cleanUp(preparedStatement, resultSet);
        }
        return domainObject;
    }

    public void update(DomainObject domainObject) {
        String sql = "UPDATE" + dataMap.getTableName() + dataMap.updateList() + " WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        int i = 1;
        try {
            for (ColumnMap columnMap : dataMap.getColumnMaps()) {
                preparedStatement.setObject(i++, columnMap.getValue(domainObject));
            }
            preparedStatement.setLong(i, domainObject.getId().longValue());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.cleanUp(preparedStatement);
        }
    }

    private DomainObject load(ResultSet resultSet) throws IllegalAccessException, InstantiationException, SQLException {
        Long key = new Long(resultSet.getLong("ID"));
        if (JUnitOfWork.isLoaded(key)) return (DomainObject) JUnitOfWork.getObject(key);
        DomainObject domainObject = (DomainObject) dataMap.getDomainClass().newInstance();

        domainObject.setId(key);
        JUnitOfWork.registerClean(domainObject);
        loadFields(resultSet, domainObject);

        return domainObject;
    }

    private void loadFields(ResultSet resultSet, DomainObject domainObject) throws SQLException {
        for (ColumnMap cm : dataMap.getColumnMaps()) {
            Object columnValue = resultSet.getObject(cm.getColumnName());
            cm.setField(domainObject, columnValue);
        }
    }
}
