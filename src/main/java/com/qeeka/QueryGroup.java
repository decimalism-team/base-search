package com.qeeka;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.qeeka.deserializer.QueryGroupJsonDeserializer;
import com.qeeka.operate.QueryLinkOperate;
import com.qeeka.operate.QueryOrder;
import com.qeeka.operate.QueryResultType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by neal.xu on 7/31 0031.
 */
@JsonDeserialize(using = QueryGroupJsonDeserializer.class)
public class QueryGroup {

    private List<QueryHandle> queryHandleList = new LinkedList<>();
    private QueryResultType queryResultType = QueryResultType.LIST;
    private List<QuerySort> sortList = new LinkedList<>();

    public QueryGroup() {
    }

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

    public QueryGroup order(String column, QueryOrder queryOrder) {
        this.sortList.add(new QuerySort(column, queryOrder));
        return this;
    }

    public List<QueryHandle> getQueryHandleList() {
        return queryHandleList;
    }

    public void setQueryHandleList(List<QueryHandle> queryHandleList) {
        this.queryHandleList = queryHandleList;
    }

    public QueryResultType getQueryResultType() {
        return queryResultType;
    }

    public void setQueryResultType(QueryResultType queryResultType) {
        this.queryResultType = queryResultType;
    }

    public List<QuerySort> getSortList() {
        return sortList;
    }

    public void setSortList(List<QuerySort> sortList) {
        this.sortList = sortList;
    }
}
