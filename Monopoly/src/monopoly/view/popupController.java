package monopoly.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class popupController {
	
	@FXML
	private Button popupButton;
	
	@FXML
	private Label popupLabel;
	
	public popupController() {}
	
	public void clickedPopupButton(ActionEvent event) throws IOException{
		try {
			Stage stage = (Stage) popupButton.getScene().getWindow();
			stage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
