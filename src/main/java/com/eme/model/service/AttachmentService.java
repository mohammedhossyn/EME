package com.eme.model.service;

import com.eme.model.entity.Attachment;
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
@ConversationScoped
@Named
public class AttachmentService implements ServiceImpl<Attachment, Long>,Serializable {

    @PersistenceContext(unitName = "eme")
    private EntityManager entityManager;

    //-------INSERT------------------------------------------------------
    @Override
    @Transactional
    public Attachment save(Attachment attachment) throws Exception {
        entityManager.persist(attachment);
        return attachment;
    }

    //-------UPDATE------------------------------------------------------
    @Override
    @Transactional
    public Attachment edit(Attachment attachment) throws Exception {
        return entityManager.merge(attachment);
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    @Override
    @Transactional
    public Attachment physicalRemove(Long id) throws Exception {
        Attachment attachment = entityManager.find(Attachment.class, id);
        entityManager.remove(attachment);
        return attachment;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    @Override
    @Transactional
    public Attachment logicalRemove(Long id) throws Exception {
        Attachment attachment = entityManager.find(Attachment.class, id);
        attachment.setStatus(Status.Deleted);
        return entityManager.merge(attachment);
    }

    //------SELECT-ALL---------------------------------------------------
    @Override
    public List<Attachment> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from attachmentEntity oo");
        return query.getResultList();
    }

    //------SELECT-BY-ID-------------------------------------------------
    @Override
    public Attachment findById(Long id) throws Exception {
        return entityManager.find(Attachment.class, id);
    }

    //------SELECT-WITH-PAGINATION---------------------------------------
    @Override
    public List<Attachment> findAllPaging(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    //------GET-RECORD-COUNT---------------------------------------------
    @Override
    public Long getRecordCount() throws Exception {
        return null;
    }


}
