package monopoly.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class purchaseChoiceController implements Initializable {

	@FXML
	private Button exitButton, purchaseSelected;

	@FXML
	private CheckBox buildMotel, buildBuilding, buildHotel, buildLandmark;

	@FXML
	private TextArea priceInformation;

	@FXML
	private Label motel;

	@FXML
	private Label building;

	@FXML
	private Label hotel;

	@FXML
	private Label landmark;

	public purchaseChoiceController() {
	};

	public void clickExitButton() throws IOException {
		try {
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void motelCheck(ActionEvent event) throws IOException {

		try {

			buildBuilding.setSelected(false);

			buildHotel.setSelected(false);

			buildLandmark.setSelected(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildingCheck() throws IOException {
		try {
			buildMotel.setSelected(false);
			buildMotel.setSelected(true);

			buildHotel.setSelected(false);

			buildLandmark.setSelected(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hotelCheck() throws IOException {

		try {
			buildMotel.setSelected(false);
			buildMotel.setSelected(true);

			buildBuilding.setSelected(false);
			buildBuilding.setSelected(true);

			buildLandmark.setSelected(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void landmarkCheck() throws IOException {
		try {

			buildMotel.setSelected(true);

			buildBuilding.setSelected(true);

			buildHotel.setSelected(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void purchaseSelectedClick() throws IOException {
		try {
			boolean motelCheck = buildMotel.isSelected();
			boolean buildingCheck = buildBuilding.isSelected();
			boolean hotelCheck = buildHotel.isSelected();
			boolean landmarkCheck = buildLandmark.isSelected();
			int totalPrice = 0;
			//
			if (motelCheck) {
				totalPrice = totalPrice + 1; // �̺κп� 1�� �޾ƿ� motel��
			}

			if (buildingCheck) {
				totalPrice = totalPrice + 10; // �̺κп� 10�� �޾ƿ� building��
			}

			if (hotelCheck) {
				totalPrice = totalPrice + 100; // �̺κп� 100�� �޾ƿ� building��
			}

			if (landmarkCheck) {
				totalPrice = totalPrice + 1000; // �̺κп� 1000�� �޾ƿ� building��
			}

			System.out.println("����: " + motelCheck + "\n����: " + buildingCheck + "\nȣ��: " + hotelCheck + "\n���帶ũ: "
					+ landmarkCheck);
			System.out.println(totalPrice);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void initData(String city) {
		motel.setText(city);
	}

}
