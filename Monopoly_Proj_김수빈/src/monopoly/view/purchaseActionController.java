package monopoly.view;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class purchaseActionController {
	@FXML 
	private Button exitButton,confirmButton;
	
	@FXML
	private TextArea purchaseInfromtaion, priceInformation;
	
	public purchaseActionController() {}
	
	
	
	public void exitButtonClick(ActionEvent event) throws IOException{
		try {
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void confirmButtonClick(ActionEvent event) throws IOException{
		//���� �Ҷ� �ʿ��� �޼ҵ�
		//������ �����ϸ� ���Ź�ư Ŭ������ ������ ���� ��ҵ� �� ���� �� ���ƿ�
	}
	
}
