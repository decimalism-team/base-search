package com.qeeka.test.service;

import com.qeeka.QueryGroup;
import com.qeeka.SimpleQueryParser;
import com.qeeka.test.domain.Person;
import com.qeeka.test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Neal on 2015/7/27.
 */
@Service
@Transactional
public class PersonService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private SimpleQueryParser queryParser;


    public void remove(int personId) {
        Person person = entityManager.find(Person.class, personId);
        this.entityManager.remove(person);
    }


    public List<Person> search(QueryGroup group) {
        return repository.search(queryParser.parse(group));
    }
}
