package com.qeeka;

import com.qeeka.operate.QueryLinkOperate;

class QueryOperateNode extends QueryHandle {

    private QueryLinkOperate queryOperate = QueryLinkOperate.AND;

    public QueryOperateNode(QueryLinkOperate queryOperate) {
        this.queryOperate = queryOperate;
    }

    public QueryLinkOperate getQueryOperate() {
        return queryOperate;
    }

    public void setQueryOperate(QueryLinkOperate queryOperate) {
        this.queryOperate = queryOperate;
    }
}