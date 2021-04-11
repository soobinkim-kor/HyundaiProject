package monopoly.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import monopoly.model.InitMonopoly;
import monopoly.model.LocationDAO;
import monopoly.model.LocationVO;
import monopoly.model.PropertiesVO;
import monopoly.model.UsersDAO;
import monopoly.model.UsersVO;

public class MainController implements Initializable {
	@FXML
	private Label turnDisplayLabel;
	@FXML
	private Label firstDiceValue;
	@FXML
	private Label secondDiceValue;

	@FXML
	private TextField firstPlayerName, secondPlayerName;

	@FXML
	private Label firstPlayerMoney, secondPlayerMoney;

	@FXML
	private Button RollDiceButton, MainExitButton, firstPlayerPropertyButton, secondPlayerPropertyButton,
			chartViewButton, purchaseSelected;
	@FXML
	private ImageView ExitButton;
	@FXML
	private ImageView view;
	@FXML
	private ImageView view2;

	@FXML
	private Label location1, location2, location3, location4, location5, location6, location7, location8, location9,
			location10, location11, location12, location13, location14, location15, location16, location17, location18,
			location19, location20, location21, location0;

	@FXML
	private CheckBox buildMotel, buildBuilding, buildHotel, buildLandmark;

	@FXML
	private TextArea priceInformation;

	@FXML
	private Label priceInfo;

	@FXML
	private Label motel, building, hotel, landmark;

	// �ý��� ������ �ʱ�ȭ
	private int turn = 0;
	private int muindoUser1 = 0;
	private int muindoUser2 = 0;
	boolean nowTurn = false;

	// ���� ������ ����
	UsersVO user1 = new UsersVO();
	UsersVO user2 = new UsersVO();
	Stack<Integer> dataList1 = new Stack<Integer>();
	Stack<Integer> dataList2 = new Stack<Integer>();
	Stack<Integer> user1MoneyFlow = new Stack<Integer>();
	Stack<Integer> user2MoneyFlow = new Stack<Integer>();
	List<userProperties> user1Properties = new ArrayList<userProperties>();
	List<userProperties> user2Properties = new ArrayList<userProperties>();

	// �� ������ ����
	LocationDAO dao = new LocationDAO();

	// ������
	public MainController() {
		UsersDAO.Init(user1, 1, "�����");
		UsersDAO.Init(user2, 2, "������");
	}

	public void MainExitButtonClick(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage) MainExitButton.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void firstPlayerPropertyButtonClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("firstPlayerProperty.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		firstPlayerPropertyController pop = loader.getController();

		String propertiesList = "";

		for (int i = 0; i < user1Properties.size(); i++) {
			propertiesList += user1Properties.get(i).toString() + "\n";
		}

		pop.initData(propertiesList);

		Stage stage = new Stage();
		stage.setTitle("�÷��̾� 1 �ڻ� ���");
		stage.setScene(scene);
		stage.show();
	}

	public void secondPlayerPropertyButtonClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("secondPlayerProperty.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		secondPlayerPropertyController pop = loader.getController();

		String propertiesList = "";

		for (int i = 0; i < user2Properties.size(); i++) {
			propertiesList += user2Properties.get(i).toString() + "\n";
		}

		pop.initData(propertiesList);

		Stage stage = new Stage();
		stage.setTitle("�÷��̾� 2 �ڻ� ���");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void gameEnd() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GameEnd.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);

		XYChart.Series<Number, Number> firstPlayer = new XYChart.Series<Number, Number>();
		XYChart.Series<Number, Number> secondPlayer = new XYChart.Series<Number, Number>();

		GameEndController pop = loader.getController();

		firstPlayer.getData().add(new XYChart.Data<Number, Number>(0, 50000));
		for (int i = 1; i < user1MoneyFlow.size(); i++) {
			firstPlayer.getData().add(new XYChart.Data<Number, Number>(i, user1MoneyFlow.get(i)));
		}

		secondPlayer.getData().add(new XYChart.Data<Number, Number>(0, 50000));
		for (int i = 1; i < user2MoneyFlow.size(); i++) {
			secondPlayer.getData().add(new XYChart.Data<Number, Number>(i, user2MoneyFlow.get(i)));
		}

