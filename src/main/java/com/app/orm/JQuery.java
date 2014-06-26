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
        String cql = "SELECT " + getAllFields() + " FROM " + getTableName() + " WHERE " + getCriterias() + " LIMIT 100;";
        return cql;
    }


    public String getAllFields() {
        return cassandraSession.getDataMap(klass.getSimpleName()).columnList();
    }

    public Object getTableName() {
        return cassandraSession.getDataMap(klass.getSimpleName()).getTableName();
    }

    public String getCriterias() {
        StringBuffer query = new StringBuffer();
        int counter = 0;
        for (JCriteria c : this.criteria) {
            if (counter > 0) {
                query.append(" AND ");
            }
            query.append(c.toString());
            counter++;
        }

        return query.toString();
    }

    public Class getKlass() {
        return klass;
    }

    public void setKlass(Class klass) {
        this.klass = klass;
    }

    public List<JCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<JCriteria> criteria) {
        this.criteria = criteria;
    }

    public CassandraSession getCassandraSession() {
        return cassandraSession;
    }

    public void setCassandraSession(CassandraSession cassandraSession) {
        this.cassandraSession = cassandraSession;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
}
