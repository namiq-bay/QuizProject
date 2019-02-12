package com.quiz;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.dataBase.InsertExamResult;
import com.hash.Md5Hashing;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class QuizController implements Initializable{

	@FXML
	private JFXButton button;

	@FXML
	private VBox vBox1;

	@FXML
	private VBox vBox2;

	@FXML
	private HBox hBox1;

	@FXML
	private JFXRadioButton buttonA;

	@FXML
	private ToggleGroup group;

	@FXML
	private HBox hBox2;

	@FXML
	private JFXRadioButton buttonB;

	@FXML
	private HBox hBox3;

	@FXML
	private JFXRadioButton buttonC;

	@FXML
	private HBox hBox4;

	@FXML
	private JFXRadioButton buttonD;

	@FXML
	private HBox hBox5;

	@FXML
	private JFXRadioButton buttonE;

	@FXML
	private Label trueLbl;

	@FXML
	private Label falseLbl;

	@FXML
	private JFXTextArea label;

	@FXML
	private Label sign2;

	@FXML
	private Label mainLabel;

	@FXML
	private JFXButton button10;

	@FXML
	private JFXButton button50;

	@FXML
	private JFXButton buttonAll;

	@FXML
	private Label sign1;

	@FXML
	private JFXTextArea falseResoultLbl;
	@FXML
	private MenuItem exitBtn;

	@FXML
	public JFXComboBox<String> courseCh;

	@FXML
	private JFXComboBox<String> examCh;

	@FXML
	private JFXComboBox<String> questCh;

	@FXML
	private JFXButton startBtn;

	private int examType;
	private static String rightAnswerStr = "";

	public static ObservableList<String> corses = FXCollections.observableArrayList("Kompüterin arxitekturası və Ə.S",
			"İnformasiya sistemləri və verilənlər bazası", "İnformasiyanın qorunması üsulları və P.T");

	ObservableList<String> exam = FXCollections.observableArrayList("10 Sual", "50 Sual", "Bütün Suallar");

	ObservableList<String> quest = FXCollections.observableArrayList("Qarışıq", "Ardıcıl");

	Quiz questions[] = new Quiz[500];;

	private Integer[] arr;
	static String path;
	static String quizName;
	static int trueCount = 0;
	static int falseCount = 0;
	int currentQuiz = 0;
	int quizMax = 0;

	Md5Hashing md5 = new Md5Hashing();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courseCh.setItems(corses);
		examCh.setItems(exam);
		questCh.setItems(quest);
		
		exitBtn.setOnAction((event)->{
			System.exit(0);
		});
	}

	@FXML
	public void onEnter(KeyEvent event) {
		if (event.getCode().toString().equals("ENTER"))
			buttonAction(null);
	}

	@FXML
	void startEvent(ActionEvent event) {
		
		if (courseCh.getSelectionModel().isEmpty()) {
			courseCh.setFocusColor(Color.web("#E3AE57"));
			courseCh.requestFocus();
		} else if (examCh.getSelectionModel().isEmpty()) {
			examCh.setFocusColor(Color.web("#E3AE57"));
			examCh.requestFocus();
		} else if (questCh.getSelectionModel().isEmpty()) {
			questCh.setFocusColor(Color.web("#E3AE57"));
			questCh.requestFocus();
		} else {

			if (courseCh.getSelectionModel().isSelected(0)) {
				arr = new Integer[500];
				path = "kaes.txt";

				if (!md5.getCheckHash(path)) {
					JOptionPane.showMessageDialog(null, "Fayf dəyişdirilib! Program təminatçısına müraciət edin.");
					System.exit(0);
				}

			} else if (courseCh.getSelectionModel().isSelected(1)) {
				arr = new Integer[250];
				path = "isvb.txt";

				if (!md5.getCheckHash(path)) {
					JOptionPane.showMessageDialog(null, "Fayf dəyişdirilib! Program təminatçısına müraciət edin.");
					System.exit(0);
				}

			} else if (courseCh.getSelectionModel().isSelected(2)) {
				arr = new Integer[300];
				path = "iqupt.txt";

				if (!md5.getCheckHash(path)) {
					JOptionPane.showMessageDialog(null, "Fayf dəyişdirilib! Program təminatçısına müraciət edin.");
					System.exit(0);
				}

			}

			for (int i = 0; i < arr.length; i++)
				arr[i] = i;

			if (questCh.getSelectionModel().isSelected(0))
				Collections.shuffle(Arrays.asList(arr));

			if (examCh.getSelectionModel().isSelected(0))
				examType = 1;

			else if (examCh.getSelectionModel().isSelected(1))
				examType = 2;

			else if (examCh.getSelectionModel().isSelected(2))
				examType = 3;

			creatQuiz();
			setQuiz(arr[currentQuiz]);
			settterMethod();

			quizName = courseCh.getSelectionModel().getSelectedItem();
		}
	}

	public void buttonAction(ActionEvent event) {
		boolean control;
		Runnable run = () -> { InsertExamResult.insert(quizName, trueCount, falseCount); };

		if (buttonA.isSelected()) {
			control = Control(Quiz.Answer.answer_A);
			buttonA.setSelected(false);
		} else if (buttonB.isSelected()) {
			control = Control(Quiz.Answer.answer_B);
			buttonB.setSelected(false);
		} else if (buttonC.isSelected()) {
			control = Control(Quiz.Answer.answer_C);
			buttonC.setSelected(false);
		} else if (buttonD.isSelected()) {
			buttonD.setSelected(false);
			control = Control(Quiz.Answer.answer_D);
		} else if (buttonE.isSelected()) {
			buttonE.setSelected(false);
			control = Control(Quiz.Answer.answer_E);
		} else {
			control = false;
		}

		if (control)
			trueLbl.setText(String.valueOf(++trueCount));
		else
			falseLbl.setText(String.valueOf(++falseCount));

		switch (examType) {

		case 1:

			setQuiz(arr[++currentQuiz]);
			if (currentQuiz == 10) {
				button.setDisable(true);
				vBox1.setVisible(false);
				falseResoultLbl.setVisible(true);
				falseResoultLbl.setText(falseResoultLbl.getText() + "\n" + rightAnswerStr);
				label.setText("İmtahan bitdi! Təbriklər: " + trueCount + " düz cavab və " + falseCount
						+ " səhv cavab verdin!");
				
				new Thread(run).start();
			}
			break;
		case 2:
			setQuiz(arr[++currentQuiz]);
			if (currentQuiz == 50) {
				button.setDisable(true);
				vBox1.setVisible(false);
				falseResoultLbl.setVisible(true);
				falseResoultLbl.setVisible(true);
				falseResoultLbl.setText(falseResoultLbl.getText() + "\n" + rightAnswerStr);
				label.setText("İmtahan bitdi! Təbriklər: " + trueCount + " düz cavab və " + falseCount
						+ " səhv cavab verdin!");

				new Thread(run).start();
			}
			break;

		default:
			setQuiz(arr[++currentQuiz]);
			if (currentQuiz == quizMax - 1) {
				button.setDisable(true);
				vBox1.setVisible(false);
				falseResoultLbl.setVisible(true);
				falseResoultLbl.setVisible(true);
				falseResoultLbl.setText(falseResoultLbl.getText() + "\n" + rightAnswerStr);
				label.setText("İmtahan bitdi! Təbriklər: " + trueCount + " düz cavab və " + falseCount
						+ " səhv cavab verdin!");

				new Thread(run).start();
				
			}
			break;
		}
	}

	public void restartEvent(ActionEvent event) {
		mainLabel.setVisible(true);

		sign1.setVisible(true);
		courseCh.setVisible(true);
		questCh.setVisible(true);
		examCh.setVisible(true);
		questCh.setVisible(true);
		startBtn.setVisible(true);
		examCh.getSelectionModel().clearSelection();
		courseCh.getSelectionModel().clearSelection();
		questCh.getSelectionModel().clearSelection();
		label.setVisible(false);
		vBox1.setVisible(false);
		vBox2.setVisible(false);
		sign2.setVisible(false);
		button.setVisible(false);
		falseResoultLbl.setVisible(false);

		rightAnswerStr = "";
		falseResoultLbl.setText("");
		currentQuiz = 0;
		trueCount = 0;
		falseCount = 0;
		quizMax = 0;
		trueLbl.setText("");
		falseLbl.setText("");

	}

	public void lastUsers(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/com/rank/rankController.fxml"));

			stage.setScene(new Scene(root));
			stage.setTitle("İmtahan Simulyatoru v2.0");
			root.getStylesheets().add("/com/quiz/quizController.css");
			// stage.initModality(Modality.WINDOW_MODAL);
			// stage.initOwner(((Node) event.getSource()).getScene().getWindow());
			stage.show();

		} catch (Exception e) {

		}

	}

	public void creatQuiz() {

		InputStream inputStream = this.getClass().getResourceAsStream("/com/exams/" + path);

		try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {

			while (scanner.hasNextLine()) {
				String question = scanner.nextLine().trim();
				String optionA = scanner.nextLine().trim();
				String optionB = scanner.nextLine().trim();
				String optionC = scanner.nextLine().trim();
				String optionD = scanner.nextLine().trim();
				String optionE = scanner.nextLine().trim();
				String rightAnswerString = scanner.nextLine().trim();

				Quiz.Answer rightAnswer = null;

				if (rightAnswerString.equals("A"))
					rightAnswer = Quiz.Answer.answer_A;
				else if (rightAnswerString.equals("B"))
					rightAnswer = Quiz.Answer.answer_B;
				else if (rightAnswerString.equals("C"))
					rightAnswer = Quiz.Answer.answer_C;
				else if (rightAnswerString.equals("D"))
					rightAnswer = Quiz.Answer.answer_D;
				else if (rightAnswerString.equals("E"))
					rightAnswer = Quiz.Answer.answer_E;

				if (rightAnswer != null) {
					questions[quizMax] = new Quiz(question, optionA, optionB, optionC, optionD, optionE, rightAnswer);
					quizMax++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String charcterControl(String line) {
		int n = line.length();
		int spaceIndex = 0;
		int index = 0;
		String cutingLine = null;
		if (n > 90) {
			cutingLine = line.substring(90, line.length());
			index = cutingLine.indexOf(' ');
			spaceIndex = index + 90;
			line = line.substring(0, spaceIndex) + '\n' + line.substring(spaceIndex + 1);
		}
		if (n > 180) {
			cutingLine = line.substring(180, line.length());
			index = cutingLine.indexOf(' ');
			spaceIndex = index + 180;
			line = line.substring(0, spaceIndex) + '\n' + line.substring(spaceIndex + 1);
		}

		return line;
	}

	public void setQuiz(int quizNo) {

		label.setText(" " + questions[quizNo].quizString);
		buttonA.setText(charcterControl(questions[quizNo].optionA));
		buttonB.setText(charcterControl(questions[quizNo].optionB));
		buttonC.setText(charcterControl(questions[quizNo].optionC));
		buttonD.setText(charcterControl(questions[quizNo].optionD));
		buttonE.setText(charcterControl(questions[quizNo].optionE));
	}

	public boolean Control(Quiz.Answer answer) {
		boolean bool;
		bool = questions[arr[currentQuiz]].answerControl(answer);

		if (!bool) {

			switch (questions[arr[currentQuiz]].rightAswer) {
			case answer_A:
				rightAnswerStr += arr[currentQuiz] + 1 + " : A, ";
				break;
			case answer_B:
				rightAnswerStr += arr[currentQuiz] + 1 + " : B, ";
				break;
			case answer_C:
				rightAnswerStr += arr[currentQuiz] + 1 + " : C, ";
				break;
			case answer_D:
				rightAnswerStr += arr[currentQuiz] + 1 + " : D, ";
				break;
			case answer_E:
				rightAnswerStr += arr[currentQuiz] + 1 + " : E, ";
				break;
			default:
				break;
			}
		}
		return bool;
	}

	public void settterMethod() {
		mainLabel.setVisible(false);
		sign1.setVisible(false);
		courseCh.setVisible(false);
		examCh.setVisible(false);
		questCh.setVisible(false);
		startBtn.setVisible(false);

		label.setVisible(true);
		vBox1.setVisible(true);
		vBox2.setVisible(true);
		sign2.setVisible(true);
		button.setVisible(true);
		button.setDisable(false);
	}

	@FXML
	void aboutAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/About.fxml"));
			stage.setScene(new Scene(root));
			// root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setResizable(false);
			stage.setTitle("Əlaqə");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}