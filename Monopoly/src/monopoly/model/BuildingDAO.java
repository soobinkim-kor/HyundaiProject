package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import monopoly.DBConnection;
import oracle.jdbc.OracleTypes;

public class BuildingDAO {
	public int buildingCheck(UsersVO user) {
		/* 이벤트가 존재하지 않으면 빈 땅, 상대 땅, 내 땅 인지 확인 */
		String runSP ="{ call game_pkg.building_check(?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow());
			callableStatement.registerOutParameter(3, OracleTypes.INTEGER);
			
			try {
				callableStatement.executeQuery();
				int flag = callableStatement.getInt(3);
				return flag;
					
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} finally {
				callableStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
	    } catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<String> purchaseEmptyRegionInformation(UsersVO user) {
		/* 빈 땅 정보 보여주기 */
		String runSP ="{ call game_pkg.purchase_empty_region_information(?, ?) }";
		
		ArrayList<String> buildingInfo = new ArrayList<String>();

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getNow() + 1);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			
			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
				
				while (resultSet.next()) {
//					int locationIdx = resultSet.getInt(1);
					String locationCity = resultSet.getString(2);
					int buildingPrice = resultSet.getInt(3);
					
//					System.out.println(locationIdx + ", " + locationCity + ", " + buildingPrice);
					String data = locationCity + " " + buildingPrice;
					buildingInfo.add(data);
				} 
				
				return buildingInfo;
		
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} finally {
				callableStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return buildingInfo;
	}
	
	public void purchaseOtherBuilding(UsersVO user) {
		/* 내 땅 도착했을 때 구매할 수 있는 추가 건물 정보 보여주기 */
		String runSP ="{ call game_pkg.purchase_other_building_information(?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow());
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			
			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(3);
				
				while (resultSet.next()) {
//					int locationIdx = resultSet.getInt(1);
//					String locationCity = resultSet.getString(2);
//					int buildingPrice = resultSet.getInt(3);
				} // 출력까지
		
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} finally {
				callableStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
