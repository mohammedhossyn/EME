package com.eme.model.service;

import com.eme.model.entity.Person;
import com.eme.model.entity.enums.Status;
import com.eme.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
@RequestScoped
@Named
public class PersonService implements ServiceImpl<Person, Long>,Serializable {

    @PersistenceContext(unitName = "eme")
    private EntityManager entityManager;

    //-------INSERT------------------------------------------------------
    @Override
    @Transactional
    public Person save(Person person) throws Exception {
        entityManager.persist(person);
        return person;
    }

    //-------UPDATE------------------------------------------------------
    @Override
    @Transactional
    public Person edit(Person person) throws Exception {
        return entityManager.merge(person);
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    @Override
    public Person physicalRemove(Long id) throws Exception {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
        return person;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    @Override
    @Transactional
    public Person logicalRemove(Long id) throws Exception {
        Person person = entityManager.find(Person.class, id);
        person.setStatus(Status.Deleted);
        return entityManager.merge(person);
    }

    //------SELECT-ALL---------------------------------------------------
    @Override
    public List<Person> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from personEntity oo");
        return query.getResultList();
    }

    //------SELECT-BY-ID-------------------------------------------------
    @Override
    public Person findById(Long id) throws Exception {
        return entityManager.find(Person.class, id);
    }

    //------SELECT-WITH-PAGINATION---------------------------------------
    @Override
    public List<Person> findAllPaging(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    //------GET-RECORD-COUNT---------------------------------------------
    @Override
    public Long getRecordCount() throws Exception {
        return null;
    }
}
