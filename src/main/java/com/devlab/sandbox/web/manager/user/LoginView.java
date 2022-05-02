package com.devlab.sandbox.web.manager.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LoginView implements Serializable {
	
	@ManagedProperty(value = "#{sessionManagerBean}")
	private SessionManagerBean sessionManagerBean;
	private String username;
	private String password;
	
	private String validationMessage;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		System.out.println("USERNAME: "+this.username);
		System.out.println("PASSWORD: "+this.password);
		return this.sessionManagerBean.userAuthentication(this.username, this.password);
	}

	public SessionManagerBean getSessionManagerBean() {
		return sessionManagerBean;
	}

	public void setSessionManagerBean(SessionManagerBean sessionManagerBean) {
		this.sessionManagerBean = sessionManagerBean;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}
}
