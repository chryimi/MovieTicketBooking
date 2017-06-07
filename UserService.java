package com.movbooking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;y
import com.movbooking.dao.UserDAO;
import com.movbooking.entity.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public User createNewUser(User user) {
		if (checkIsExit(user.getUserName())) {
			return null;
		}
		userDAO.addUser(user);
		return userDAO.getUser(user.getUserName());
	}
	
	public User getUserById(Integer id) {
		User user = userDAO.getUserById(id);
		return user;
	}

	
	public User userLogin(User loginUser) {
		User user= userDAO.getUser(loginUser.getUserName());
		if (user == null) {
			return null;
		} else {
			if (user.getPassword().equals(loginUser.getPassword())) {
				return user;
			} else {
				return null;
			}
		}
	}
	
	private boolean checkIsExit(String userName) {
		User user= userDAO.getUser(userName);
		return (user != null);
	}
}
