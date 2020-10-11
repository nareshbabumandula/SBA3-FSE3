package com.interviewtracker.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.comman.AttendeeResponse;
import com.comman.InterviewAssembler;
import com.comman.UserAssembler;
import com.interviewtracker.exceptions.ValidationError;
import com.interviewtracker.exceptions.ValidationException;
import com.interviewtracker.model.AttendeeDTO;
import com.interviewtracker.model.Interview;
import com.interviewtracker.model.InterviewDTO;
import com.interviewtracker.model.User;
import com.interviewtracker.repository.InterviewRepository;
import com.interviewtracker.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepository;

	@Autowired
	private UserRepository userRepository;

	private List<Interview> getCollectionFromIteralbe(Iterable<Interview> itr) {

		List<Interview> cltn = new ArrayList<Interview>();
		for (Interview t : itr)
			cltn.add(t);
		return cltn;
	}

	@Override
	public List<InterviewDTO> findAll() {
		InterviewAssembler userAssembler = new InterviewAssembler();
		return userAssembler.converToListDto(getCollectionFromIteralbe(interviewRepository.findAll()));
	}

	@Override
	public List<InterviewDTO> search(String q) {
		InterviewAssembler userAssembler = new InterviewAssembler();
		List<Interview> interviws = interviewRepository.findByNameContaining(q);
		if (interviws != null && !interviws.isEmpty()) {
			return userAssembler.converToListDto(interviewRepository.findByNameContaining(q));
		} else {
			return userAssembler.converToListDto(interviewRepository.findByInterviewerName(q));
		}

	}

	@Override
	public InterviewDTO findOne(String id) {
		InterviewAssembler userAssembler = new InterviewAssembler();
		return userAssembler.converToDto(interviewRepository.findOne(id));
	}

	@Override
	public void save(InterviewDTO contact) {
		contact.setInterviewId(String.valueOf(new Date().getTime()).substring(1));
		validateInterviewDTO(contact);
		InterviewAssembler userAssembler = new InterviewAssembler();
		interviewRepository.save(userAssembler.converToEntity(contact));
	}

	@Override
	public void delete(String id) {
		Interview interView = interviewRepository.findOne(id);
		if (interView == null) {
			throw new ValidationException(new ValidationError("INTERVIEW_ID_NOT_FOUND", "Interview Not Found"));

		}
		interviewRepository.delete(id);
	}

	@Override
	public void updateInterview(InterviewDTO userDtls) {
		InterviewAssembler userAssembler = new InterviewAssembler();
		if (StringUtils.isEmpty(userDtls.getInterviewId())) {
			throw new ValidationException(new ValidationError("ID", "ID cannot be empty"));
		}
		// validateInterviewDTO(userDtls);
		Interview interView = interviewRepository.findOne(userDtls.getInterviewId());
		if (interView == null) {
			throw new ValidationException(new ValidationError("INTERVIEW_ID_NOT_FOUND", "Interview Not Found"));

		}
		if (!StringUtils.isEmpty(userDtls.getInterviewerName())) {
			interView.setInterviewerName(userDtls.getInterviewerName());
		}
		if (!StringUtils.isEmpty(userDtls.getInterviewStatus())) {
			interView.setInterviewStatus(userDtls.getInterviewStatus());
		}
		if (!StringUtils.isEmpty(userDtls.getInterviewTime())) {
			interView.setInterviewTime(parseStrToDateTime(userDtls.getInterviewTime()));
		}
		if (!StringUtils.isEmpty(userDtls.getInterviewName())) {
			interView.setName(userDtls.getInterviewName());
		}
		if (!StringUtils.isEmpty(userDtls.getInterviewDate())) {
			interView.setInterviewDate(parseStrToDate(userDtls.getInterviewDate()));
		}
		interviewRepository.save(userAssembler.converToEntity(userDtls));
	}
	private LocalDate parseStrToDate(String dte) {
		if (dte != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			return LocalDate.parse(dte, formatter);
		}
		return null;
	}

	private LocalDateTime parseStrToDateTime(String dte) {
		if (dte != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			return LocalDateTime.parse(dte, formatter);
		}
		return null;
	}
	private void validateInterviewDTO(InterviewDTO dto) {
		Assert.notNull(dto, "InterviewDTO must not be null!");
		
		if (StringUtils.isEmpty(dto.getInterviewerName())) {
			throw new ValidationException(new ValidationError("INTERVIEWER_NAME", "Interviewer Name cannot be empty"));
		}
		else if(!(dto.getInterviewerName().length()>=5 && dto.getInterviewerName().length()<30)) {
			throw new ValidationException(new ValidationError("INTERVIEWER_NAME_MIN_MAX", "Interviewer name should be minimum 5 and maximum 30 characters"));
		}
		
		if (StringUtils.isEmpty(dto.getInterviewName())) {
			throw new ValidationException(new ValidationError("INTERVIEW NAME", "Interview name cannot be empty"));
		}else if(!(dto.getInterviewName().length()>=3 && dto.getInterviewName().length()<30)) {
			throw new ValidationException(new ValidationError("INTERVIEW NAME_MIN_MAX", "Interview name should be minimum 5 and maximum 100 characters"));
		}
		
		if (StringUtils.isEmpty(dto.getUserSkills())) {
			throw new ValidationException(new ValidationError("USER SKILLS", "User Skills cannot be empty"));
		}else if(!(dto.getUserSkills().length()>=5 && dto.getUserSkills().length()<30)) {
			throw new ValidationException(new ValidationError("USER SKILLS_MIN_MAX", "User skills should be minimum 5 and maximum 30 characters"));
		}
		
		if (StringUtils.isEmpty(dto.getInterviewStatus())) {
			throw new ValidationException(new ValidationError("INTERVIEW STATUS", "Interview status cannot be empty"));
		}else if(!(dto.getInterviewStatus().length()>=5 && dto.getInterviewStatus().length()<100)) {
			throw new ValidationException(new ValidationError("INTERVIEW STATUS_MIN_MAX", "Interview status should be minimum 5 and maximum 100 characters"));
		}
		
		if (StringUtils.isEmpty(dto.getRemarks())) {
			throw new ValidationException(new ValidationError("REMARKS", "Remarks cannot be empty"));
		}else if(!(dto.getRemarks().length()>=5 && dto.getRemarks().length()<100)) {
			throw new ValidationException(new ValidationError("REMARKS_MIN_MAX", "Remarks should be minimum 5 and maximum 100 characters"));
		}
		
		if (StringUtils.isEmpty(dto.getInterviewerName()))
			throw new ValidationException(new ValidationError("INTERVIEWER_NAME", "Interviewer Name cannot be empty"));
		if (StringUtils.isEmpty(dto.getInterviewName()))
			throw new ValidationException(new ValidationError("INTERVIEW_NAME", "Interview Name cannot be empty"));
		if (StringUtils.isEmpty(dto.getUserSkills()))
			throw new ValidationException(new ValidationError("USER_SKILLS", "User Skills cannot be empty"));
		if (StringUtils.isEmpty(dto.getRemarks()))
			throw new ValidationException(new ValidationError("REMARKS", "Remarks cannot be empty"));
		if (StringUtils.isEmpty(dto.getInterviewStatus()))
			throw new ValidationException(new ValidationError("INTERVIEW_STATUS", "Interview Status cannot be empty"));
	}

	@Override
	public void addAttendee(AttendeeDTO userDtls) {

		Interview interView = interviewRepository.findOne(userDtls.getInterviewId());
		if (interView == null) {
			throw new ValidationException(new ValidationError("INTERVIEW_ID_NOT_FOUND", "Interview Not Found"));

		}
		User user = userRepository.findOne(userDtls.getUserId());
		if (user == null) {
			throw new ValidationException(new ValidationError("USER_ID_NOT_FOUND", "User Not Found"));

		}
		java.util.Set<User> set = new HashSet<User>();
		set.add(user);
		interView.setAttendees(set);
		interviewRepository.save(interView);
	}

	@Override
	public AttendeeResponse getAllAttendee(String userDtls) {
		// TODO Auto-generated method stub
		Interview interView = interviewRepository.findOne(userDtls);
		if (interView == null) {
			throw new ValidationException(new ValidationError("ATTENDEES_NOT_AVAL", "Attendees are not found"));

		}
		UserAssembler assembler = new UserAssembler();
		AttendeeResponse resp = new AttendeeResponse();
		List<User> listUser = convertSetToList(interView.getAttendees());
		resp.setListUser(assembler.converToListDto(listUser));
		return resp;
	}

	private <T> List<T> convertSetToList(Set<T> set) {
		// create an empty list
		List<T> list = new ArrayList<>();

		// push each element in the set into the list
		for (T t : set)
			list.add(t);

		// return the list
		return list;
	}
}
