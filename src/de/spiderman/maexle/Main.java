package de.spiderman.maexle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

public class Main extends Application {

	private static final MaexleNumber leftMaexleNumber = new MaexleNumber(2, 1);
	private static final MaexleNumber rightMaexleNumber = new MaexleNumber(2, 1);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("fxml/maexleWindow.fxml"))));
		stage.setWidth(750);
		stage.setHeight(500);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}

	public static MaexleNumber getLeftMaexleNumber() {
		return leftMaexleNumber;
	}

	public static MaexleNumber getRightMaexleNumber() {
		return rightMaexleNumber;
	}

}
