package monopoly.model;

import java.util.ArrayList;

public class InitMonopoly {

	public static void InitMonopolySystem(ArrayList<LocationVO> list, UsersVO user) {
		Dice diceA = new Dice();
		Dice diceB = new Dice();
		diceA.getDice();
		diceB.getDice();
		int dice = diceA.getDice() + diceB.getDice();

		int now_idx = user.getNow() + dice;
		LocationVO data = (LocationVO) list.get(now_idx);
		String city = data.getCity();

		if (city != "Ȳ�ݿ���") {
			BuildingDAO dao = new BuildingDAO();
			ArrayList<BuildingVO> buildingList = dao.list(user.getIdx(), now_idx);

//			for (int i = 0; i < list.size(); i++) {
//				BuildingVO buildingData = (BuildingVO) buildingList.get(i);
//				int locationIdx = buildingData.getLocationIdx();
//				int typeIdx = buildingData.getTypeIdx();
//				int price = buildingData.getPrice();
//
//				System.out.println("locationIdx : " + locationIdx);
//				System.out.println("typeIdx : " + typeIdx);
//				System.out.println("price : " + price);
//			}

			/* �������� ���� ������ �÷��̾ �湮�� ��� */
//			if {
//				
//			}

			/* Ÿ �÷��̾�� �������� �ִ� ������ �÷��̾ �湮�� ��� */
//			else if {
//			
//			}

			/* �ش� �÷��̾�� �������� �ִ� ������ �÷��̾ �湮�� ��� */
//			else if {
//			
//		    }
		}

		else if (city == "Ȳ�ݿ���" || city == "����������") {
			/* �̺�Ʈ �߻� */
			EventDAO dao = new EventDAO();
			ArrayList<EventVO> EventList = dao.list(now_idx);

//			for (int i = 0; i < list.size(); i++) {
//				EventVO EventData = (EventVO) EventList.get(i);
//				int idx = EventData.getIdx();
//				int locationIdx = EventData.getLocationIdx();
//				String description = EventData.getDescription();
//
//				System.out.println("idx : " + idx);
//				System.out.println("locationIdx : " + locationIdx);
//				System.out.println("price : " + description);
//			}
		}

		user.setTurn(user.getTurn() + 1);

		if (diceA.getDice() == diceB.getDice() && user.getTurn() != 2) {
			/* �� �ֻ����� ���ڰ� �����ϸ� �� �� �� ���� */
			InitMonopolySystem(list, user);
		}

	}

}
