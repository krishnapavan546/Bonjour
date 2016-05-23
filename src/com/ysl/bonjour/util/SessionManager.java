package com.ysl.bonjour.util;

import java.util.Random;

import com.ysl.bonjour.bean.UserDetails;

public class SessionManager {

	private UserDetails userDetails;
	private double userSessionId;

	public double createSession(UserDetails userDatails) {
		
		Random random=new Random();		
		return random.nextDouble();
	}
	
	public double getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(UserDetails userDetails) {

		this.userSessionId = createSession(userDetails);
	}

}
