package monopoly.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameEndController implements Initializable {
	@FXML
	private Button exitButton;
	
	@FXML
	private LineChart<Number, Number> linechart;
	
	public void exitButtonClick(ActionEvent event) throws IOException{
		try {
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
			Platform.exit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void initData(XYChart.Series<Number, Number> playerA, XYChart.Series<Number, Number> playerB) {
		playerA.setName("Player 1");
		playerB.setName("Player 2");
		linechart.getData().add(playerA);
		linechart.getData().add(playerB);
	}
}
