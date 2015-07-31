package com.qeeka;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class SimpleQueryParser {
    public String parse(QueryGroup group) {
        Queue<QueryHandle> nodeStack = group.getNodeStack();
        if (nodeStack == null || nodeStack.isEmpty()) {
            throw new IllegalArgumentException("Can't parse query!");
        }

        QueryNode node;
        QueryOperateNode operateNode;
        while (!nodeStack.isEmpty()) {
            QueryHandle queryHandle = nodeStack.remove();
            if(queryHandle instanceof QueryNode){

            }

            if(queryHandle instanceof  QueryOperateNode){

            }

        }

        return "";
    }
}
