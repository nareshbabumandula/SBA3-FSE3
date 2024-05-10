package com.comman;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.interviewtracker.model.User;
import com.interviewtracker.model.UserDTO;

public class UserAssembler {

	public UserDTO converToDto(Optional<User> userOptional) {
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			UserDTO dto=new UserDTO();
			dto.setEmail(user.getEmail());
			dto.setUserId(user.getUserId());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setMobileNo(user.getMobileNo());
			return dto;	
		}
		return null;
	}
	
	public User converToEntity(UserDTO userDto) {
		if(userDto!=null) {
			User dto=new User();
			dto.setEmail(userDto.getEmail());
			dto.setUserId(userDto.getUserId());
			dto.setFirstName(userDto.getFirstName());
			dto.setLastName(userDto.getLastName());
			dto.setMobileNo(userDto.getMobileNo());
			return dto;	
		}
		return null;
	}
	
	public List<UserDTO>  converToListDto(List<User> user){
		if(user!=null)
			return user.parallelStream().
					            map(a->this.converToDto(Optional.of(a))).collect(Collectors.toList());
			
			return new ArrayList<UserDTO>();
	}
}
