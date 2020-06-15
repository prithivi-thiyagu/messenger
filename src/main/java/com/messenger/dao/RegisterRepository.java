package com.messenger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.pojo.UserDetails;

public interface RegisterRepository extends JpaRepository<UserDetails, String>{

}
