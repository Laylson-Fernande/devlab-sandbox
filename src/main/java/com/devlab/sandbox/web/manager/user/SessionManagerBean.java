package com.devlab.sandbox.web.manager.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.devlab.sandbox.web.manager.Navigation;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SessionManagerBean implements Serializable {
	
	@Inject
	private UserServiceInterface userServiceBean;
	protected User loggedUser;
	protected Long loggedLifeTime;
	
	private static final int USER_TIMEOUT = 10 * 60000;
	
	public String userAuthentication(String username, String password) {
		String result = null;
		if(username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
			String usernameTrin = username.trim();
			User user = userServiceBean.getUserByUsername(usernameTrin);
			if(user != null) {
				String passwordTrim = password.trim();
				boolean validPassword = this.userServiceBean.isUserPassword(user.getId(), passwordTrim);
				if(validPassword) {
					this.loggedLifeTime = System.currentTimeMillis() + getUSER_TIMEOUT();
					this.setLoggedUser(user);
					result = Navigation.mainPage();
				}
			}
		}
		return result;
	}
	
	public void logout() {
		this.setLoggedUser(null);
		Navigation.toLoginPage();
	}
	
	public User getLoggedUser() {
		if(loggedLifeTime != null && loggedLifeTime < System.currentTimeMillis()) {
			this.loggedUser = null;
		}
		if(this.loggedUser == null) {
			Navigation.toLoginPage();
		}
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	protected void setUserServiceBean(UserServiceInterface userServiceBean) {
		this.userServiceBean = userServiceBean;
	}
	
	protected int getUSER_TIMEOUT() {
		return USER_TIMEOUT;
	}

}
