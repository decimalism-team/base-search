package com.qeeka;

import com.qeeka.operate.QueryLinkOperate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class QueryGroup {

    private List<QueryHandle> queryHandleList = new LinkedList<QueryHandle>();

    public QueryGroup(QueryNode node) {
        queryHandleList.add(node);
    }

    public QueryGroup(QueryGroup group) {
        for (QueryHandle handle : group.getQueryHandleList()) {
            queryHandleList.add(handle);
        }
    }

    public QueryGroup(String columnName, Object value) {
        queryHandleList.add(new QueryNode(columnName, value));
    }


    public QueryGroup and(QueryNode node) {
        queryHandleList.add(node);
        queryHandleList.add(new QueryOperateNode(QueryLinkOperate.AND));
        return this;
    }

    public QueryGroup and(String columnName, Object value) {
        queryHandleList.add(new QueryNode(columnName, value));
        queryHandleList.add(new QueryOperateNode(QueryLinkOperate.AND));
        return this;
    }

    public QueryGroup or(QueryNode node) {
        queryHandleList.add(node);
        queryHandleList.add(new QueryOperateNode(QueryLinkOperate.OR));
        return this;
    }

    public QueryGroup or(String columnName, Object value) {
        queryHandleList.add(new QueryNode(columnName, value));
        queryHandleList.add(new QueryOperateNode(QueryLinkOperate.OR));
        return this;
    }

    public QueryGroup and(QueryGroup group) {
        for (QueryHandle handle : group.getQueryHandleList()) {
            queryHandleList.add(handle);
        }
        queryHandleList.add(new QueryOperateNode(QueryLinkOperate.AND));
        return this;
    }

    public QueryGroup or(QueryGroup group) {
        for (QueryHandle handle : group.getQueryHandleList()) {
            queryHandleList.add(handle);
        }
        queryHandleList.add(new QueryOperateNode(QueryLinkOperate.OR));
        return this;
    }

    public List<QueryHandle> getQueryHandleList() {
        return queryHandleList;
    }
}
