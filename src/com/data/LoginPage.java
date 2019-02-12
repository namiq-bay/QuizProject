package com.data;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.dataBase.DataBaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class LoginPage implements Initializable {
	String[] data;

	@FXML
	private JFXSpinner slider;

	@FXML
	private Label label;

	@FXML
	private Label label2;

	@FXML
	private Label label3;

	@FXML
	private Label label4;

	@FXML
	public JFXTextField userName;

	@FXML
	private Label sign1;

	@FXML
	private JFXButton enterBtn;

	@FXML
	void enterEvent(ActionEvent event) {

		try {

			DataBaseConnection db = new DataBaseConnection();
			Runnable run = () -> {
				db.insertData(userName.getText());
			};

			if (data[0] == null)
				new Thread(run).start();

		} catch (Exception e) {
			// TODO: handle exception
		}
		enterBtn.getScene().getWindow().hide();

		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/com/quiz/quizController.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("İmtahan Simulyatoru v2.0");
			root.getStylesheets().add("/com/quiz/quizController.css");
			// stage.initModality(Modality.WINDOW_MODAL);
			// stage.initOwner(((Node) event.getSource()).getScene().getWindow());
			stage.show();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			DataBaseConnection db = new DataBaseConnection();
			data = db.selectData();
			label3.setText(label3.getText() + data[0]);

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					slider.setVisible(false);
					if (data[0] != null) {
						label3.setVisible(true);
						label.setVisible(false);
						enterBtn.setVisible(true);
					} else {
						label.setVisible(false);
						label2.setVisible(true);
						userName.setVisible(true);
						enterBtn.setVisible(true);
					}

					timer.cancel();
				}
			}, 3000);

		} catch (Exception e) {

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					slider.setVisible(false);

					label3.setVisible(true);
					label.setVisible(false);
					enterBtn.setVisible(true);
					label4.setVisible(true);

					timer.cancel();
				}
			}, 3000);

		}

	}

}