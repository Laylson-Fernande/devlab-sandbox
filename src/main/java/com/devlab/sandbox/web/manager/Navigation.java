package com.devlab.sandbox.web.manager;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Navigation {

	public static void redirectPage(String url) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String contextPath = externalContext.getRequestContextPath();
			externalContext.redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void toMainPage() {
		redirectPage(mainPage());
	}
	
	public static String mainPage() {
		return "/Index.xhtml?faces-redirect=true";
	}
	
	public static void toLoginPage() {
		redirectPage(loginPage());
	}
	
	public static String loginPage() {
		return "/authentication/Login.xhtml?faces-redirect=true";
	}
}
