package com.qeeka.test.person;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.qeeka.QueryGroup;
import com.qeeka.operate.QueryOperate;
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

    @Test
    @DatabaseSetup("/PersonData.xml")
    @Transactional
    public void testRemove() {
        personService.remove(1);
    }

    @Test
    @DatabaseSetup("/PersonData.xml")
    public void testSearch() {
        QueryGroup group = new QueryGroup("name", "%n%",QueryOperate.LIKE).and("type", 1).and("name", "name", QueryOperate.COLUMN_COMPARE);
        List<Person> result = personService.search(group);
        for (Person person : result) {
            System.out.println(QueryJSONBinder.binder(Person.class).toJSON(person));
        }

    }

}
