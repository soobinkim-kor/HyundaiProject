package monopoly.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {
	@FXML
	private ImageView startView;

	// »ý¼ºÀÚ
	public RootController() {}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		startView.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event event) {
				try {
					Stage stage = (Stage) startView.getScene().getWindow();

					Stage primaryStage = new Stage();
					Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
					Scene scene = new Scene(main);
					primaryStage.setTitle("Monopoly");
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
					primaryStage.show();

					stage.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
