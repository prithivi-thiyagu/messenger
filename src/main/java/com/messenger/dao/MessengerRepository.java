package com.messenger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.messenger.pojo.Message;
import com.messenger.pojo.Senders;

public interface MessengerRepository extends JpaRepository<Senders, String> {


}
