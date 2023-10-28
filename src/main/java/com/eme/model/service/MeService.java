package com.eme.model.service;

import com.eme.model.entity.Me;
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
public class MeService implements ServiceImpl<Me, Long>, Serializable {

    @PersistenceContext(unitName = "eme")
    private EntityManager entityManager;

    //-------INSERT------------------------------------------------------
    @Override
    @Transactional
    public Me save(Me me) throws Exception {
        entityManager.persist(me);
        return me;
    }

    //-------UPDATE------------------------------------------------------
    @Override
    @Transactional
    public Me edit(Me me) throws Exception {
        return entityManager.merge(me);
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    @Override
    public Me physicalRemove(Long id) throws Exception {
        Me me = entityManager.find(Me.class, id);
        entityManager.remove(me);
        return me;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    @Override
    @Transactional
    public Me logicalRemove(Long id) throws Exception {
        Me me = entityManager.find(Me.class, id);
        me.setStatus(Status.Deleted);
        return entityManager.merge(me);
    }

    //------SELECT-ALL---------------------------------------------------
    @Override
    public List<Me> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from myEntity oo");
        return query.getResultList();
    }

    //------SELECT-BY-ID-------------------------------------------------
    @Override
    public Me findById(Long id) throws Exception {
        return entityManager.find(Me.class, id);
    }

    //------SELECT-WITH-PAGINATION---------------------------------------
    @Override
    public List<Me> findAllPaging(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    //------GET-RECORD-COUNT---------------------------------------------
    @Override
    public Long getRecordCount() throws Exception {
        return null;
    }
}
