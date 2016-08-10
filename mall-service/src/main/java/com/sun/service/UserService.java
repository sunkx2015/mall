package com.sun.service;

import com.sun.model.user;

public interface UserService {
	int insertUser();
	user selectUserList(String keyId);
}
