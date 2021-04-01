package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		{
//			Parent root= FXMLLoader.load(getClass().getResource("Root.fxml"));
			Parent GameScreen= FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
			Scene scene = new Scene(GameScreen);
//			Scene scene = new Scene(root);
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Monopoly Game");
			primaryStage.setResizable(false); 
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
