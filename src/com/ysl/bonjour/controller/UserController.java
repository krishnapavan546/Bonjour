package com.ysl.bonjour.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import com.ysl.bonjour.bean.UserDetails;
import com.ysl.bonjour.core.BonjourRunner;
import com.ysl.bonjour.util.Constants;
import com.ysl.bonjour.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author pku134
 *
 *This is to deal with login and user administrator related details. 
 */
public class UserController implements Controller {

	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	@FXML
	private Button login;
	private Scene scene;
	

	private Logger log = Logger.getLogger(UserController.class);
	private SessionManager sessionManager = new SessionManager();
	private UserDetails userDetails;
	@Override
	public Scene getScene() {
		try {
			log.info("INFO::In Controller ");
			Pane pane = (Pane) FXMLLoader.load(ServiceController.class.getClassLoader().getResource(Constants.LOGIN_FXML_PATH));
			if (pane != null) {
				pane.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10), new Insets(5))));
				scene = new Scene(pane);
				scene.setRoot(pane);
				return scene;
			} else {
				log.error("ERROR::");
			}
		}
		catch (IOException e) {
			log.error("ERROR::Error occured in getting hangout page", e);
			scene = null;
		}
		return scene;
	}

	/**
	 * Sets scene navigation and for future scene enabling between navigation
	 */
	@Override
	public void setScene(Scene scene) {
		try
		{
		if (scene != null) {
			this.scene = scene;
			log.info("DEBUG::Moving ahead to next scene as navigation initiated");
			scene.getWindow().hide();
			Controller controller = BonjourControllerFactory.getController("MakeoverController");
			if (controller != null) {
				BonjourRunner runner = new BonjourRunner();
				runner.execute(controller);		
			}
			else
			{
				log.info("INFO::Controller Object is null");				
			}		
		}
		else
		{
			log.info("INFO::Controller Scene is not avaialable");
		}
		}
		catch(Exception e)
		{
			log.error("ERROR::Could not start scene for MakeoverController from Userconroller",e);
		}
	}

	public void processUserLogin(ActionEvent event) {

		try {
			log.info("INFO::Login User Processing for availablility check for user " + userName.getText());
			userDetails = new UserDetails(userName.getText(), password.getText());
			Double sessionID = processUserSession(userDetails);
			if (sessionID != null) {
				log.info("INFO::User is a valid user");
				Node node=(Node)event.getSource();
				setScene(node.getScene());
			} else {
				log.info("INFO::User is a not valid user.Please sing up for access.");
			}
		} catch (Exception e) {
			log.error("ERROR::Logine Event could not be processed,since there is an error in UserController.",e);
		}
	}

	private Double processUserSession(UserDetails userDetails) {
		if (userDetails != null) {
			if (userDetails.getUserName().equals(Constants.USER_NAME)
					&& userDetails.getPassword().equals(Constants.PASSWORD)) {
				sessionManager.setUserSessionId(userDetails);
				return sessionManager.getUserSessionId();
			}
			else
			{
				log.info("INFO::Not receved user details");
				return null;
			}

		} else {
			log.info("INFO::Not receved user details");
			return null;
		}
	}

}
