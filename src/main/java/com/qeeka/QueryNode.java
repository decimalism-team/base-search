package com.qeeka;

import com.qeeka.operate.QuerySpecialOperate;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class QueryNode extends QueryHandle {
    static String format = "%s %s :%s";
    private String columnName;
    private Object value;
    private QuerySpecialOperate querySpecialOperate = QuerySpecialOperate.EQUALS;

    public QueryNode(String columnName, Object value) {
        this.columnName = columnName;
        this.value = value;
    }

    public QueryNode(Object value, String columnName, QuerySpecialOperate querySpecialOperate) {
        this.value = value;
        this.columnName = columnName;
        this.querySpecialOperate = querySpecialOperate;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public QuerySpecialOperate getQuerySpecialOperate() {
        return querySpecialOperate;
    }

    public void setQuerySpecialOperate(QuerySpecialOperate querySpecialOperate) {
        this.querySpecialOperate = querySpecialOperate;
    }

    public StringBuilder generateHql() {
        return new StringBuilder(String.format(format, columnName, querySpecialOperate.getValue(), columnName));
    }

}
