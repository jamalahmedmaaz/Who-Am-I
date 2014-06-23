package com.app.orm;

/**
 * User: Jamal
 * Date: 6/23/14
 * Time: 6:52 PM
 */
public class JCriteria {

    private String cqlOpertor;
    protected String field;
    protected Object value;

    public JCriteria(String cqlOperator, String fieldName, Object value) {
        this.cqlOpertor = cqlOperator;
        this.field = fieldName;
        this.value = value;
    }

    public static JCriteria greaterThan(String fieldName, int value) {
        return JCriteria.greaterThan(fieldName, new Integer(value));
    }

    public static JCriteria greaterThan(String fieldName, Object value) {
        return new JCriteria(" > ", fieldName, value);
    }

    public static void main(String... ar) throws ClassNotFoundException {
        CassandraSession cassandraSession = new CassandraSession();
        JQuery jQuery = cassandraSession.createQuery(User.class);
        JCriteria jCriteria = null;
        System.out.println(cassandraSession);
    }
}
