package monopoly.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


import javafx.scene.control.Button;
public class RootController{


	
	@FXML
	private Button startButton, exitButton;

	
	// »ý¼ºÀÚ
	public RootController() {}

	
	public void startButtonClick(ActionEvent event) throws IOException {

		try {
			
			Stage stage = (Stage) startButton.getScene().getWindow();
			stage.close();
			
			Stage primaryStage= new Stage();
		    Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
		    Scene scene = new Scene(main);
		    primaryStage.setScene(scene);
            primaryStage.show();
            

		 } catch(Exception e){

		       e.printStackTrace();

		}
		
	}
	
	public void exitButtonClick(ActionEvent event) throws IOException{
		try {
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	


