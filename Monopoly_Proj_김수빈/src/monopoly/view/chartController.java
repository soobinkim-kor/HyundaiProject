package monopoly.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class chartController {
	@FXML private Button chartExitButton;
	
	public chartController() {};
	
	public void chartExitButtonClicked(){
		try {
			Stage stage = (Stage) chartExitButton.getScene().getWindow();
			stage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
