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
			
			
			if(value=="Ȳ�ݿ���1") {
				value= "location3";
				
			}
			else if(value=="Ȳ�ݿ���2") {
				value= "location11";
			}
			
			else {
				value= "location17";
			}
			
			//value���� ���� location3, 11, 17 �϶��� ��ȯ�ϸ� �ɰͰ��ƿ�
			
			Stage stage = (Stage) travelChoiceBox.getScene().getWindow();
			stage.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
