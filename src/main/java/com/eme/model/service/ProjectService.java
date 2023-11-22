package com.eme.model.service;

import com.eme.model.entity.Project;
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
public class ProjectService implements ServiceImpl<Project, Long>,Serializable {

    @PersistenceContext(unitName = "eme")
    private EntityManager entityManager;

    //-------INSERT------------------------------------------------------
    @Override
    @Transactional
    public Project save(Project project) throws Exception {
        entityManager.persist(project);
        return project;
    }

    //-------UPDATE------------------------------------------------------
    @Override
    @Transactional
    public Project edit(Project project) throws Exception {
        return entityManager.merge(project);
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    @Override
    @Transactional
    public Project physicalRemove(Long id) throws Exception {
        Project project = entityManager.find(Project.class, id);
        entityManager.remove(project);
        return project;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    @Override
    @Transactional
    public Project logicalRemove(Long id) throws Exception {
        Project project = entityManager.find(Project.class, id);
        project.setStatus(Status.Deleted);
        return entityManager.merge(project);
    }

    //------SELECT-ALL---------------------------------------------------
    @Override
    public List<Project> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from projectEntity oo");
        return query.getResultList();
    }

    //------SELECT-BY-ID-------------------------------------------------
    @Override
    public Project findById(Long id) throws Exception {
        return entityManager.find(Project.class, id);
    }

    //------SELECT-WITH-PAGINATION---------------------------------------
    @Override
    public List<Project> findAllPaging(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    //------GET-RECORD-COUNT---------------------------------------------
    @Override
    public Long getRecordCount() throws Exception {
        return null;
    }
}
