package com.devlab.sandbox.web.manager.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.devlab.sandbox.web.manager.Navigation;

public class SessionManagerBeanTest {
	
	@SuppressWarnings("serial")
	public class SessionManagerBeanMock extends SessionManagerBean {
		
		private int user_timeout = 10 * 60000;
		
		@Override
		public User getLoggedUser() {
			if(loggedLifeTime != null && loggedLifeTime < System.currentTimeMillis()) {
				this.loggedUser = null;
			}
			return loggedUser;
		}
		
		@Override
		protected int getUSER_TIMEOUT() {
			return user_timeout;
		}

		public void setUser_timeout(int user_timeout) {
			this.user_timeout = user_timeout;
		}
	}
	
	@Test
	public void testUserAuthenticationFail() {
		SessionManagerBean sessionBean = new SessionManagerBean();
		sessionBean.setUserServiceBean(new UserServiceBean());
		
		String result = sessionBean.userAuthentication("username", "password");
		
		assertNull(result);
	}
	
	@Test
	public void testUserAuthenticationSuccess() {
		SessionManagerBean sessionBean = new SessionManagerBean();
		sessionBean.setUserServiceBean(new UserServiceBean());
		
		String result = sessionBean.userAuthentication("mock.user", "mock@user123");
		String expected = Navigation.mainPage();
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testLoggedUser() {
		SessionManagerBean sessionBean = new SessionManagerBean();
		sessionBean.setUserServiceBean(new UserServiceBean());
		
		sessionBean.userAuthentication("mock.user", "mock@user123");
		
		User user = sessionBean.getLoggedUser();
		assertNotNull(user);
		
		assertEquals("Unit Test Mock User", user.getName());
	}
	
	@Test
	public void testLoggedUserTimeOut() throws InterruptedException {
		SessionManagerBeanMock sessionBean = new SessionManagerBeanMock();
		sessionBean.setUserServiceBean(new UserServiceBean());
		sessionBean.setUser_timeout(100);
		
		sessionBean.userAuthentication("mock.user", "mock@user123");
		
		User user = sessionBean.getLoggedUser();
		assertNotNull(user);
		
		Thread.sleep(100L);
		
		user = sessionBean.getLoggedUser();
		assertNull(user);
	}
	
	@Test
	public void testLoggedUserLogout() throws InterruptedException {
		SessionManagerBeanMock sessionBean = new SessionManagerBeanMock();
		sessionBean.setUserServiceBean(new UserServiceBean());
		
		sessionBean.userAuthentication("mock.user", "mock@user123");
		
		User user = sessionBean.getLoggedUser();
		assertNotNull(user);
		
		try {
			sessionBean.logout();
		} catch (NullPointerException e) {
			// Avoid NullPointerException in navigation class
		}
		
		user = sessionBean.getLoggedUser();
		assertNull(user);
	}
}
