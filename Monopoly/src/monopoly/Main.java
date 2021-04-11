package monopoly;

import java.sql.Connection;
import java.util.ArrayList;

import monopoly.model.ExitProcedure;
import monopoly.model.LocationDAO;
import monopoly.model.LocationVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/monopoly/view/root.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Monopoly");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Connection conn = DBConnection.getConnection();

		LocationDAO dao = new LocationDAO();
		@SuppressWarnings("unused")
		ArrayList<LocationVO> locationList = dao.list();

		launch(args);
		@SuppressWarnings("unused")
		ExitProcedure exit = new ExitProcedure();
	}

}