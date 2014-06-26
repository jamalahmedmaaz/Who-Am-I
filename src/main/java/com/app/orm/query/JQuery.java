package com.app.orm.query;

import com.app.orm.session.JCassandraSessionImpl;
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
    private JCassandraSessionImpl cassandraSessionImpl;
    private Long identifier;

    public JQuery(Class klass, List criteria) {
        this.klass = klass;
        this.criteria = criteria;
    }

    public JQuery(Class klass, JCassandraSessionImpl cassandraSessionImpl) {
        this.klass = klass;
        this.cassandraSessionImpl = cassandraSessionImpl;
    }

    public JQuery(Class klass, List criteria, JCassandraSessionImpl cassandraSessionImpl) {
        this.klass = klass;
        this.criteria = criteria;
        this.cassandraSessionImpl = cassandraSessionImpl;
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
        return cassandraSessionImpl.getResult(this);
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String generateSelectQuery() {
        String cql = "SELECT " + getAllFields() + " FROM " + getTableName() + " WHERE " + getCriterias() + " LIMIT 100;";
        return cql;
    }


    public String getAllFields() {
        return cassandraSessionImpl.getDataMap(klass.getSimpleName()).columnList();
    }

    public Object getTableName() {
        return cassandraSessionImpl.getDataMap(klass.getSimpleName()).getTableName();
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

    public JCassandraSessionImpl getCassandraSessionImpl() {
        return cassandraSessionImpl;
    }

    public void setCassandraSessionImpl(JCassandraSessionImpl cassandraSessionImpl) {
        this.cassandraSessionImpl = cassandraSessionImpl;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
}
