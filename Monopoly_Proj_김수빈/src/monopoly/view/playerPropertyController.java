package monopoly.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class playerPropertyController {
	@FXML
	private Button fppExitButton, sppExitButton;
	
	public playerPropertyController(){}
	
	public void fppExitButtonClick(ActionEvent event) throws IOException {

		try {
			Stage stage = (Stage) fppExitButton.getScene().getWindow();
			stage.close();      

		 } catch(Exception e){

		       e.printStackTrace();

		}
		
	}
	
	public void sppExitButtonClick(ActionEvent event) throws IOException {

		try {
			Stage stage = (Stage) sppExitButton.getScene().getWindow();
			stage.close();      

		 } catch(Exception e){

		       e.printStackTrace();

		}
		
	}
}
