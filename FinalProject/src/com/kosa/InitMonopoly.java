package com.kosa;

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
			ArrayList<BuildingVO> locationList = dao.list(user.getIdx(), now_idx);

//			for (int i = 0; i < list.size(); i++) {
//				BuildingVO buildingData = (BuildingVO) locationList.get(i);
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
		}

		user.setTurn(user.getTurn() + 1);

		if (diceA.getDice() == diceB.getDice() && user.getTurn() != 2) {
			/* �� �ֻ����� ���ڰ� �����ϸ� �� �� �� ���� */
			InitMonopolySystem(list, user);
		}

	}

}
