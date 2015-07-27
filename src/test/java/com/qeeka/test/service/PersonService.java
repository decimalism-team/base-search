package com.qeeka.test.service;

import com.qeeka.test.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Neal on 2015/7/27.
 */
@Service
@Transactional
public class PersonService {
    @PersistenceContext
    private EntityManager entityManager;

    public void remove(int personId) {
        Person person = entityManager.find(Person.class, personId);
        this.entityManager.remove(person);
    }
}
