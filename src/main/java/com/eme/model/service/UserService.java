package com.eme.model.service;

import com.eme.controller.session.FacesSessionMap;
import com.eme.model.entity.MessageVO;
import com.eme.model.entity.User;
import com.eme.model.entity.enums.Role;
import com.eme.model.entity.enums.Status;
import com.eme.model.service.impl.ServiceImpl;
import jakarta.enterprise.context.*;
import jakarta.faces.context.FacesContext;
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
public class UserService implements ServiceImpl<User, String>, Serializable {


    @PersistenceContext(unitName = "eme")
    private EntityManager entityManager;


    //-------INSERT------------------------------------------------------
    @Override
    @Transactional
    public User save(User user) throws Exception {
        entityManager.persist(user);
        return user;
    }

    //-------UPDATE------------------------------------------------------
    @Override
    @Transactional
    public User edit(User user) throws Exception {
        return entityManager.merge(user);
    }

    //-------PHYSICAL-REMOVE---------------------------------------------
    @Override
    @Transactional
    public User physicalRemove(String username) throws Exception {
        User user = entityManager.find(User.class, username);
        entityManager.remove(user);
        return user;
    }

    //-------LOGICAL-REMOVE----------------------------------------------
    @Override
    @Transactional
    public User logicalRemove(String username) throws Exception {
        User user = entityManager.find(User.class, username);
        user.setStatus(Status.Deleted);
        return entityManager.merge(user);
    }

    //------SELECT-ALL---------------------------------------------------
    @Override
    public List<User> findAll() throws Exception {
        Query query = entityManager.createQuery("select oo from userEntity oo");
        return query.getResultList();
    }

    //------SELECT-BY-ID-------------------------------------------------
    @Override
    public User findById(String username) throws Exception {
        return entityManager.find(User.class, username);
    }

    //------SELECT-WITH-PAGINATION---------------------------------------
    @Override
    public List<User> findAllPaging(int pageNumber, int pageSize) throws Exception {
        return null;
    }

    //------GET-RECORD-COUNT---------------------------------------------
    @Override
    public Long getRecordCount() throws Exception {
        return null;
    }

    //------VALIDATE-USER-EXISTS-----------------------------------------
    public User userValidation(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        params.put("status", Status.Active);
        params.put("role", Role.ME);
        Query query = entityManager.createNamedQuery("user.validate");
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        List<User> result = query.getResultList();
        if (!result.isEmpty()) {
             return user = result.get(0);
        } else {
            return null;
        }
    }
}
