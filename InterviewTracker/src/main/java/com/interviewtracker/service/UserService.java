package com.interviewtracker.service;
import java.util.List;

import com.interviewtracker.model.User;
import com.interviewtracker.model.UserDTO;

public interface UserService {
    List<UserDTO> findAll();

    List<UserDTO> search(String q);

    UserDTO findOne(String id);

    void save(UserDTO contact);

    void delete(String id);

	void updateUser(UserDTO userDtls);
}