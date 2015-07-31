package com.qeeka;

import com.qeeka.operate.QuerySpecialOperate;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class QueryNode implements QueryHandle{
    private String columnName;
    private String value;
    private QuerySpecialOperate querySpecialOperate = QuerySpecialOperate.EQUALS;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QuerySpecialOperate getQuerySpecialOperate() {
        return querySpecialOperate;
    }

    public void setQuerySpecialOperate(QuerySpecialOperate querySpecialOperate) {
        this.querySpecialOperate = querySpecialOperate;
    }
}
