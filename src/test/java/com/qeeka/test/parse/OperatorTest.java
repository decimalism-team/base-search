package com.qeeka.test.parse;

import com.qeeka.QueryGroup;
import com.qeeka.SimpleQueryParser;
import org.junit.Test;

import static com.qeeka.operate.QueryOperate.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Neal on 8/6 0006.
 */
public class OperatorTest {

    SimpleQueryParser parser = new SimpleQueryParser();

    @Test
    public void testEquals() {
        QueryGroup group = new QueryGroup("a", "test1");
        assertEquals(parser.parse(group).getHql(), "a = :a0");
        group = new QueryGroup("a", "test2", EQUALS);
        assertEquals(parser.parse(group).getHql(), "a = :a0");
    }

    @Test
    public void testNoEquals() {
        QueryGroup group = new QueryGroup("a", "test1", NO_EQUALS);
        assertEquals(parser.parse(group).getHql(), "a <> :a0");
    }

    @Test
    public void testIsNull() {
        QueryGroup group = new QueryGroup("a", IS_NULL);
        assertEquals(parser.parse(group).getHql(), "a IS NULL");

        group = new QueryGroup("a", "test1").or("b", IS_NULL);
        assertEquals(parser.parse(group).getHql(), "(a = :a0 OR b IS NULL)");
    }

    @Test
    public void testNotNull() {
        QueryGroup group = new QueryGroup("a", IS_NOT_NULL);
        assertEquals(parser.parse(group).getHql(), "a IS NOT NULL");

        group = new QueryGroup("a", "test1").or("b", IS_NOT_NULL);
        assertEquals(parser.parse(group).getHql(), "(a = :a0 OR b IS NOT NULL)");
    }

    @Test
    public void testColumnCompare() {
        QueryGroup group = new QueryGroup("a", "b", COLUMN_EQUALS);
        assertEquals(parser.parse(group).getHql(), "a = b");

        group = new QueryGroup("a", "b", COLUMN_NO_EQUALS);
        assertEquals(parser.parse(group).getHql(), "a <> b");
    }

    @Test
    public void testLike() {
        QueryGroup group = new QueryGroup("a", "%s%", LIKE);
        assertEquals(parser.parse(group).getHql(), "a LIKE :a0");
    }

    @Test
    public void testNoLike() {
        QueryGroup group = new QueryGroup("a", "%s%", NOT_LIKE);
        assertEquals(parser.parse(group).getHql(), "a NOT LIKE :a0");
    }

    @Test
    public void testLess() {
        QueryGroup group = new QueryGroup("a", 1, LESS_THAN).and("b", 2, LESS_THAN_EQUALS);
        assertEquals(parser.parse(group).getHql(), "(a < :a0 AND b <= :b1)");
    }

    @Test
    public void testGreat() {
        QueryGroup group = new QueryGroup("a", 1, GREAT_THAN).and("b", 2, GREAT_THAN_EQUALS);
        assertEquals(parser.parse(group).getHql(), "(a > :a0 AND b >= :b1)");
    }
}
