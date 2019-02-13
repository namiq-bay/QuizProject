package com.mail;

import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.data.LoginPage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class MailController implements Initializable {

	@FXML
	private JFXTextField emailField;

	@FXML
	private JFXTextArea messField;

	@FXML
	private JFXTextField codeField;

	@FXML
	private Label errCode;

	@FXML
	private Label errMailLbl;
	@FXML
	private Label errMsg;

	@FXML
	private Label codeLbl;

	@FXML
	private JFXButton sendBtn;

	@FXML
	private Text verifyLbl;

	private static String code;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// get verification code between 1000 - 9999
		code = String.valueOf((int) (Math.random() * (8889)) + 1000);
		codeLbl.setText(code);

		codeField.setOnMouseClicked(event -> errCode.setVisible(false));
		emailField.setOnMouseClicked(event -> errMailLbl.setVisible(false));
		messField.setOnMouseClicked(event -> errMsg.setVisible(false));
	}

	@FXML
	void sendEvent(ActionEvent event) {
		Runnable run = () -> {
			try {
				String name = LoginPage.data[0];
				String email = emailField.getText();
				String mailBody = messField.getText();

				SendEmail.generateAndSendEmail(name + " : " + email + " :    " + mailBody);

			} catch (Exception e) {
			}
		};

		// check the email
		if (!emailField.getText().contains("@") || (!emailField.getText().contains(".")))
			errMailLbl.setVisible(true);

		// check the email body
		else if (messField.getText().trim().isEmpty())
			errMsg.setVisible(true);

		// check the verification code
		else if (!code.equals(codeField.getText().trim()))
			errCode.setVisible(true);

		else {
			emailField.setVisible(false);
			messField.setVisible(false);
			codeField.setVisible(false);
			codeLbl.setVisible(false);
			sendBtn.setVisible(false);

			verifyLbl.setVisible(true);

			new Thread(run).start();
		}

	}

}
