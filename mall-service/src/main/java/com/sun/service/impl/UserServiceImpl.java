package com.sun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.dao.userMapper;
import com.sun.model.user;
import com.sun.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	userMapper userService;

	public int insertUser() {
		user user = new user();
		user.setUsername("admin");
		user.setPassword("password");
		return userService.insert(user);
	}

	public user selectUserList(String keyId) {
		return userService.selectByPrimaryKey(keyId);
	}
	
}
