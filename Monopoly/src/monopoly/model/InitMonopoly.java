package monopoly.model;

import java.util.ArrayList;
import java.util.Stack;

public class InitMonopoly {
	public static Stack<Integer> InitMonopolySystem(ArrayList<LocationVO> list, UsersVO user) {
		final int MAP_SIZE = 21;
		Stack<Integer> returnDataList = new Stack<Integer>();
		
		/* 주사위 굴리기 */
		Dice diceA = new Dice();
		Dice diceB = new Dice();
		diceA.getDice();
		diceB.getDice();
		
		/* 굴린 주사위를 유저의 현재 위치에 더함 */
		int dice = diceA.getDice() + diceB.getDice();
		int now_idx = user.getNow() + dice;
		
		/* 맵 전체 인덱스보다 유저 인덱스가 커지면 유저 인덱스 초기화*/
		if (now_idx > MAP_SIZE) {
			now_idx -= MAP_SIZE + 1;
			
			/* 한바퀴 돌면 2000원 획득 */ 
			EventDAO eventDao = new EventDAO();
			eventDao.moveOne(user);
		}
		
		/* 이동한 위치를 대상으로 도시명 검색 */
		user.setNow(now_idx);
		LocationVO data = list.get(now_idx);
		String city = data.getCity();
		System.out.println("도착 장소 : " + city);
		
		/* 유저의 인덱스를 매개변수로 현재위치를 변경 */
		UsersDAO.spMove(user);
		
		/* 변경된 유저의 현재위치를 데이터베이스에 업데이트 */
		UsersDAO.getUserCursor(user);
		
		/* 이벤트 지역 유무 체크 */
		EventDAO eventDao = new EventDAO();
		int eventFlag = eventDao.eventCheck(user);

		/* 이벤트가 존재하면 */
		if (eventFlag == 1) {
			System.out.println("이벤트 지역입니다.");
			/* 랜덤 이벤트 받기 및 실행*/
			int flag = eventDao.eventExecute(user, eventDao.getRandomEvent(user));
			
			if (flag == 0) {
				eventDao.eventCheck(user);
				returnDataList.add(1);
			}
			else if (flag == 1) {
				System.out.println("무인도 이벤트");
				returnDataList.add(-1);
			}
			else if (flag == 2) {
				System.out.println("우주선 이벤트");
				eventDao.moveSpaceship(user);
				returnDataList.add(1);
			}
			
			System.out.println();
			UsersDAO.getUserCursor(user);
			// 이미지 실제 이동 처리 필요
		}

		/* 이벤트가 존재하지 않으면 */
		else {
			System.out.println("이벤트 지역이 아닙니다.");
			BuildingDAO buildingDao = new BuildingDAO();
			int flag = buildingDao.buildingCheck(user);
			
			/* 비어있는 지역 */
			if (flag == 0) {
				/* 해당 정보 GUI에 매핑하고 파라미터로 건물타입을 받음 */
				buildingDao.purchaseEmptyRegionInformation(user);
				
				/* 빈 지역 구매하기 */
				PropertiesDAO propertiesDao = new PropertiesDAO();
				propertiesDao.purchaseEmptyRegion(user, 3);
				
				/* 유저 정보 업데이트*/
				UsersDAO.getUserCursor(user);
				propertiesDao.getPropertiesCursor();
			}
			
			/* 상대방의 지역 */
			else if (flag == 1) {
				/* 벌금 지불 */
				UsersDAO.penalty(user);
				UsersDAO.getUserCursor(user);
				
				/* 상대방의 건물 인수 */
				int takeoverFlag = UsersDAO.getYourBuilding(user);
				if (takeoverFlag == 1) {
					System.out.println("상대방의 건물을 인수했습니다!");
				}
				else {
					System.out.println("잔고가 부족해서 상대방의 건물을 인수할 수 없습니다.");
				}
			}
			
			/* 나의 지역 */
			else {
				buildingDao.purchaseOtherBuilding(user);
				PropertiesDAO propertiesDao = new PropertiesDAO();
				
				/* 해당 정보 GUI에 매핑하고 파라미터로 건물타입을 받음 */
				int purchaseFlag = propertiesDao.purhcaseOtherBuilding(user, 3);
				
				/* 보유 금액 >= 건물 가격 */
				if (purchaseFlag == 1) {
					System.out.println("건물 추가 구매");
				}
				
				/* 보유 금액 < 건물 가격 */
				else {
					System.out.println("잔고가 부족해서 건물을 추가로 구매할 수 없습니다.");
				}
			}
			returnDataList.add(1);
		}

		/* 턴을 증가시킴 */
		user.setTurn(user.getTurn() + 1);
		
		/* 유저 잔액과 주사위 정보를 반환 */
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
