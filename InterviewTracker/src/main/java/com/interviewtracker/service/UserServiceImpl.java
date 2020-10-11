package com.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.comman.UserAssembler;
import com.interviewtracker.exceptions.ValidationError;
import com.interviewtracker.exceptions.ValidationException;
import com.interviewtracker.model.User;
import com.interviewtracker.model.UserDTO;
import com.interviewtracker.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	private List<User> getCollectionFromIteralbe(Iterable<User> itr) {

		List<User> cltn = new ArrayList<User>();
		for (User t : itr)
			cltn.add(t);
		return cltn;
	}

	@Override
	public List<UserDTO> findAll() {
		UserAssembler userAssembler = new UserAssembler();
		return userAssembler.converToListDto(getCollectionFromIteralbe(userRepository.findAll()));
	}

	@Override
	public List<UserDTO> search(String q) {
		UserAssembler userAssembler = new UserAssembler();
		return null; 
				//userAssembler.converToListDto(userRepository.findByNameContaining(q));
	}

	@Override
	public UserDTO findOne(String id) {
		UserAssembler userAssembler = new UserAssembler();
		return userAssembler.converToDto(userRepository.findOne(id));
	}

	@Override
	public void save(UserDTO contact) {
		validateUserDTO(contact);
		UserAssembler userAssembler = new UserAssembler();
		User user = userRepository.findOne(contact.getUserId());
		if (user != null) {
			throw new ValidationException(new ValidationError("USER_ID_ALREADY_EXISTS", "User ID Already Exists"));

		}
		userRepository.save(userAssembler.converToEntity(contact));
	}

	@Override
	public void delete(String id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new ValidationException(new ValidationError("USER_NOT_FOUND", "User Not Found"));

		}
		userRepository.delete(id);
	}

	@Override
	public void updateUser(UserDTO userDtls) {
		UserAssembler userAssembler = new UserAssembler();
		if (StringUtils.isEmpty(userDtls.getUserId())) {
			throw new ValidationException(new ValidationError("ID", "ID cannot be empty"));
		}
		validateUserDTO(userDtls);
		userRepository.save(userAssembler.converToEntity(userDtls));
	}

	private void validateUserDTO(UserDTO dto) {
		Assert.notNull(dto, "UserDto must not be null!");
		if (StringUtils.isEmpty(dto.getUserId())) {
			throw new ValidationException(new ValidationError("ID", "ID cannot be empty"));
		}
		if (StringUtils.isEmpty(dto.getFirstName())) {
			throw new ValidationException(new ValidationError("FIRST_NAME", "First Name cannot be empty"));
		}else if(!(dto.getFirstName().length()>=5 && dto.getUserId().length()<30)) {
			throw new ValidationException(new ValidationError("FIRST_NAME_MIN_MAX", "First Name should be minimum 5 and maximum 30 characters"));
		}
		if (StringUtils.isEmpty(dto.getLastName()))
			throw new ValidationException(new ValidationError("LAST_NAME", "Last Name cannot be empty"));
		if (StringUtils.isEmpty(dto.getEmail()))
			throw new ValidationException(new ValidationError("EMAIL", "Email cannot be empty"));
		if (StringUtils.isEmpty(dto.getMobileNo()))
			throw new ValidationException(new ValidationError("MOBILE_NO", "Mobile No cannot be empty"));

	}
}