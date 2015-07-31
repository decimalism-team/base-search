package com.qeeka;

import com.qeeka.operate.QueryOperate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class QueryGroup {

    private List<QueryHandle> queryHandleList = new ArrayList<QueryHandle>();

    public QueryGroup(QueryNode node) {
        queryHandleList.add(node);
    }

    public QueryGroup(String columnName, Object value) {
        queryHandleList.add(new QueryNode(columnName, value));
    }


    public QueryGroup and(QueryNode node) {
        queryHandleList.add(node);
        queryHandleList.add(new QueryOperateNode(QueryOperate.AND));
        return this;
    }

    public QueryGroup and(String columnName, Object value) {
        queryHandleList.add(new QueryNode(columnName, value));
        queryHandleList.add(new QueryOperateNode(QueryOperate.AND));
        return this;
    }

    public QueryGroup or(QueryNode node) {
        queryHandleList.add(node);
        queryHandleList.add(new QueryOperateNode(QueryOperate.OR));
        return this;
    }

    public QueryGroup or(String columnName, Object value) {
        queryHandleList.add(new QueryNode(columnName, value));
        queryHandleList.add(new QueryOperateNode(QueryOperate.OR));
        return this;
    }

    public QueryGroup and(QueryGroup group) {
        for (QueryHandle handle : group.getQueryHandleList()) {
            queryHandleList.add(handle);
        }
        queryHandleList.add(new QueryOperateNode(QueryOperate.AND));
        return this;
    }

    public QueryGroup or(QueryGroup group) {
        for (QueryHandle handle : group.getQueryHandleList()) {
            queryHandleList.add(handle);
        }
        queryHandleList.add(new QueryOperateNode(QueryOperate.OR));
        return this;
    }

    public List<QueryHandle> getQueryHandleList() {
        return queryHandleList;
    }
}
