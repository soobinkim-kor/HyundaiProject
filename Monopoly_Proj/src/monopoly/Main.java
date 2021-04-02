package monopoly;

import java.sql.Connection;
import java.util.ArrayList;

import monopoly.model.InitMonopoly;
import monopoly.model.LocationDAO;
import monopoly.model.LocationVO;
import monopoly.model.UsersDAO;
import monopoly.model.UsersVO;
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

		/* 시스템 데이터 초기화 */
		int turn = 0;
		boolean nowTurn = false;

		/* 맵 데이터 초기화 */
		LocationDAO dao = new LocationDAO();
		ArrayList<LocationVO> locationList = dao.list();

		/* 유저 데이터 초기화 */
		UsersVO user1 = new UsersVO();
		UsersVO user2 = new UsersVO();
		String user1_name = "abc";
		String user2_name = "cba";
		UsersDAO.Init(user1, user1_name);
		UsersDAO.Init(user2, user2_name);

//		while (user1.getMoney() >= 0 || user2.getMoney() >= 0 || turn <= 10) {
//			if (nowTurn == false) {
//				System.out.println("진입");
				InitMonopoly.InitMonopolySystem(locationList, user1);
				nowTurn = true;
//			}
//
//			else if (nowTurn == true) {
//				InitMonopoly.InitMonopolySystem(locationList, user2);
//				nowTurn = false;
//			}
//
//			turn++;
//
//			
//		}
		launch(args);

	}
}