package com.app.orm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * User: Jamal
 * Date: 6/23/14
 * Time: 6:51 PM
 */
public class JQuery {
    private Class klass;
    private List<JCriteria> criteria = Lists.newArrayListWithExpectedSize(0);
    private CassandraSession cassandraSession;
    private Long identifier;

    public JQuery(Class klass, List criteria) {
        this.klass = klass;
        this.criteria = criteria;
    }

    public JQuery(Class klass, CassandraSession cassandraSession) {
        this.klass = klass;
        this.cassandraSession = cassandraSession;
    }

    public JQuery(Class klass, List criteria, CassandraSession cassandraSession) {
        this.klass = klass;
        this.criteria = criteria;
        this.cassandraSession = cassandraSession;
    }

    public JQuery(Class klass) {
        this.klass = klass;
    }

    public JQuery() {
    }

    public void addCriteria(JCriteria jCriteria) {
        criteria.add(jCriteria);
    }

    public List getResults() {
        return cassandraSession.getResult(this);
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String generateSelectQuery() {
        String cql = "SELECT " + getAllFields() + " FROM " + getTableName() + " WHERE " + getCriterias() + " LIMIT 100";
        return cql;
    }


    public String getAllFields() {
        return cassandraSession.getDataMap(klass.getSimpleName()).columnList();
    }

    public Object getTableName() {
        return klass.getSimpleName();
    }

    public String getCriterias() {
        StringBuffer stringBuffer = new StringBuffer();
        for (JCriteria c : this.criteria) {
            stringBuffer.append(c.toString()).append(" AND ");
        }
        return stringBuffer.toString();
    }
}
