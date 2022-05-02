package com.devlab.sandbox.web.manager.user;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import com.devlab.sandbox.fake.database.DataBaseFake;

@ManagedBean
@Stateless
public class UserServiceBean implements UserServiceInterface {
	
	public User getUserByUsername(String username) {
		return DataBaseFake.getUserByUsername(username);
	}
	
	public boolean isUserPassword(Long id, String password) {
		return DataBaseFake.isUserPassword(id, password);
	}

}
