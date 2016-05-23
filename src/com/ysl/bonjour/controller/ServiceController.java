package com.ysl.bonjour.controller;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.ysl.bonjour.util.Constants;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ServiceController implements Controller {

	@FXML private Button hangout;
	@FXML private Button captureFirst;
	@FXML private Button captureLater;
	@FXML private ImageView imageView;
	@FXML private Button email;
	@FXML private ImageView imageViewFirst;
	
	private Logger log = Logger.getLogger(ServiceController.class);
	private Scene scene;
	private Robot robot;
	private Rectangle rectangle;
	/*private HBox hboxFirst;
	private HBox hboxLater;*/
	
	

	@Override
	public Scene getScene() {
		try {
			log.info("INFO::In Controller ");
			Pane pane = (Pane) FXMLLoader.load(ServiceController.class.getClassLoader().getResource(Constants.SERVICE_FXML_PATH));
			if (pane != null) {
				pane.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10), new Insets(5))));
				scene = new Scene(pane);
				
				scene.setRoot(pane);
				return scene;
			} else {
				log.error("ERROR::");
			}

		} catch (IOException e) {
			log.error("ERROR::Error occured in getting hangout page",e);
			scene= null;
		}
		return scene;
		
	}	
	@Override
	public void setScene(Scene scene) {
		/**
		 * Sets scene navigation and for future scene enabling between navigation
		 */
	}
	public void setHangout(ActionEvent event) {
		try {
			URI hangoutURL = new URI(Constants.HANGOUT_URL);
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(hangoutURL);
		} catch (URISyntaxException e) {
			log.error("ERROR::Error occured in getting hangout page",e);
		} catch (IOException e) {
			log.error("ERROR::Error occured in getting hangout page",e);
		}
	}
	public void setCapture(ActionEvent event) {
		try 
		{
			log.info("INFO::Capture Intiated");
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.hide();
			Thread.sleep(50);
			rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			robot = new Robot();
		
			BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
			if (bufferedImage != null) {
				WritableImage image = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
				PixelWriter pw = image.getPixelWriter();
				for (int i = 0; i < bufferedImage.getWidth(); i++) {
					for (int j = 0; j < bufferedImage.getHeight(); j++) {
						pw.setArgb(i, j, bufferedImage.getRGB(i, j));
					}
				}	
		/*	HBox hboxFirst=new HBox();
			HBox hboxLater=new HBox();
			String cssBordering = "-fx-border-color:yellow; \n"
						+ "-fx-border-insets:3;\n"
			            + "-fx-border-radius:7;\n"
			            + "-fx-border-width:1.0";
			
			hboxFirst.setStyle(cssBordering);
			hboxLater.setStyle(cssBordering);*/
			
			String buttonName=((Button)event.getSource()).getId();
			if ("captureFirst".equals(buttonName)) {
				boolean imageStatus = ImageIO.write(SwingFXUtils.fromFXImage(image, bufferedImage), Constants.CAPTURED_IMAGE_TYPE, new File(Constants.CAPTURED_IMAGE_PATH));
				log.debug("DEBUG::Image Save status was "+imageStatus);
				log.info("INFO::Image Saved on to disk at" +new File(Constants.CAPTURED_IMAGE_PATH).getPath());
				imageViewFirst.setImage(image);
				imageViewFirst.getImage();
				captureFirst.setDisable(true);
			}else
			if ("captureLater".equals(buttonName))
			{
				boolean imageStatus = ImageIO.write(SwingFXUtils.fromFXImage(image, bufferedImage), Constants.CAPTURED_IMAGE_TYPE, new File(Constants.CAPTUREDLATER_IMAGE_PATH));
				log.debug("DEBUG::Image Save status was "+imageStatus);
				log.info("INFO::Image Saved on to disk at" +new File(Constants.CAPTUREDLATER_IMAGE_PATH).getPath());
				imageView.setImage(image);
				imageView.getImage();
				captureLater.setDisable(true);
			}
			stage.show();
			}
			else
			{
				log.info("INFO::Could not capture image please capture image after some time");
			}
		}
		catch(Exception e){
			log.error("ERROR::Error occured in capturing screen",e);
		}
	}
}
