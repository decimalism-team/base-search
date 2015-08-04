package com.qeeka;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class SimpleQueryParser {
    private static final String COLUMN_FORMAT = "%s %s :%s";


    public SimpleQuery parse(QueryGroup group) {
        List<QueryHandle> nodeStack = group.getQueryHandleList();

        if (nodeStack == null || nodeStack.isEmpty()) {
            throw new IllegalArgumentException("Can't parse query!");
        }

        SimpleQuery simpleQuery = new SimpleQuery();

        Stack<CharSequence> hqlParts = new Stack<>();

        QueryHandle node1 = null;
        QueryHandle node2 = null;

        int lastScanIndex = 0;

        int index = 0;
        while (!nodeStack.isEmpty()) {
            QueryHandle currentNode = nodeStack.get(index);
            if (currentNode instanceof QueryOperateNode) {
                QueryOperateNode operateNode = (QueryOperateNode) currentNode;

                if (index - 2 >= 0) {
                    node2 = nodeStack.get(index - 2);
                }
                if (index - 1 >= 0) {
                    node1 = nodeStack.get(index - 1);
                }

                //when express like a b +
                if (lastScanIndex <= index - 2 && node1 instanceof QueryNode && node2 instanceof QueryNode) {
                    hqlParts.push(new StringBuilder().append('(')
                            .append(generateParameterHql(node2, simpleQuery.getParameters())).append(operateNode.getQueryLinkOperate().getValue())
                            .append(generateParameterHql(node1, simpleQuery.getParameters()))
                            .append(')'));
                    //remove handle node
                    nodeStack.remove(index--);
                    nodeStack.remove(index--);
                    nodeStack.remove(index);
                    lastScanIndex = index;
                } else {
                    CharSequence popNode = hqlParts.pop();

                    if (!hqlParts.isEmpty()) {
                        CharSequence popNode2 = hqlParts.pop();
                        hqlParts.push(
                                new StringBuilder().append('(').append(popNode2)
                                        .append(operateNode.getQueryLinkOperate().getValue())
                                        .append(popNode).append(')')
                        );
                        //remove operate node
                        nodeStack.remove(index--);
                    } else {
                        hqlParts.push(
                                new StringBuilder('(').append('(').append(generateParameterHql(node1, simpleQuery.getParameters()))
                                        .append(operateNode.getQueryLinkOperate().getValue())
                                        .append(popNode).append(')').append(')')
                        );
                        //remove operate node and query node
                        nodeStack.remove(index--);
                        nodeStack.remove(index);
                    }
                }
            } else {
                index++;
            }
        }
        simpleQuery.setHql(hqlParts.pop().toString());
        return simpleQuery;
    }

    private static String generateParameterHql(QueryHandle handle, Map<String, Object> parameters) {
        if (handle instanceof QueryNode) {
            QueryNode node = (QueryNode) handle;
            String tempParameterName = new StringBuilder(node.getColumnName()).append(parameters.size()).toString();
            parameters.put(tempParameterName, node.getValue());
            return String.format(COLUMN_FORMAT, node.getColumnName(), node.getQueryOperate(), tempParameterName);
        }
        return "";
    }
}
