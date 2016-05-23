package com.ysl.bonjour.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeupIndicator 
{
	private String userName;
	private Date meetingDate;
	private String meetingTime;
	
	DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}	
	
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(Date date) {
		meetingTime = formatter.format(date);
		this.meetingTime = meetingTime;
	}
	public MakeupIndicator(String userName, Date meetingDate) {
		super();
		this.userName = userName;
		this.meetingDate = meetingDate;
	}
	public MakeupIndicator(String userName, Date meetingDate,Date meetingTime) {
		super();
		this.userName = userName;
		this.meetingDate = meetingDate;
		setMeetingTime(meetingTime);
		this.meetingTime=getMeetingTime();
	}
}
