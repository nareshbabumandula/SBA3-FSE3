package com.interviewtracker.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interviewtracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //List<User> findByFirstNameContaining(String q);

}