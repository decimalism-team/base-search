package com.qeeka;

import com.qeeka.operate.QueryOperate;

class QueryOperateNode extends QueryHandle {

    private QueryOperate queryOperate = QueryOperate.AND;

    public QueryOperateNode(QueryOperate queryOperate) {
        this.queryOperate = queryOperate;
    }

    public QueryOperate getQueryOperate() {
        return queryOperate;
    }

    public void setQueryOperate(QueryOperate queryOperate) {
        this.queryOperate = queryOperate;
    }
}