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
		if (city != "황금열쇠") {

		}
		
		else if (city == "황금열쇠") {
//			if (Action 1: 소유권이 없는 지역을 플레이어가 방문한 경우) {
//				user1.setTurn(turn + 3);
//			}
		}
		

		
		
		
		
		
		
//		...
	}
	
	
}
