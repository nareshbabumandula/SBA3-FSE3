package com.interviewtracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.comman.SuccessfulReponse;
import com.interviewtracker.exceptions.ErrorDetails;
import com.interviewtracker.exceptions.ErrorReponse;
import com.interviewtracker.model.UserDTO;
import com.interviewtracker.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Value("${welcome.message}")
    private String message;

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping(path = "/api/User/createUser")
	public ResponseEntity<?> createUser(HttpServletRequest request, @Valid @RequestBody UserDTO user)
			throws IOException {
		try {
			userService.save(user);
			return ResponseEntity.ok(new SuccessfulReponse("User Created Succesfully"));
		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);
			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", "Error " + e.getMessage(), null)));
		}
	}

	@GetMapping(path = "/api/User/getAllUsers")
	public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
		try {
			List<UserDTO> uersList = userService.findAll();

			return ResponseEntity.ok(new SuccessfulReponse(uersList));

		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}

	@PostMapping(path = "/api/User/updateUser")
	public ResponseEntity<?> updateUser(HttpServletRequest request, @Valid @RequestBody UserDTO userDtls) {
		try {
			userService.updateUser(userDtls);

			return ResponseEntity.ok(new SuccessfulReponse("User updated Succesfully"));

		} catch (Exception e) {
			LOGGER.error(UserController.class.getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}

	@DeleteMapping(path = "/api/User/deleteUser/{userId}")
	public ResponseEntity<?> deleteUser(HttpServletRequest request, @PathVariable("userId") String id) {
		try {
			userService.delete(id);

			return ResponseEntity.ok(new SuccessfulReponse("User deleted Succesfully"));

		} catch (Exception e) {
			LOGGER.error(UserController.class.getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}

	@GetMapping(path = "/api/User/search")
	public ResponseEntity<?> Search(HttpServletRequest request, @RequestParam("s") String s) {
		try {
			List<UserDTO> uersList = userService.search(s);

			return ResponseEntity.ok(new SuccessfulReponse(uersList));

		} catch (Exception e) {
			LOGGER.error(this.getClass().getName(), e);

			return ResponseEntity.ok(new ErrorReponse(new ErrorDetails("", e.getMessage(), null)));
		}
	}
	

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);

        return "welcome"; //view
    }

}