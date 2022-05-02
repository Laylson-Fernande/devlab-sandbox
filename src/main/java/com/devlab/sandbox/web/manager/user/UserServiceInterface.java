package com.devlab.sandbox.web.manager.user;

public interface UserServiceInterface {

	User getUserByUsername(String username);

	boolean isUserPassword(Long id, String password);

}