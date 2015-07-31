package com.qeeka;

import java.util.List;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class SimpleQueryParser {
    public String parse(QueryGroup group) {
        List<QueryHandle> nodeStack = group.getQueryHandleList();

        if (nodeStack == null || nodeStack.isEmpty()) {
            throw new IllegalArgumentException("Can't parse query!");
        }

        StringBuilder whereHql = new StringBuilder();

        QueryOperateNode operateNode;
        boolean beginParse = false;

        for (int i = 0; i < nodeStack.size(); ) {
            QueryHandle currentNode = nodeStack.get(i);
            if (currentNode instanceof QueryOperateNode) {
                operateNode = (QueryOperateNode) currentNode;
                QueryHandle preNode = nodeStack.get(i - 1);
                if (!beginParse) {
                    QueryHandle prePreNode = nodeStack.get(i - 2);
                    whereHql.append('(')
                            .append(prePreNode.generateHql()).append(operateNode.getQueryOperate().getValue())
                            .append(preNode.generateHql())
                            .append(')');
                    nodeStack.remove(i);
                    nodeStack.remove(i - 1);
                    nodeStack.remove(i - 2);
                    i = i - 2;
                    beginParse = true;
                } else {
                    whereHql.insert(0, '(')
                            .append(operateNode.getQueryOperate().getValue())
                            .append(preNode.generateHql())
                            .append(')');
                    nodeStack.remove(i);
                    nodeStack.remove(i - 1);
                    i = i - 1;
                }
            } else {
                i++;
            }
        }

        return whereHql.toString();
    }
}
