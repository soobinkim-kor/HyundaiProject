package monopoly.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class firstPlayerPropertyController implements Initializable {
	@FXML
	private Button fppExitButton;
	@FXML
	private TextArea propertiesData;
	
	public firstPlayerPropertyController() {}
	
	public void fppExitButtonClick(ActionEvent event) throws IOException {

		try {
			Stage stage = (Stage) fppExitButton.getScene().getWindow();
			stage.close();

		 } catch(Exception e){

		       e.printStackTrace();

		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void initData(String data) {
	    propertiesData.setText(data);
	  }

}
