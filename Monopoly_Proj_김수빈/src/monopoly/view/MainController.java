package monopoly.view;

import java.io.IOException;
import java.util.ArrayList;
import monopoly.model.LocationDAO;
import monopoly.model.LocationVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainController{

	@FXML
	private Label location0, location1, location2, location3, location4, location5, location6,
				  location7, location8, location9, location10, location11, location12, location13, 
				  location14, location15, location16, location17, location18, location19, location20, 
				  location21, turnDisplayLabel, firstDiceValue, SecondDiceValue, firstPlayerMoney, secondPlayerMoney;
	@FXML
	private Button RollDiceButton, MainExitButton, firstPlayerPropertyButton, secondPlayerPropertyButton, chartViewButton, testButton;
	
	@FXML
	private TextArea text;
	
	
	// ������
	public MainController() {}

	
	public void Button(ActionEvent action) {
		/* �� ������ �ʱ�ȭ */
		LocationDAO dao = new LocationDAO();
		ArrayList<LocationVO> locationList = dao.list();
		ArrayList<Label> labelList = new ArrayList<Label>();
		InitLabelList(labelList);
		
		for (int i = 0; i < locationList.size(); i++) {
			labelList.get(i).setText(locationList.get(i).getCity());
		}
	}
	
	private void InitLabelList(ArrayList<Label> labelList) {
		labelList.add(location0);
		labelList.add(location1);
		labelList.add(location2);
		labelList.add(location3);
		labelList.add(location4);
		labelList.add(location5);
		labelList.add(location6);
		labelList.add(location7);
		labelList.add(location8);
		labelList.add(location9);
		labelList.add(location10);
		labelList.add(location11);
		labelList.add(location12);
		labelList.add(location13);
		labelList.add(location14);
		labelList.add(location15);
		labelList.add(location16);
		labelList.add(location17);
		labelList.add(location18);
		labelList.add(location19);
		labelList.add(location20);
		labelList.add(location21);
	}
	
	public void testPurchaseAction(ActionEvent event) throws IOException{

		try {		
			
			Stage primaryStage= new Stage();
		    Parent main = FXMLLoader.load(getClass().getResource("purchaseAction.fxml"));
		    Scene scene = new Scene(main);
		    primaryStage.setScene(scene);
            primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setMoney(ActionEvent event) throws IOException{
		//if������ player ����
		try {
			//���� �ҷ����� �޼ҵ�
			firstPlayerMoney.setText(null); //null�� ���� ���� ��
			//���� �ٲٴ� �޼ҵ�
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			//���� �ҷ����� �޼ҵ�
			secondPlayerMoney.setText(null); //null�� ���� ���� ��
			//���� �ٲٴ� �޼ҵ�
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void RollDiceButtonClick(ActionEvent event) throws IOException{
		System.out.println("�ֻ��� ������ ��ư");
		//���ʿ� purchaseAction �� �޼ҵ� ȣ�� or �����̼ǿ� �ǹ��� ���� �� �ٷ� �� ����
	}
	
	
	public void MainExitButtonClick(ActionEvent event) throws IOException{
		try {
			Stage stage = (Stage) MainExitButton.getScene().getWindow();
			stage.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void firstPlayerPropertyButtonClick(ActionEvent event) throws IOException{

		try {		
			
			Stage primaryStage= new Stage();
		    Parent main = FXMLLoader.load(getClass().getResource("firstPlayerProperty.fxml"));
		    Scene scene = new Scene(main);
		    primaryStage.setScene(scene);
            primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void secondPlayerPropertyButtonClick(ActionEvent event) throws IOException{
		
		try {

			Stage primaryStage= new Stage();
		    Parent main = FXMLLoader.load(getClass().getResource("secondPlayerProperty.fxml"));
		    Scene scene = new Scene(main);
		    primaryStage.setScene(scene);
            primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void viewChart(ActionEvent event) throws IOException{
		System.out.println("viewChart ����");
		try {

			Stage primaryStage= new Stage();
		    Parent main = FXMLLoader.load(getClass().getResource("chart.fxml"));
		    Scene scene = new Scene(main);
		    primaryStage.setScene(scene);
            primaryStage.show();
            
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

