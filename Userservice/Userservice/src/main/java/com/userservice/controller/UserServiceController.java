package com.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.userservice.entity.User;
import com.userservice.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserServiceController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		User fetchedUser = userService.getUserById(userId);
		return new ResponseEntity<User>(fetchedUser, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> usersList = userService.getAllUser();
		return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUserByID(@PathVariable String userId){
		userService.deleteUserById(userId);
		return new ResponseEntity<String>("User Deleted.", HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId){
		User updatedUser = userService.updateUserById(userId, user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
}
