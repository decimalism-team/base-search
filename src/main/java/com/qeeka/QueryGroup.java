package com.qeeka;

import com.qeeka.operate.QueryOperate;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class QueryGroup implements QueryHandle{

    private Queue<QueryHandle> nodeStack = new LinkedList<QueryHandle>();

    public QueryGroup(QueryNode node) {
        nodeStack.add(node);
    }

    public void and(QueryNode node) {
        nodeStack.add(node);
        nodeStack.add(new QueryOperateNode(QueryOperate.AND));
    }

    public void or(QueryNode node) {
        nodeStack.add(node);
        nodeStack.add(new QueryOperateNode(QueryOperate.OR));
    }

    public void and(QueryGroup group) {
        nodeStack.add(group);
        nodeStack.add(new QueryOperateNode(QueryOperate.AND));
    }

    public void or(QueryGroup group) {
        nodeStack.add(group);
        nodeStack.add(new QueryOperateNode(QueryOperate.OR));
    }

    public Queue<QueryHandle> getNodeStack() {
        return nodeStack;
    }

    public void setNodeStack(Queue<QueryHandle> nodeStack) {
        this.nodeStack = nodeStack;
    }
}
