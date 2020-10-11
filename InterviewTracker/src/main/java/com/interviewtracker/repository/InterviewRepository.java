package com.interviewtracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.interviewtracker.model.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, String> {

    List<Interview> findByNameContaining(String q);
    
    @Query("FROM Interview WHERE interviewerName = ?1")
	List<Interview> findByInterviewerName(String InterviewerName);

}
