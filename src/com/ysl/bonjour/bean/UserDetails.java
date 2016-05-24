package com.ysl.bonjour.bean;

import java.util.Map;

import com.ysl.bonjour.util.Constants;
import com.ysl.bonjour.util.SessionManager;


/**
 * @author pku134
 *
 *This class is a bean of user details and can add more fields as per requirement related to user details.
 */
public class UserDetails {

	private String userName;
	private String password;
	private String emailId;
	private String country;
	private String expectFor;
	
	public UserDetails(Map loginDetails) {
		/*
		 * Will use this to get Map details to fetch as input for meetingDetailed List
		 */
	}

	public UserDetails(String userName, String password) {

		this.userName = userName;
		this.password = password;
	}

	public UserDetails(String userName, String emailId, String country, String expectFor) {
		this.userName = userName;
		this.emailId = emailId;
		this.country = country;
		this.expectFor = expectFor;
	}

	public UserDetails() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExpectFor() {
		return expectFor;
	}

	public void setExpectFor(String expectFor) {
		this.expectFor = expectFor;
	}





}
