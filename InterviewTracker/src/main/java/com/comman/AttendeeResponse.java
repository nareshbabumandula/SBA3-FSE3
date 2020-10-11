package com.comman;

import java.io.Serializable;
import java.util.List;

import com.interviewtracker.model.UserDTO;

public class AttendeeResponse implements Serializable{

	private List<UserDTO> listUser;

	public List<UserDTO> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserDTO> listUser) {
		this.listUser = listUser;
	}
	
	
}
