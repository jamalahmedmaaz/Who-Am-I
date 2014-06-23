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
    private List criteria = Lists.newArrayListWithExpectedSize(0);

    public JQuery(Class klass, List criteria) {
        this.klass = klass;
        this.criteria = criteria;
    }

    public JQuery(Class klass) {
        this.klass = klass;
    }

    public JQuery() {
    }

}
