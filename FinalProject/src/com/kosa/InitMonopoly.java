package com.kosa;

import java.util.ArrayList;

public class InitMonopoly {
	
	public static void InitMonopolySystem(ArrayList<LocationVO> list, UsersVO user, int turn) {
		Dice diceA = new Dice();
		Dice diceB = new Dice();
		diceA.getDice();
		diceB.getDice();
		
		int now_idx = user.getNow();
		LocationVO data = (LocationVO)list.get(now_idx);
		String city = data.getCity();
		if (city != "Ȳ�ݿ���") {

		}
		
		else if (city == "Ȳ�ݿ���") {
//			if (Action 1: �������� ���� ������ �÷��̾ �湮�� ���) {
//				user1.setTurn(turn + 3);
//			}
		}
		

		
		
		
		
		
		
//		...
	}
	
	
}
