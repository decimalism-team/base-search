package com.qeeka;

import com.qeeka.operate.QueryOperate;

class QueryOperateNode implements QueryHandle {

    private QueryOperate queryOperate = QueryOperate.AND;

    public QueryOperateNode(QueryOperate queryOperate) {
        this.queryOperate = queryOperate;
    }
}