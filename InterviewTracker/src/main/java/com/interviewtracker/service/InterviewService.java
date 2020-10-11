package com.interviewtracker.service;

import java.util.List;

import com.comman.AttendeeResponse;
import com.interviewtracker.model.AttendeeDTO;
import com.interviewtracker.model.InterviewDTO;

public interface InterviewService {

	List<InterviewDTO> findAll();

    List<InterviewDTO> search(String q);

    InterviewDTO findOne(String id);

    void save(InterviewDTO contact);

    void delete(String id);

	void updateInterview(InterviewDTO userDtls);
	void addAttendee(AttendeeDTO userDtls);
	
	AttendeeResponse getAllAttendee(String userDtls);
}
