package com.addressbook.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addressbook.entity.UserMst;

@Repository
public interface UserMstRepository extends JpaRepository<UserMst,Integer>{

    public UserMst findById(Integer id);

}
