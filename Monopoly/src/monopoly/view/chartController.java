package monopoly.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class chartController implements Initializable {
	@FXML
	private Button chartExitButton;
	@FXML
	private Label data;

	public chartController() {
	};

	public void chartExitButtonClicked() {
		try {
			Stage stage = (Stage) chartExitButton.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void initData(String text) {
		data.setText(text);
	}
}
