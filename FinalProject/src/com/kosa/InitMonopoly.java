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
		}

		user.setTurn(user.getTurn() + 1);

		if (diceA.getDice() == diceB.getDice()) {
			/* �ý��� ���� */
			if (user.getTurn() == 2) {
				
			}

			/* �� �ֻ����� ���ڰ� �����ϸ� �� �� �� ���� */
			else {
				InitMonopolySystem(list, user);
			}
		}
		
	}

}
