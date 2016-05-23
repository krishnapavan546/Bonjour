package com.ysl.bonjour.bean;

import com.ysl.bonjour.util.SessionManager;

public class BonjourMakeupRequest implements BonjourRequest{
	
	private SessionManager session;
	private UserDetails userDetails;
	private MakeupIndicator makeupIndicator;
	
	public SessionManager getSession() {
		return session;
	}
	public void setSession(SessionManager session) {
		this.session = session;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public MakeupIndicator getMakeupIndicator() {
		return makeupIndicator;
	}
	public void setMakeupIndicator(MakeupIndicator makeupIndicator) {
		this.makeupIndicator = makeupIndicator;
	}
}
