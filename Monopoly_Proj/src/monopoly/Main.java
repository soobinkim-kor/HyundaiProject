package monopoly;
    
import java.sql.Connection;
import java.util.ArrayList;

import monopoly.model.LocationDAO;
import monopoly.model.LocationVO;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
 
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/monopoly/view/Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Monopoly");
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	@SuppressWarnings("unused")
		Connection conn = DBConnection.getConnection();
    	
        launch(args);
    }
}