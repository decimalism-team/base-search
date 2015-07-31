package com.qeeka.test;

import com.qeeka.QueryGroup;
import com.qeeka.QueryNode;
import com.qeeka.SimpleQueryParser;
import org.junit.Test;

/**
 * Created by neal.xu on 7/31 0031.
 */
public class ParserTest {
    @Test
    public void test1() {
        // a=1 and b=2 and c=3
        QueryGroup group = new QueryGroup(new QueryNode("a", 1)).and(new QueryNode("b", 2));
        SimpleQueryParser parser = new SimpleQueryParser();
        System.out.println(parser.parse(group));
    }

    @Test
    public void tet2() {
        // e=5 and ((a=1 and b=2) or c=3) and d=4)
        QueryGroup group = new QueryGroup(new QueryNode("d", 4)).
                and(
                        new QueryGroup(new QueryNode("c", 3)).and(new QueryNode("a", 1)).and(new QueryNode("b", 2))
                );
        SimpleQueryParser parser = new SimpleQueryParser();
        System.out.println(parser.parse(group));
    }

    @Test
    public void test3() {
        // (a = 3 and b = 4 ) or c = 5
        QueryGroup group = new QueryGroup("c", 5).or(
                new QueryGroup("a", 3).and("b", 4).or("f", 9)
        );
        SimpleQueryParser parser = new SimpleQueryParser();
        System.out.println(parser.parse(group));
    }
}
