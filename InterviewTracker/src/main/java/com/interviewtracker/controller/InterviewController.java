package com.interviewtracker.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comman.AttendeeResponse;
import com.comman.SuccessfulReponse;
import com.interviewtracker.exceptions.ErrorDetails;
import com.interviewtracker.exceptions.ErrorReponse;
import com.interviewtracker.model.AttendeeDTO;
import com.interviewtracker.model.InterviewDTO;
import com.interviewtracker.service.InterviewService;

@RestController
public class InterviewController {

	@Autowired
    private InterviewService interviewService;

	private final static Logger LOGGER = LoggerFactory.getLogger(InterviewController.class);

	
	@PostMapping(path = "/api/interview/create")
	public ResponseEntity<?> createUser(HttpServletRequest request,  @RequestBody InterviewDTO employeeProfileDetails)
			throws IOException {
		try {
			interviewService.save(employeeProfileDetails);
			return ResponseEntity.ok(new SuccessfulReponse("Interview Created Succesfully"));
		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);
			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", "Error " + e.getMessage(), null)));
		}
	}

	@GetMapping(path = "/api/interview/getAllInterviews")
	public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
		try {
			List<InterviewDTO> uersList = interviewService.findAll();

			return ResponseEntity.ok(new SuccessfulReponse(uersList));

		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}

	@PostMapping(path = "/api/interview/update")
	public ResponseEntity<?> updateUser(HttpServletRequest request, @Valid @RequestBody InterviewDTO userDtls) {
		try {
			interviewService.updateInterview(userDtls);

			return ResponseEntity.ok(new SuccessfulReponse("Interview updated Succesfully"));

		} catch (Exception e) {
			LOGGER.error(InterviewController.class.getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}

	@DeleteMapping(path = "/api/interview/delete/{id}")
	public ResponseEntity<?> deleteUser(HttpServletRequest request, @PathVariable("id") String id) {
		try {
			interviewService.delete(id);

			return ResponseEntity.ok(new SuccessfulReponse("Interview deleted Succesfully"));

		} catch (Exception e) {
			LOGGER.error(InterviewController.class.getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}

	@GetMapping(path = "/api/interview/search/{string}")
	public ResponseEntity<?> Search(HttpServletRequest request, @PathVariable("string") String string) {
		try {
			List<InterviewDTO> uersList = interviewService.search(string);

			return ResponseEntity.ok(new SuccessfulReponse(uersList));

		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}
	
	@PostMapping(path = "/api/attendee/add")
	public ResponseEntity<?> addAttendee(HttpServletRequest request, @Valid @RequestBody AttendeeDTO employeeProfileDetails)
			throws IOException {
		try {
			interviewService.addAttendee(employeeProfileDetails);
			return ResponseEntity.ok(new SuccessfulReponse("Attendee Added Succesfully"));
		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);
			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", "Error " + e.getMessage(), null)));
		}
	}
	@GetMapping(path = "/api/attendee/getAllAttendees/{interviewId}")
	public ResponseEntity<?> getAllAttendees(HttpServletRequest request, @PathVariable("interviewId") String interviewId) {
		try {
			AttendeeResponse uersList = interviewService.getAllAttendee(interviewId);

			return ResponseEntity.ok(new SuccessfulReponse(uersList.getListUser()));

		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}
}
