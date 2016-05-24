package com.ysl.bonjour.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author pku134
 *
 *This is a controller interface which leads to get controller objects and fetches and pushes javafx scene.
 */
public interface Controller {

	/*
	 * This interface can have methods for future purpose on sending request and receiving response objects.
	 */
	public Object getScene();
	public void setScene(Scene scene);

}
