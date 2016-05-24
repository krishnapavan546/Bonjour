package com.ysl.bonjour.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ysl.bonjour.bean.MakeupIndicator;
import com.ysl.bonjour.core.BonjourRunner;
import com.ysl.bonjour.util.Constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
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


/**
 * @author pku134
 *
 * This class refers to meeting list details.
 */
public class MakeoverListController implements Controller {

	@FXML private Hyperlink joinMeeting;
	private TableView<MakeupIndicator> meetingListTable = new TableView<>();
	private Logger log = Logger.getLogger(MakeoverListController.class);
	private Pane pane;
	private Scene scene;
	@Override
	public Scene getScene() {
		try {
			log.info("INFO::In Controller of Makeover controller");
			pane = (Pane) FXMLLoader.load(MakeoverListController.class.getClassLoader().getResource(Constants.MAKUP_FXML_PATH));
			if (pane != null) {
				pane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), new Insets(5))));
				log.info("DEBUG::entered in to Setting Frame for Pane at MakeoverController");
				scene = new Scene(pane);
				this.meetingListTable = addMeetingList(this.meetingListTable);
				if (meetingListTable != null) {

					((Pane) scene.getRoot()).getChildren().addAll(meetingListTable);
				}

				scene.setRoot(pane);
			} else {
				log.error("ERROR::Parent for MakeoverList controller could not loaded");
				
			}
		} catch (Exception e) {
			log.error("ERROR::Error occured in getting Makeover Controller page", e);
			scene = null;
		}
		return scene;
	}

	@FXML
	private TableView<MakeupIndicator> addMeetingList(TableView<MakeupIndicator> meetingListTable1) {

		List<MakeupIndicator> list = new ArrayList<>();
		list.add(new MakeupIndicator(Constants.USER_NAME, new Date(),new Date()));
		list.add(new MakeupIndicator(Constants.USER_NAME2, new Date(),new Date()));
		ObservableList<MakeupIndicator> items = FXCollections.observableList(list);
		TableColumn<MakeupIndicator, Date> date = new TableColumn<>("Date");
		date.setCellValueFactory(new PropertyValueFactory<>("meetingDate"));
		date.setMinWidth(140);
		TableColumn<MakeupIndicator, Date> time = new TableColumn<>("Time");
		time.setCellValueFactory(new PropertyValueFactory<>("meetingTime"));
		time.setMinWidth(80);
		TableColumn<MakeupIndicator, String> user = new TableColumn<>("User");
		user.setCellValueFactory(new PropertyValueFactory<>("userName"));
		user.setMinWidth(80);
		TableColumn<MakeupIndicator, Hyperlink> meetingLink = new TableColumn<>("");
		meetingLink.setCellFactory(
				new Callback<TableColumn<MakeupIndicator, Hyperlink>, TableCell<MakeupIndicator, Hyperlink>>() {

					@Override
					public TableCell call(TableColumn arg0) {

						return new ButtonCell();
					}
				});
		meetingLink.setMinWidth(80);

		meetingListTable1.setEditable(true);
		meetingListTable1.setPrefWidth(370);
		meetingListTable1.setPrefHeight(250);
		meetingListTable1.setLayoutX(110);
		meetingListTable1.setLayoutY(80);
		meetingListTable1.setPadding(new Insets(5));
		meetingListTable1.setPlaceholder(meetingListTable);
		meetingListTable1.setFixedCellSize(105);
		meetingListTable1.setCenterShape(true);
		meetingListTable1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		meetingListTable1.setItems(items);
		meetingListTable1.getColumns().addAll(date, time, meetingLink);		
		return meetingListTable1;
	}

	public class ButtonCell extends TableCell<MakeupIndicator, Hyperlink> {

		ButtonCell() {
			joinMeeting = new Hyperlink();
			if (joinMeeting != null) {
				joinMeeting.setOnAction(event -> {
					Node node1 = (Node) event.getSource();
					setScene(node1.getScene());
				});

			} else {
				log.info("DEBUG::Button did not get initiated in inner class ");
			}

		}

		@Override
		protected void updateItem(Hyperlink arg0, boolean arg1) {

			super.updateItem(arg0, arg1);
			if (!arg1) {
				joinMeeting.setText("Start Meeting ");
				setGraphic(joinMeeting);
			} else {
				setGraphic(null);
			}

		}
	}

	/**
	 * For now this is inner class can make this as seperate class and can
	 * resuse this for all Table cell details.
	 */

	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
		log.info("DEBUG::Moving ahead to next scene as navigation initiated");
		scene.getWindow().hide();
		Controller controller = BonjourControllerFactory.getController("MakeupDetailedController");
		if (controller != null) {
			BonjourRunner runner = new BonjourRunner();
			runner.execute(controller);
		} else {
			log.info("INFO::Controller Object is null");
		}

		log.info("INFO::Controller Scene is not avaialable");
	}

	public void setrequestedMeeting(ActionEvent event) {
		scene = ((Node) event.getSource()).getScene();
		setScene(scene);
	}
}
