package com.qeeka.test;

import com.qeeka.QueryGroup;
import com.qeeka.QueryNode;
import com.qeeka.SimpleQuery;
import com.qeeka.SimpleQueryParser;
import com.qeeka.operate.QueryOperate;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class ParserTest {
    SimpleQueryParser parser;

    @Before
    public void init() {
        parser = new SimpleQueryParser();
    }

    @Test
    public void testSimpleEquals() {
        // (c = :c2 AND (a = :a0 AND b = :b1)))
        QueryGroup group = new QueryGroup(new QueryNode("a", 1)).and(new QueryNode("b", 2)).and("c", 3);
        System.out.println(parser.parse(group).getHql());
    }

    @Test
    public void testLike() {
        // (a like :a AND b <> :b)
        QueryGroup group = new QueryGroup(new QueryNode("a", 1, QueryOperate.LIKE)).and(new QueryNode("b", 2, QueryOperate.NO_EQUALS));
        System.out.println(parser.parse(group).getHql());
    }

    @Test
    public void tet2() {
        // (d = :d3 AND (b = :b2 AND (c = :c0 AND a = :a1)))))
        QueryGroup group = new QueryGroup(new QueryNode("d", 4)).
                and(
                        new QueryGroup(new QueryNode("c", 3)).and(new QueryNode("a", 1)).and(new QueryNode("b", 2))
                );
        System.out.println(parser.parse(group).getHql());
    }

    @Test
    public void test3() {
        // (a = 3 and b = 4 ) or c = 5
        QueryGroup group = new QueryGroup("c", 5).or(
                new QueryGroup("a", 3).and("b", 4).or("f", 9)
        );
        System.out.println(parser.parse(group).getHql());
    }

    @Test
    public void test4() {
        // (a=3 and b=4) or (c=3 or d=5)
        QueryGroup group = new QueryGroup(
                new QueryGroup("a", 3).and("b", 4)
        ).or(
                new QueryGroup("c", 3).or("d", 5)
        );
        System.out.println(parser.parse(group).getHql());
    }

    @Test
    public void testSimpleColumnParameters() {
        QueryGroup group = new QueryGroup("a", 30).and("b", 10).or("a", 20);
        SimpleQuery simpleQuery = parser.parse(group);
        System.out.println(simpleQuery.getHql());
        for (Map.Entry<String, Object> entry : simpleQuery.getParameters().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
