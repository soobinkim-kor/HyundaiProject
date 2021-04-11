package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import monopoly.DBConnection;
import oracle.jdbc.OracleTypes;

public class PropertiesDAO {
	public ArrayList<PropertiesVO> getPropertiesCursor() {
		/* 빈 영역의 건물 구매 */
		String runSP = "{ call game_pkg.get_properties_cursor(?) }";
		ArrayList<PropertiesVO> propertiesList = new ArrayList<PropertiesVO>();
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

				while (resultSet.next()) {
					PropertiesVO tempList = new PropertiesVO();
					tempList.setUsersIdx(resultSet.getInt(1));
					tempList.setBuildingLocationIdx(resultSet.getInt(2));
					tempList.setBuildingTypeIdx(resultSet.getInt(3));
					tempList.setFine(resultSet.getInt(4));
					propertiesList.add(tempList);
				}

				return propertiesList;

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
		
		return propertiesList;
	}
	
	public ArrayList<PropertiesVO> getPropertiesCursor(UsersVO user) {
		/* 빈 영역의 건물 구매 */
		String runSP = "{ call game_pkg.get_properties_cursor(?) }";
		ArrayList<PropertiesVO> propertiesList = new ArrayList<PropertiesVO>();
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

				while (resultSet.next()) {
					PropertiesVO tempList = new PropertiesVO();
					tempList.setUsersIdx(resultSet.getInt(1));
					tempList.setBuildingLocationIdx(resultSet.getInt(2));
					tempList.setBuildingTypeIdx(resultSet.getInt(3));
					tempList.setFine(resultSet.getInt(4));
					if (tempList.getUsersIdx() == user.getIdx()) {
						propertiesList.add(tempList);
					}
					else {
						continue;
					}
				}

				return propertiesList;

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
		
		return propertiesList;
	}

	public void purchaseEmptyRegion(UsersVO user, int purchaseType) {
		/* 빈 영역의 건물 구매 */
		String runSP = "{ call game_pkg.purchase_empty_region(?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow() + 1);
			callableStatement.setInt(3, purchaseType);

			try {
				callableStatement.executeUpdate();

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

	public int purhcaseOtherBuilding(UsersVO user, int purchaseType) {
		/* 내 땅 도착했을 때 다른 건물 구매하기 */
		String runSP = "{ call game_pkg.purchase_other_building(?, ?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow());
			callableStatement.setInt(3, purchaseType);
			callableStatement.registerOutParameter(4, OracleTypes.INTEGER);

			try {
				callableStatement.executeQuery();
				int flag = callableStatement.getInt(4);
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
}
