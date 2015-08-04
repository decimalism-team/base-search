package com.qeeka;

import com.qeeka.operate.QueryOrder;

/**
 * Created by Neal on 8/5 0005.
 */
public class QuerySort {
    private String columnName;
    private QueryOrder queryOrder;

    public QuerySort() {
    }

    public QuerySort(String columnName, QueryOrder queryOrder) {
        this.columnName = columnName;
        this.queryOrder = queryOrder;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public QueryOrder getQueryOrder() {
        return queryOrder;
    }

    public void setQueryOrder(QueryOrder queryOrder) {
        this.queryOrder = queryOrder;
    }
}
