package com.eme.model.service;


import com.eme.model.entity.Book;
import com.eme.model.entity.Me;
import com.eme.model.entity.User;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.Status;
import com.eme.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class BookService implements ServiceImpl<Book, Long>,Serializable {

    @PersistenceContext(unitName = "eme")
    private EntityManager entityManager;

    Map<String, Object> params = new HashMap<>();

    //-------INSERT------------------------------------------------------
    @Override
    @Transactional
    public Book save(Book book) throws Exception {
        entityManager.persist(book);
        return book;
    }

    //-------UPDATE------------------------------------------------------
    @Override
    @Transactional
    public Book edit(Book book) throws Exception {
        return entityManager.merge(book);
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    @Override
    @Transactional
    public Book physicalRemove(Long id) throws Exception {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        return book;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    @Override
    @Transactional
    public Book logicalRemove(Long id) throws Exception {
        Book book = entityManager.find(Book.class, id);
        book.setStatus(Status.Deleted);
        return entityManager.merge(book);
    }

    //------SELECT-ALL---------------------------------------------------
    @Override
    public List<Book> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from bookEntity oo");
        return query.getResultList();
    }

    //------SELECT-BY-ID-------------------------------------------------
    @Override
    public Book findById(Long id) throws Exception {
        return entityManager.find(Book.class, id);
    }

    //------SELECT-WITH-PAGINATION---------------------------------------
    @Override
    public List<Book> findAllPaging(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    //------GET-RECORD-COUNT---------------------------------------------
    @Override
    public Long getRecordCount() throws Exception {
        return null;
    }

    //------SELECT-ALL-ACTIVE---------------------------------------------
    public List<Book> findAllActive() throws Exception {
        params.put("status", Status.Active);
        Query query = entityManager.createNamedQuery("book.findAllActive");
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        List<Book> result = query.getResultList();
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

}
