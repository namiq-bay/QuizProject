package com.rank;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.dataBase.DataBaseConnection;
import com.dataBase.QuizData;
import com.dataBase.SelectQuizData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;

public class RankController implements Initializable {

	@FXML
	public ListView<String> noList;

	@FXML
	public ListView<String> uNameList;

	@FXML
	public ListView<String> fennList;

	@FXML
	public ListView<String> errList;

	@FXML
	public ListView<String> corrList;

	DataBaseConnection db = new DataBaseConnection();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				Node n1 = uNameList.lookup(".scroll-bar");
				if (n1 instanceof ScrollBar) {
					final ScrollBar bar1 = (ScrollBar) n1;
					Node n2 = fennList.lookup(".scroll-bar");
					if (n2 instanceof ScrollBar) {
						final ScrollBar bar2 = (ScrollBar) n2;
						bar1.valueProperty().bindBidirectional(bar2.valueProperty());
					}
				}
				Node n3 = noList.lookup(".scroll-bar");
				if (n3 instanceof ScrollBar) {
					final ScrollBar bar1 = (ScrollBar) n3;
					Node n2 = fennList.lookup(".scroll-bar");
					if (n2 instanceof ScrollBar) {
						final ScrollBar bar2 = (ScrollBar) n2;
						bar1.valueProperty().bindBidirectional(bar2.valueProperty());
					}
				}
				Node n4 = errList.lookup(".scroll-bar");
				if (n3 instanceof ScrollBar) {
					final ScrollBar bar1 = (ScrollBar) n4;
					Node n2 = fennList.lookup(".scroll-bar");
					if (n2 instanceof ScrollBar) {
						final ScrollBar bar2 = (ScrollBar) n2;
						bar1.valueProperty().bindBidirectional(bar2.valueProperty());
					}
				}
				Node n5 = corrList.lookup(".scroll-bar");
				if (n3 instanceof ScrollBar) {
					final ScrollBar bar1 = (ScrollBar) n5;
					Node n2 = fennList.lookup(".scroll-bar");
					if (n2 instanceof ScrollBar) {
						final ScrollBar bar2 = (ScrollBar) n2;
						bar1.valueProperty().bindBidirectional(bar2.valueProperty());
					}
				}
			}
		});

		try {
		
		SelectQuizData obj = new SelectQuizData();
		obj.selectData();

		QuizData data[] = SelectQuizData.data;

		for (QuizData i : data) {
			if (i == null)
				break;

			noList.getItems().add(0, i.id);
			uNameList.getItems().add(0, i.userName);
			fennList.getItems().add(0, i.quizName);
			errList.getItems().add(0, i.falseCount);
			corrList.getItems().add(0, i.trueCount);
		}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aktiv Internet əlaqəsi yoxdur və ya VB ilə əlaqə qurmaq olmur.");
		}
		
		
	}
}