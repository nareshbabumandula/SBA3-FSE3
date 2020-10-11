package com.comman;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.interviewtracker.model.Interview;
import com.interviewtracker.model.InterviewDTO;

public class InterviewAssembler {

	public InterviewDTO converToDto(Interview user) {
		if (user != null) {
			InterviewDTO dto = new InterviewDTO();
			dto.setInterviewDate(parseDateToStr(user.getInterviewDate()));
			dto.setInterviewerName(user.getInterviewerName());
			dto.setInterviewId(user.getInterviewId());
			dto.setInterviewStatus(user.getInterviewStatus());
			dto.setInterviewTime(parseDateTimeToStr(user.getInterviewTime()));
			dto.setInterviewName(user.getName());
			dto.setRemarks(user.getRemarks());
			dto.setUserSkills(user.getUserSkills());
			return dto;
		}
		return null;
	}

	public Interview converToEntity(InterviewDTO userDto) {
		if (userDto != null) {
			Interview dto = new Interview();
			dto.setInterviewDate(parseStrToDate(userDto.getInterviewDate()));
			dto.setInterviewerName(userDto.getInterviewerName());
			dto.setInterviewId(userDto.getInterviewId());
			dto.setInterviewStatus(userDto.getInterviewStatus());
			dto.setInterviewTime(parseStrToDateTime(userDto.getInterviewTime()));
			dto.setName(userDto.getInterviewName());
			dto.setRemarks(userDto.getRemarks());
			dto.setUserSkills(userDto.getUserSkills());
			return dto;
		}
		return null;
	}

	public List<InterviewDTO> converToListDto(List<Interview> user) {
		if (user != null)
			return user.parallelStream().map(this::converToDto).collect(Collectors.toList());

		return new ArrayList<InterviewDTO>();
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

	private String parseDateToStr(LocalDate dte) {
		if (dte != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			return dte.format(formatter);
		}
		return null;
	}

	private String parseDateTimeToStr(LocalDateTime dte) {
		if (dte != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			return dte.format(formatter);
		}
		return null;
	}
}
