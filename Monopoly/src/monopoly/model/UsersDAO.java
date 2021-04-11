package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import monopoly.DBConnection;
import oracle.jdbc.OracleTypes;

public class UsersDAO {
	public static void Init(UsersVO user, int idx, String name) {
		user.setIdx(idx);
		user.setName(name);
		user.setMoney(50000);
		user.setNow(0);
		user.setTurn(0);

		/* 초기 플레이어 설정 */
		String runSP = " {call game_pkg.init_users(?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			try {
				callableStatement.setString(1, user.getName());
				callableStatement.setInt(2, user.getMoney());
				callableStatement.executeUpdate();

			} catch (SQLException e) {
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
				e.printStackTrace();
			} finally {
				callableStatement.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void updateUserNow(UsersVO user) {
//		/* 유저 정보 업데이트(Java->DB) 프로시저 */
//		String runSP = " {call game_pkg.update_user_information_procedure(?, ?)} ";
//
//		try {
//			Connection conn = DBConnection.getConnection();
//			CallableStatement callableStatement = conn.prepareCall(runSP);
//			try {
//				callableStatement.setInt(1, user.getIdx());
//				callableStatement.setInt(2, user.getNow());
//				callableStatement.executeUpdate();
//
//				System.out.println("현재 유저 : " + user.getName());
//
//			} catch (SQLException e) {
//				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//				e.printStackTrace();
//			} finally {
//				callableStatement.close();
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void getAllUsersInformationProcedure() {
		/* 전체 유저 정보 전달 프로시저 */
		String runSP = " {call game_pkg.get_all_users_information_procedure(?)} ";
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

				while (resultSet.next()) {
//					int idx = resultSet.getInt(1);
//					String name = resultSet.getString(2);
//					int money = resultSet.getInt(3);
//					int now = resultSet.getInt(4);
				}

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

	public static void getUserCursor(UsersVO user) {
		/* 특정 유저 정보 전달 프로시저 */
		String runSP = " { call game_pkg.get_user_cursor(?, ?) } ";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setInt(1, user.getIdx());
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

				while (resultSet.next()) {
					int idx = resultSet.getInt(1);
					String name = resultSet.getString(2);
					int money = resultSet.getInt(3);
					int now = resultSet.getInt(4);

					System.out.println("idx: " + idx);
					System.out.println("name: " + name);
					System.out.println("money: " + money);
					System.out.println("now: " + now);

				}
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
	
	public static int getUserCursor(int userIdx) {
		/* 특정 유저 정보 전달 프로시저(데이터 맵핑용 int 반환) */
		String runSP = " { call game_pkg.get_user_cursor(?, ?) } ";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setInt(1, userIdx);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

				while (resultSet.next()) {
//					int idx = resultSet.getInt(1);
//					String name = resultSet.getString(2);
					int money = resultSet.getInt(3);
//					int now = resultSet.getInt(4);

					return money;

				}
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

	public static void spMove(UsersVO user) {
		/* 주사위 굴린 후 이동 */
		String runSP = " {call game_pkg.sp_move(?, ?)} ";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow());

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

	public static void penalty(UsersVO user) {
		/* 상대 지역 벌금 부여 */
		String runSP = " {call game_pkg.penalty(?, ?)} ";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow());

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

	public static int getYourBuilding(UsersVO user) {
		/* 상대 건물 인수 */
		String runSP = " {call game_pkg.get_your_building(?, ?, ?)} ";

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
}
