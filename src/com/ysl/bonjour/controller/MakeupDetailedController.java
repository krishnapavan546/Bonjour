package com.ysl.bonjour.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ysl.bonjour.bean.UserDetails;
import com.ysl.bonjour.core.BonjourRunner;
import com.ysl.bonjour.util.Constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class MakeupDetailedController implements Controller {

	private Logger log = Logger.getLogger(MakeupDetailedController.class);
	private Scene scene;
	@FXML Button service;
	@Override
	public Scene getScene() {
		try {
			log.info("INFO::In makeover of Detailed Controller ");
			Pane pane = (Pane) FXMLLoader
					.load(MakeoverListController.class.getClassLoader().getResource(Constants.MDETAILS_FXML_PATH));
			if (pane != null) {
				pane.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10), new Insets(5))));
				scene = new Scene(pane);
				((Pane) scene.getRoot()).getChildren().addAll(getMeetingDetails("12345"));
				scene.setRoot(pane);
				return scene;
			} else {
				log.error("ERROR::Error in getting Detailed controller");
			}
		} catch (Exception e) {
			log.error("ERROR::Error occured in getting hangout page", e);
			scene = null;
		}
		return scene;

	}

	@Override
	public void setScene(Scene scene) {
		try
		{
		if (scene != null) {
			this.scene = scene;
			log.info("DEBUG::Moving ahead to next scene as navigation initiated");
			scene.getWindow().hide();
			Controller controller = BonjourControllerFactory.getController("ServiceController");
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
public class ButtonCell extends TableCell<UserDetails, Button> {		
	ButtonCell() {
			service=new Button();
			if (service != null) {
				service.setOnAction(event -> {
					Node node1 = (Node) event.getSource();
					setScene(node1.getScene());
				});				
			}
			else
			{
				log.info("DEBUG::Button did not get initiated in inner class ");
			}

			
		}
		@Override
		protected void updateItem(Button arg0, boolean arg1) {
		
		super.updateItem(arg0, arg1);
		if(!arg1){
            service.setText("Start Meeting ");
            setGraphic(service);
        } else {
            setGraphic(null);
        }
		
		}
	
	}

	private TableView<UserDetails> getMeetingDetails(String meetingID) {

		log.info("Meeting ID accessing is " + meetingID);
		TableView<UserDetails> table = new TableView<UserDetails>();
		table.setEditable(true);
		table.setMaxWidth(2000);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		List<UserDetails> list = new ArrayList<>();
		list.add(new UserDetails("Pavan","pavanbvrk@gmail.com","India","Change" ));
		ObservableList<UserDetails> items = FXCollections.observableList(list);
		TableColumn<UserDetails, String> userName = new TableColumn<>("User");
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		TableColumn<UserDetails, String> emailId = new TableColumn<>("emailId");
		emailId.setCellValueFactory(new PropertyValueFactory<>("emailId"));
		TableColumn<UserDetails, String> country = new TableColumn<>("country");
		country.setCellValueFactory(new PropertyValueFactory<>("country"));
		TableColumn<UserDetails, String> expectFor = new TableColumn<>("Expect For");
		expectFor.setCellValueFactory(new PropertyValueFactory<>("expectFor"));
		TableColumn<UserDetails, Button> meetingLink = new TableColumn<>("Hangout Call");
		meetingLink.setCellFactory(new Callback<TableColumn<UserDetails, Button>, TableCell<UserDetails, Button>>() {

			@Override
			public TableCell call(TableColumn arg0) {

				return new ButtonCell();
			}
		});
		table.setEditable(true);
		table.setPrefWidth(250);
		table.setPrefHeight(250);
		table.setLayoutX(180);
		table.setLayoutY(80);
		table.setPadding(new Insets(5));		
		table.setItems(items);
		table.getColumns().addAll(userName,emailId, country,expectFor,meetingLink);
		return table;

	}

}
