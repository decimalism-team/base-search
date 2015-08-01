package com.qeeka;

import java.util.List;
import java.util.Stack;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class SimpleQueryParser {
    public String parse(QueryGroup group) {
        List<QueryHandle> nodeStack = group.getQueryHandleList();

        if (nodeStack == null || nodeStack.isEmpty()) {
            throw new IllegalArgumentException("Can't parse query!");
        }

        Stack<CharSequence> hqlParts = new Stack<CharSequence>();

        QueryHandle node1 = null;
        QueryHandle node2 = null;

        int lastScanIndex = 0;

        int i = 0;
        while (!nodeStack.isEmpty()) {
            QueryHandle currentNode = nodeStack.get(i);
            if (currentNode instanceof QueryOperateNode) {
                QueryOperateNode operateNode = (QueryOperateNode) currentNode;

                if (i - 2 >= 0) {
                    node2 = nodeStack.get(i - 2);
                }
                if (i - 1 >= 0) {
                    node1 = nodeStack.get(i - 1);
                }

                //when express like a b +
                if (lastScanIndex <= i - 2 && node1 instanceof QueryNode && node2 instanceof QueryNode) {
                    hqlParts.push(new StringBuilder().append('(')
                            .append(node2.generateHql()).append(operateNode.getQueryOperate().getValue())
                            .append(node1.generateHql())
                            .append(')'));
                    //remove handle node
                    nodeStack.remove(i--);
                    nodeStack.remove(i--);
                    nodeStack.remove(i);
                    lastScanIndex = i;
                } else {
                    CharSequence popNode = hqlParts.pop();

                    if (!hqlParts.isEmpty()) {
                        CharSequence popNode2 = hqlParts.pop();
                        hqlParts.push(
                                new StringBuilder().append('(').append(popNode2)
                                        .append(operateNode.getQueryOperate().getValue())
                                        .append(popNode).append(')')
                        );
                        //remove operate node
                        nodeStack.remove(i--);
                    } else {
                        hqlParts.push(
                                new StringBuilder('(').append('(').append(node1.generateHql())
                                        .append(operateNode.getQueryOperate().getValue())
                                        .append(popNode).append(')').append(')')
                        );
                        //remove operate node and query node
                        nodeStack.remove(i--);
                        nodeStack.remove(i);
                    }
                }
            } else {
                i++;
            }
        }
        return hqlParts.pop().toString();
    }
}
