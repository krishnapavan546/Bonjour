package com.ysl.bonjour.bean;

import java.io.File;

public class BonjourMakeupResponse 
{
	private MakeupIndicator makeupIndicator;
	private File capturedImages;
	public MakeupIndicator getMakeupIndicator() {
		return makeupIndicator;
	}
	public void setMakeupIndicator(MakeupIndicator makeupIndicator) {
		this.makeupIndicator = makeupIndicator;
	}
	public File getCapturedImages() {
		return capturedImages;
	}
	public void setCapturedImages(File capturedImages) {
		this.capturedImages = capturedImages;
	}
}
