package com.ysl.bonjour.bean;

public class MeetingProperties {

	private String propertiesKey;
	private String propertiesValue;

	public String getPropertiesValue() {
		return propertiesValue;
	}

	public void setPropertiesValue(String propertiesValue) {
		this.propertiesValue = propertiesValue;
	}

	public String getProperties() {
		return propertiesKey;
	}

	public void setProperties(String propertiesKey) {
		this.propertiesKey = propertiesKey;
	}

	public MeetingProperties(String propertiesKey, String propertiesValue) {
		
		this.propertiesKey = propertiesKey;
		this.propertiesValue = propertiesValue;
	}
}
