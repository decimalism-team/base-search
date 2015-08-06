package com.qeeka.test.parse;

import com.qeeka.QueryGroup;
import com.qeeka.QueryNode;
import com.qeeka.SimpleQuery;
import com.qeeka.SimpleQueryParser;
import com.qeeka.operate.QueryOperate;
import com.qeeka.util.QueryJSONBinder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void sampleTest() {
        QueryGroup group = new QueryGroup("a", 1).and("b", 2).or("c", 3);
        Assert.assertEquals(parser.parse(group).getHql(), "(c = :c2 OR (a = :a0 AND b = :b1)))");
    }

    @Test
    public void testSimpleEquals() {
        QueryGroup group = new QueryGroup(new QueryNode("a", 1)).and(new QueryNode("b", 2)).and("c", 3);
        Assert.assertEquals(parser.parse(group).getHql(), "(c = :c2 AND (a = :a0 AND b = :b1)))");
    }

    @Test
    public void testLike() {
        QueryGroup group = new QueryGroup(new QueryNode("a", 1, QueryOperate.LIKE)).and(new QueryNode("b", 2, QueryOperate.NO_EQUALS));
        Assert.assertEquals(parser.parse(group).getHql(), "(a LIKE :a0 AND b <> :b1)");
    }

    @Test
    public void tet2() {
        QueryGroup group = new QueryGroup(new QueryNode("d", 4)).
                and(
                        new QueryGroup(new QueryNode("c", 3)).and(new QueryNode("a", 1)).and(new QueryNode("b", 2))
                );
        Assert.assertEquals(parser.parse(group).getHql(), "(d = :d3 AND (b = :b2 AND (c = :c0 AND a = :a1)))))");
    }

    @Test
    public void test3() {
        QueryGroup group = new QueryGroup("c", 5).or(
                new QueryGroup("a", 3).and("b", 4).or("f", 9)
        );
        Assert.assertEquals(parser.parse(group).getHql(), "(c = :c3 OR (f = :f2 OR (a = :a0 AND b = :b1)))))");
    }

    @Test
    public void test4() {
        QueryGroup group = new QueryGroup(
                new QueryGroup("a", 3).and("b", 4)
        ).or(
                new QueryGroup("c", 3).or("d", 5)
        );
        Assert.assertEquals(parser.parse(group).getHql(), "((a = :a0 AND b = :b1) OR (c = :c2 OR d = :d3))");
    }

    @Test
    public void testSimpleColumnParameters() {
        QueryGroup group = new QueryGroup("a", 30).and("b", 10).or("a", 20);
        SimpleQuery simpleQuery = parser.parse(group);
        Assert.assertEquals(simpleQuery.getHql(), "(a = :a2 OR (a = :a0 AND b = :b1)))");
        Assert.assertTrue(simpleQuery.getParameters().size() == 3);
        Assert.assertEquals(simpleQuery.getParameters().get("a0"), 30);
        Assert.assertEquals(simpleQuery.getParameters().get("b1"), 10);
        Assert.assertEquals(simpleQuery.getParameters().get("a2"), 20);
    }

    @Test
    public void testJsonTransaction() {
        QueryGroup group = new QueryGroup(
                new QueryGroup("a", 3).and("b", 4)
        ).or(
                new QueryGroup("c", 3).or("d", 5)
        );

        String s = QueryJSONBinder.binder(QueryGroup.class).toJSON(group);
        QueryGroup queryGroup = QueryJSONBinder.binder(QueryGroup.class).fromJSON(s);
        Assert.assertEquals(parser.parse(queryGroup).getHql(), "((a = :a0 AND b = :b1) OR (c = :c2 OR d = :d3))");
    }
}
