package com.qeeka;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neal.xu on 8/1 0001.
 */
public class SimpleQuery {
    private String hql;
    private Map<String, Object> parameters = new HashMap<String, Object>();

    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
