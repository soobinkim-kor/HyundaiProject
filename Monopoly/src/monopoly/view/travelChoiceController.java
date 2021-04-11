package monopoly.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class travelChoiceController {
	
	public travelChoiceController() {}

	@FXML private ChoiceBox<String> travelChoiceBox;
	
	
	
	

	
	public void travelToLocation() throws IOException {
		try {
			String value = (String) travelChoiceBox.getValue();
			
			
			if(value=="황금열쇠1") {
				value= "location3";
				
			}
			else if(value=="황금열쇠2") {
				value= "location11";
			}
			
			else {
				value= "location17";
			}
			
			//value값에 저장 location3, 11, 17 일때만 변환하면 될것같아요
			
			Stage stage = (Stage) travelChoiceBox.getScene().getWindow();
			stage.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
