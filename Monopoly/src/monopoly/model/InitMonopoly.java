package monopoly.model;

import java.util.ArrayList;
import java.util.Stack;

public class InitMonopoly {
	public static Stack<Integer> InitMonopolySystem(ArrayList<LocationVO> list, UsersVO user) {
		final int MAP_SIZE = 21;
		Stack<Integer> returnDataList = new Stack<Integer>();
		
		/* �ֻ��� ������ */
		Dice diceA = new Dice();
		Dice diceB = new Dice();
		diceA.getDice();
		diceB.getDice();
		
		/* ���� �ֻ����� ������ ���� ��ġ�� ���� */
		int dice = diceA.getDice() + diceB.getDice();
		int now_idx = user.getNow() + dice;
		
		/* �� ��ü �ε������� ���� �ε����� Ŀ���� ���� �ε��� �ʱ�ȭ*/
		if (now_idx > MAP_SIZE) {
			now_idx -= MAP_SIZE + 1;
			
			/* �ѹ��� ���� 2000�� ȹ�� */ 
			EventDAO eventDao = new EventDAO();
			eventDao.moveOne(user);
		}
		
		/* �̵��� ��ġ�� ������� ���ø� �˻� */
		user.setNow(now_idx);
		LocationVO data = list.get(now_idx);
		String city = data.getCity();
		System.out.println("���� ��� : " + city);
		
		/* ������ �ε����� �Ű������� ������ġ�� ���� */
		UsersDAO.spMove(user);
		
		/* ����� ������ ������ġ�� �����ͺ��̽��� ������Ʈ */
		UsersDAO.getUserCursor(user);
		
		/* �̺�Ʈ ���� ���� üũ */
		EventDAO eventDao = new EventDAO();
		int eventFlag = eventDao.eventCheck(user);

		/* �̺�Ʈ�� �����ϸ� */
		if (eventFlag == 1) {
			System.out.println("�̺�Ʈ �����Դϴ�.");
			/* ���� �̺�Ʈ �ޱ� �� ����*/
			int flag = eventDao.eventExecute(user, eventDao.getRandomEvent(user));
			
			if (flag == 0) {
				eventDao.eventCheck(user);
				returnDataList.add(1);
			}
			else if (flag == 1) {
				System.out.println("���ε� �̺�Ʈ");
				returnDataList.add(-1);
			}
			else if (flag == 2) {
				System.out.println("���ּ� �̺�Ʈ");
				eventDao.moveSpaceship(user);
				returnDataList.add(1);
			}
			
			System.out.println();
			UsersDAO.getUserCursor(user);
			// �̹��� ���� �̵� ó�� �ʿ�
		}

		/* �̺�Ʈ�� �������� ������ */
		else {
			System.out.println("�̺�Ʈ ������ �ƴմϴ�.");
			BuildingDAO buildingDao = new BuildingDAO();
			int flag = buildingDao.buildingCheck(user);
			
			/* ����ִ� ���� */
			if (flag == 0) {
				/* �ش� ���� GUI�� �����ϰ� �Ķ���ͷ� �ǹ�Ÿ���� ���� */
				buildingDao.purchaseEmptyRegionInformation(user);
				
				/* �� ���� �����ϱ� */
				PropertiesDAO propertiesDao = new PropertiesDAO();
				propertiesDao.purchaseEmptyRegion(user, 3);
				
				/* ���� ���� ������Ʈ*/
				UsersDAO.getUserCursor(user);
				propertiesDao.getPropertiesCursor();
			}
			
			/* ������ ���� */
			else if (flag == 1) {
				/* ���� ���� */
				UsersDAO.penalty(user);
				UsersDAO.getUserCursor(user);
				
				/* ������ �ǹ� �μ� */
				int takeoverFlag = UsersDAO.getYourBuilding(user);
				if (takeoverFlag == 1) {
					System.out.println("������ �ǹ��� �μ��߽��ϴ�!");
				}
				else {
					System.out.println("�ܰ� �����ؼ� ������ �ǹ��� �μ��� �� �����ϴ�.");
				}
			}
			
			/* ���� ���� */
			else {
				buildingDao.purchaseOtherBuilding(user);
				PropertiesDAO propertiesDao = new PropertiesDAO();
				
				/* �ش� ���� GUI�� �����ϰ� �Ķ���ͷ� �ǹ�Ÿ���� ���� */
				int purchaseFlag = propertiesDao.purhcaseOtherBuilding(user, 3);
				
				/* ���� �ݾ� >= �ǹ� ���� */
				if (purchaseFlag == 1) {
					System.out.println("�ǹ� �߰� ����");
				}
				
				/* ���� �ݾ� < �ǹ� ���� */
				else {
					System.out.println("�ܰ� �����ؼ� �ǹ��� �߰��� ������ �� �����ϴ�.");
				}
			}
			returnDataList.add(1);
		}

		/* ���� ������Ŵ */
		user.setTurn(user.getTurn() + 1);
		
		/* ���� �ܾװ� �ֻ��� ������ ��ȯ */
		returnDataList.add(UsersDAO.getUserCursor(1));
		returnDataList.add(UsersDAO.getUserCursor(2));
		returnDataList.add(diceA.getDice());
		returnDataList.add(diceB.getDice());

		return returnDataList;
	}

	public static ArrayList<String> buildingData(UsersVO user) {
		BuildingDAO buildingDao = new BuildingDAO();
		ArrayList<String> buildingData = buildingDao.purchaseEmptyRegionInformation(user);
		
		return buildingData;
	}
	
	public static String eventData(UsersVO user) {
		EventDAO eventDao = new EventDAO();
		String event = eventDao.getRandomEventReturnString(user);
		return event;
	}
	
	public static ArrayList<PropertiesVO> propertiesData(UsersVO user) {
		PropertiesDAO propertiesDao = new PropertiesDAO();
		ArrayList<PropertiesVO> propertiesData = propertiesDao.getPropertiesCursor(user);
		
		return propertiesData;
	}
}
