package de.spiderman.maexle.controller;

import de.spiderman.maexle.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MaexleWindowController implements Initializable {

	private int xOffset, yOffset;

	@FXML
	public Pane leftResultContainer, rightResultContainer, rightNumberContainer, leftNumberContainer;
	@FXML
	public Label leftMaexleNumber, rightMaexleNumber, evaluationLabel;
	@FXML
	public TextField leftNumber1, leftNumber2, rightNumber1, rightNumber2;
	@FXML
	public Button closeButton;
	@FXML
	public AnchorPane basePane;

	public void closeProgramm(ActionEvent event) {
		System.exit(0);
	}

	public void leftRandomize(ActionEvent event) {
		Main.getLeftMaexleNumber().randomize(2);
		updateNumbers();
		updateResults();
	}

	public void rightRandomize(ActionEvent event) {
		Main.getRightMaexleNumber().randomize(2);
		updateNumbers();
		updateResults();
	}

	private void updateResults() {
		leftMaexleNumber.setText(Main.getLeftMaexleNumber().toString());
		rightMaexleNumber.setText(Main.getRightMaexleNumber().toString());

		switch (Main.getLeftMaexleNumber().compareTo(Main.getRightMaexleNumber())) {
			case -1: {
				evaluationLabel.setText("Player 2 is the winner!");
				break;
			}
			case 0: {
				evaluationLabel.setText("Draw!");
				break;
			}
			case 1: {
				evaluationLabel.setText("Player 1 is the winner!");
				break;
			}
		}
	}

	private void updateNumbers() {
		leftNumber1.setText(String.valueOf(Main.getLeftMaexleNumber().getIndex(0)));
		leftNumber2.setText(String.valueOf(Main.getLeftMaexleNumber().getIndex(1)));

		rightNumber1.setText(String.valueOf(Main.getRightMaexleNumber().getIndex(0)));
		rightNumber2.setText(String.valueOf(Main.getRightMaexleNumber().getIndex(1)));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// draggable stage
		basePane.setOnMousePressed(event ->  {
			xOffset = (int) (basePane.getScene().getWindow().getX() - event.getScreenX());
			yOffset = (int) (basePane.getScene().getWindow().getY() - event.getScreenY());
		});
		basePane.setOnMouseDragged(event -> {
			basePane.getScene().getWindow().setX(event.getScreenX() + xOffset);
			basePane.getScene().getWindow().setY(event.getScreenY() + yOffset);
		});

		// winner highlighting TODO: highlighting
		evaluationLabel.setOnMouseEntered(event -> {
			if (Main.getLeftMaexleNumber().isBiggerThan(Main.getRightMaexleNumber())) {
				leftNumberContainer.setId("win-highlight");
				leftResultContainer.setId("win-highlight");
			} else if (Main.getRightMaexleNumber().isBiggerThan(Main.getLeftMaexleNumber())) {
				rightNumberContainer.setId("win-highlight");
				rightResultContainer.setId("win-highlight");
			}
		});
		evaluationLabel.setOnMouseExited(event -> {
			leftNumberContainer.setId("");
			leftResultContainer.setId("");
			rightNumberContainer.setId("");
			rightResultContainer.setId("");
		});

		// validation
		leftNumber1.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) {
				if (leftNumber1.getText().matches("[1-6]")){
					Main.getLeftMaexleNumber().clear();
					Main.getLeftMaexleNumber().addNumber(Integer.parseInt(leftNumber1.getText()));
					Main.getLeftMaexleNumber().addNumber(Integer.parseInt(leftNumber2.getText()));
				} else {
					leftNumber1.setText("");
				}
			}

		});

		//update
		updateNumbers();
		updateResults();
	}

}
