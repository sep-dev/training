package com.addressbook.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addressbook.entity.UserMst;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager manager;

    @SuppressWarnings("unchecked")
    @Transactional
    public List<UserMst> getAll(){
        return (List<UserMst>)manager.createQuery("form User").getResultList();
    }
}