		pop.initData(firstPlayer, secondPlayer);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("���");
		stage.show();
	}

	public void RollDiceButtonClick(ActionEvent event) throws IOException {
		/* ����1 ���ε� ���� */
		if (muindoUser1 > 0) {
			muindoUser1--;
			setDataListUser2();
			user2.setTurn(user2.getTurn() - 1);
		}

		/* ����2 ���ε� ���� */
		else if (muindoUser2 > 0) {
			muindoUser2--;
			setDataListUser1();
			user1.setTurn(user1.getTurn() - 1);
		}

		/* ����1, ����2 ���ε� ���� */
		else if (muindoUser1 > 0 && muindoUser2 > 0) {
			muindoUser1--;
			muindoUser2--;
		}

		else {
			if (nowTurn == false) {

				setDataListUser1();
				nowTurn = true;

				Glow glow = new Glow();
				glow.setLevel(0.9);

				firstPlayerName.setEffect(glow);
				firstPlayerMoney.setEffect(null);
				firstPlayerPropertyButton.setEffect(glow);
				secondPlayerName.setEffect(null);
				secondPlayerMoney.setEffect(glow);
				secondPlayerPropertyButton.setEffect(null);

				Light.Spot light = new Light.Spot();
				light.setColor(Color.BLUE);
				light.setX(70);
				light.setY(55);
				light.setZ(45);
				Lighting lighting = new Lighting();
				lighting.setLight(light);
				RollDiceButton.setEffect(lighting);
			}

			else if (nowTurn == true) {

				setDataListUser2();
				nowTurn = false;
				Glow glow = new Glow();
				glow.setLevel(0.9);
				secondPlayerName.setEffect(glow);
				secondPlayerMoney.setEffect(null);
				secondPlayerPropertyButton.setEffect(glow);
				firstPlayerName.setEffect(null);
				firstPlayerMoney.setEffect(glow);
				firstPlayerPropertyButton.setEffect(null);

				Light.Spot light = new Light.Spot();
				light.setColor(Color.YELLOW);
				light.setX(70);
				light.setY(55);
				light.setZ(45);
				Lighting lighting = new Lighting();
				lighting.setLight(light);
				RollDiceButton.setEffect(lighting);
			}
		}
	}

	public void setDataListUser1() {
		Stack<Integer> dataList1 = InitGame(user1);
		dataSet(dataList1.get(1), dataList1.get(2), dataList1.get(3), dataList1.get(4), dataList1.get(5));

		movePlayer(user1);

		if (dataList1.get(0) == -1) {
			muindoUser1 = 2;
		}
	}

	public void setDataListUser2() {
		Stack<Integer> dataList2 = InitGame(user2);
		dataSet(dataList2.get(1), dataList2.get(2), dataList2.get(3), dataList2.get(4), dataList2.get(5));

		movePlayer(user2);

		if (dataList2.get(0) == -1) {
			muindoUser2 = 2;
		}
	}

	public void dataSet(int moneyUserA, int moneyUserB, int diceA, int diceB, int turn) {
		Integer userA = Integer.valueOf(moneyUserA);
		Integer userB = Integer.valueOf(moneyUserB);
		Integer dice = Integer.valueOf(diceA);
		Integer dice2 = Integer.valueOf(diceB);
		Integer turnA = Integer.valueOf(turn);

		if (nowTurn == false)
			user1MoneyFlow.add(moneyUserA);
		else if (nowTurn == true)
			user2MoneyFlow.add(moneyUserB);

		firstDiceValue.setText(dice.toString());
		secondDiceValue.setText(dice2.toString());
		turnDisplayLabel.setText(turnA.toString());
		firstPlayerMoney.setText(userA.toString());
		secondPlayerMoney.setText(userB.toString());

		if (userA == 0 || userB == 0 || turn == 10) {
			try {
				gameEnd();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void movePlayer(UsersVO user) {
		ArrayList<Label> labelList = InitLabelList(new ArrayList<Label>());

		if (user.getIdx() == 1) {
			view.setLayoutX(labelList.get(user.getNow()).getLayoutX());
			view.setLayoutY(labelList.get(user.getNow()).getLayoutY());
		}

		else if (user.getIdx() == 2) {
			view2.setLayoutX(labelList.get(user.getNow()).getLayoutX());
			view2.setLayoutY(labelList.get(user.getNow()).getLayoutY());
		}
	}

	public Stack<Integer> InitGame(UsersVO user) {
		if (turn == 0) {
			user.setMoney(50000);
		}

		/* �� ������ �ʱ�ȭ */
		ArrayList<LocationVO> locationList = dao.list();

		/* ���� ������ �ʱ�ȭ */
		ArrayList<PropertiesVO> user1PropertiesList = new ArrayList<PropertiesVO>();
		ArrayList<PropertiesVO> user2PropertiesList = new ArrayList<PropertiesVO>();
		Stack<Integer> initialization = new Stack<Integer>();
		ArrayList<String> buildingList = new ArrayList<String>();
		String eventLog = "";

		if (nowTurn == false) {
			initialization = InitMonopoly.InitMonopolySystem(locationList, user1);
			buildingList = InitMonopoly.buildingData(user1);
			user1PropertiesList = InitMonopoly.propertiesData(user1);
			eventLog = InitMonopoly.eventData(user1);
		}

		else if (nowTurn == true) {
			initialization = InitMonopoly.InitMonopolySystem(locationList, user2);
			buildingList = InitMonopoly.buildingData(user2);
			user2PropertiesList = InitMonopoly.propertiesData(user2);
			eventLog = InitMonopoly.eventData(user2);
		}

		if (user1.getTurn() >= user2.getTurn()) {
			turn = user2.getTurn();
		} else {
			turn = user1.getTurn();
		}

		if (buildingList.size() != 0) {
			motel.setVisible(true);
			building.setVisible(true);
			hotel.setVisible(true);
			landmark.setVisible(true);
			buildMotel.setVisible(true);
			buildBuilding.setVisible(true);
			buildHotel.setVisible(true);
			buildLandmark.setVisible(true);
			purchaseSelected.setVisible(true);
			priceInfo.setText("���� ���� ����");
			motel.setText(buildingList.get(0));
			building.setText(buildingList.get(1));
			hotel.setText(buildingList.get(2));
			landmark.setText(buildingList.get(3));
		} else {
			priceInfo.setText(eventLog);
			motel.setVisible(false);
			building.setVisible(false);
			hotel.setVisible(false);
			landmark.setVisible(false);
			buildMotel.setVisible(false);
			buildBuilding.setVisible(false);
			buildHotel.setVisible(false);
			buildLandmark.setVisible(false);
			purchaseSelected.setVisible(false);
		}

		// buildingList�� propertiesList Matching
		if (nowTurn == false) {
			for (int i = 0; i < user1PropertiesList.size(); i++) {
				userProperties userPropertyData = new userProperties();

				int j = user1PropertiesList.get(i).getBuildingLocationIdx() - 1;
				int k = user1PropertiesList.get(i).getBuildingTypeIdx();
				String type = "";
				System.out.println("����: " + user1PropertiesList.get(i).getUsersIdx());
				System.out.println("���ø�: " + locationList.get(j).getCity());
				System.out.println("�ǹ�Ÿ��: " + user1PropertiesList.get(i).getBuildingTypeIdx());
				if (k == 1) {
					type = "����";
					System.out.println("�ǹ�Ÿ��: ����");
				} else if (k == 2) {
					type = "����";
					System.out.println("�ǹ�Ÿ��: ����");
				} else if (k == 3) {
					type = "ȣ��";
					System.out.println("�ǹ�Ÿ��: ȣ��");
				} else if (k == 4) {
					type = "���帶ũ";
					System.out.println("�ǹ�Ÿ��: ���帶ũ");
				}
				System.out.println("����: " + user1PropertiesList.get(i).getFine());

				userPropertyData.setUserIdx(user1PropertiesList.get(i).getUsersIdx());
				userPropertyData.setCity(locationList.get(j).getCity());
				userPropertyData.setType(type);
				userPropertyData.setFine(user1PropertiesList.get(i).getFine());

				user1Properties.add(userPropertyData);
			}
		}

		else if (nowTurn == true) {
			for (int i = 0; i < user2PropertiesList.size(); i++) {
				userProperties userPropertyData = new userProperties();

				int j = user2PropertiesList.get(i).getBuildingLocationIdx() - 1;
				int k = user2PropertiesList.get(i).getBuildingTypeIdx();
				String type = "";
				System.out.println("����: " + user2PropertiesList.get(i).getUsersIdx());
				System.out.println("���ø�: " + locationList.get(j).getCity());
				System.out.println("�ǹ�Ÿ��: " + user2PropertiesList.get(i).getBuildingTypeIdx());
				if (k == 1) {
					type = "����";
					System.out.println("�ǹ�Ÿ��: ����");
				} else if (k == 2) {
					type = "����";
					System.out.println("�ǹ�Ÿ��: ����");
				} else if (k == 3) {
					type = "ȣ��";
					System.out.println("�ǹ�Ÿ��: ȣ��");
				} else if (k == 4) {
					type = "���帶ũ";
					System.out.println("�ǹ�Ÿ��: ���帶ũ");
				}
				System.out.println("����: " + user2PropertiesList.get(i).getFine());

				userPropertyData.setUserIdx(user2PropertiesList.get(i).getUsersIdx());
				userPropertyData.setCity(locationList.get(j).getCity());
				userPropertyData.setType(type);
				userPropertyData.setFine(user2PropertiesList.get(i).getFine());

				user2Properties.add(userPropertyData);
			}
		}

		initialization.add(turn);

		return initialization;

	}

	public void motelCheck() throws IOException {

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

	public void clickedPurchaseButton() throws IOException {
		try {
			Stage primaryStage = new Stage();
			Parent main = FXMLLoader.load(getClass().getResource("popup.fxml"));
			Scene scene = new Scene(main);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Monopoly");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Label> InitLabelList(ArrayList<Label> labelList) {
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

		return labelList;
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

			// ���� �� ���� ��� ���� ������Ʈ

			System.out.println("����: " + motelCheck + "\n����: " + buildingCheck + "\nȣ��: " + hotelCheck + "\n���帶ũ: "
					+ landmarkCheck);
			System.out.println(totalPrice);

			buildMotel.setSelected(false);
			buildBuilding.setSelected(false);
			buildHotel.setSelected(false);
			buildLandmark.setSelected(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ExitButton.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event event) {
				try {
					Stage stage = (Stage) ExitButton.getScene().getWindow();
					stage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
