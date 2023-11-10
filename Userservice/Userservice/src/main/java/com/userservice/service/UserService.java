package com.userservice.service;

import com.userservice.entity.User;
import java.util.List;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUserById(String userId);
	
	void deleteUserById(String userId);
	
	User updateUserById(String userId, User user);
	
}
