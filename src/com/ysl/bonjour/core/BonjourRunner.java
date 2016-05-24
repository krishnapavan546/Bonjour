package com.ysl.bonjour.core;

import org.apache.log4j.Logger;

import com.ysl.bonjour.controller.BonjourControllerFactory;
import com.ysl.bonjour.controller.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author pku134
 *
 *	This is the executor class for the application and will start main flow from here.
 */
public class BonjourRunner extends Application {
	Stage stage;
	private Logger log = Logger.getLogger(BonjourRunner.class);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		Controller controller = BonjourControllerFactory.getController("UserController");
		Scene scene = (Scene) controller.getScene();
		stage.setTitle("Bonjour YSL");
		stage.resizableProperty().setValue(Boolean.FALSE);
		stage.setScene(scene);
		stage.show();
	}

	public void execute(Controller controller) {
		try {
			log.info("INFO::Setting Stage for New Scene");
			stage = new Stage();
			if (controller != null) {
				Scene scene = (Scene) controller.getScene();
				if (scene != null) {
					stage.setScene(scene);
					stage.setTitle("Bonjour YSL");
					stage.resizableProperty().setValue(Boolean.FALSE);
					stage.show();
				} else {
					log.info("Error::Scene is not received");
				}
			} else {
				log.info("ERROR::Controller is not received");				
			}
		} catch (Exception e){
			log.error("ERROR::Error received in getting Controller scene", e);
		}
	}

}
