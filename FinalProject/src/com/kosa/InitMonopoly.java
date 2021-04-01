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

		if (city != "황금열쇠") {
			/* 소유권이 없는 지역을 플레이어가 방문한 경우 */
//			if {
//			
//			}
			
			/* 타 플레이어에게 소유권이 있는 지역을 플레이어가 방문한 경우 */
//			else if {
//			
//			}
			
			/* 해당 플레이어에게 소유권이 있는 지역을 플레이어가 방문한 경우 */
//			else if {
//			
//		    }
		}

		else if (city == "황금열쇠" || city == "우주정거장") {
			/* 이벤트 발생 */
		}

		user.setTurn(user.getTurn() + 1);

		if (diceA.getDice() == diceB.getDice()) {
			/* 시스템 종료 */
			if (user.getTurn() == 2) {
				
			}

			/* 두 주사위의 숫자가 동일하면 한 턴 더 진행 */
			else {
				InitMonopolySystem(list, user);
			}
		}
		
	}

}
