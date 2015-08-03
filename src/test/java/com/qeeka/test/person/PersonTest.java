package com.qeeka.test.person;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.qeeka.QueryGroup;
import com.qeeka.SimpleQuery;
import com.qeeka.SimpleQueryParser;
import com.qeeka.test.SpringTestWithDB;
import com.qeeka.test.domain.Person;
import com.qeeka.test.service.PersonService;
import com.qeeka.util.QueryJSONBinder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Neal on 2015/7/27.
 */
public class PersonTest extends SpringTestWithDB {


    @Autowired
    private PersonService personService;
    @Autowired
    private SimpleQueryParser queryParser;

    @Test
    @DatabaseSetup("/PersonData.xml")
    @Transactional
    public void testRemove() {
        personService.remove(1);
    }

    @Test
    @DatabaseSetup("/PersonData.xml")
    public void testSearch() {
        QueryGroup group = new QueryGroup("name", "neal1").or("status", 1).and("type", 1);
        SimpleQuery query = queryParser.parse(group);
        List<Person> result = personService.search(query);
        for (Person person : result) {
            System.out.println(QueryJSONBinder.binder(Person.class).toJSON(person));
        }

    }

}
