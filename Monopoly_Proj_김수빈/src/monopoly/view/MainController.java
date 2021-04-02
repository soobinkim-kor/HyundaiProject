package monopoly.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import monopoly.Main;
import monopoly.model.LocationDAO;
import monopoly.model.LocationVO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




import javafx.fxml.Initializable;
import javafx.scene.control.Button;
public class MainController{
//public class MainController {

	
	@FXML
	private Label location0, location1, location2, location3, location4, location5, location6,
				  location7, location8, location9, location10, location11, location12, location13, 
				  location14, location15, location16, location17, location18, location19, location20, 
				  location21;
	@FXML
	private Button startButton, exitButton;

	
	// 생성자
	public MainController() {}

//	public void Start() throws Exception {
//		Stage primaryStage = new Stage();
//		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);
//		primaryStage.setTitle("Monopoly");
//		primaryStage.show();
//	}
	
	public void Button(ActionEvent action) {
		/* 맵 데이터 초기화 */
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
	
	public void startButtonClick(ActionEvent event) throws IOException {

		try {
			
			Stage stage = (Stage) startButton.getScene().getWindow();
			stage.close();
			
			Stage primaryStage= new Stage();
		    Parent main = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
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
	
//	@Override
//	public void initialize(URL root, ResourceBundle AnchorPane) {
//		startButton.setOnAction(e->{
//				startButtonClick(e);
//
//		});
		
//		startButton.setOnMouseClicked( event -> { 	
//            System.out.println("test");
//            
//        });
//		
//		exitButton.setOnMouseClicked(event-> {
//			System.out.println("test1");
//		});
	}
	
	
//	@Override
//	public void initialize(URL root, ResourceBundle AnchorPane) {
//		startButton.setOnMouseClicked( event -> { 
//            System.out.println("test");
//            
//        });
//		
//		exitButton.setOnMouseClicked(event-> {
//			System.out.println("test1");
//		});
//	}
	

//	public void setMainApp(Main mainApp) {
//		this.setMainApp(mainApp);
//	}

