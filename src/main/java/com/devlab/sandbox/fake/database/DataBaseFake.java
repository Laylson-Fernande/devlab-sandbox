package com.devlab.sandbox.fake.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devlab.sandbox.web.manager.user.User;

public class DataBaseFake {
	
	static List<User> USERS = new ArrayList<User>();
	
	static Map<Long, String> PASSWORD_BY_USER = new HashMap<Long, String>();
	
	static {
		USERS.add(new User(1L,"Laylson","lalo"));
		USERS.add(new User(2L,"Unit Test Mock User","mock.user"));
		PASSWORD_BY_USER.put(1L, "1234");
		PASSWORD_BY_USER.put(2L, "mock@user123");
	}
	
	public static User getUserByUsername(String username) {
		for(User user : USERS) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public static boolean isUserPassword(Long id, String password) {
		boolean result = false;
		String pw = PASSWORD_BY_USER.get(id);
		if(pw != null && pw.equals(password)) {
			result = true;
		}
		return result;
	}
}
