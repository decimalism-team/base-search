# Easy generate hql Statement

You can generate hql by QueryGroup 

#Simple
```java
new QueryGroup("a", 1).and("b", 2) 
==> (a = :a0 AND b = :b1)
```
```java
new QueryGroup(new QueryNode("a", 1)).and(new QueryNode("b", 2)).and("c", 3) 
==>(c = :c2 AND (a = :a0 AND b = :b1)))
```

#Multi sample parameters generate
```java
new QueryGroup("a", 30).and("b", 10).or("a", 20)  ==>  (a = :a2 OR (a = :a0 AND b = :b1)))
```

#You can get hql or parameters with SimpleQuery
```java
QueryGroup group = new QueryGroup("a", 30).and("b", 10).or("a", 20);
SimpleQuery simpleQuery = parser.parse(group);
Assert.assertEquals(simpleQuery.getHql(), "(a = :a2 OR (a = :a0 AND b = :b1)))");
Assert.assertTrue(simpleQuery.getParameters().size() == 3);
Assert.assertEquals(simpleQuery.getParameters().get("a0"), 30);
Assert.assertEquals(simpleQuery.getParameters().get("b1"), 10);
Assert.assertEquals(simpleQuery.getParameters().get("a2"), 20);
```
