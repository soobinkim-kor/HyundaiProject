package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import monopoly.DBConnection;
import oracle.jdbc.OracleTypes;

public class EventDAO {
	public int eventCheck(UsersVO user) {
		/* 이벤트 체크 */
		String runSP ="{ call game_pkg.event_check(?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getNow() + 1);
			callableStatement.registerOutParameter(2, OracleTypes.INTEGER);

			try {
				callableStatement.executeQuery();
				int flag = (int)callableStatement.getInt(2);
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
	
	public int getRandomEvent(UsersVO user) {
		/* 이벤트가 존재하면 랜덤 이벤트 받기 */
		String runSP ="{ call game_pkg.get_random_event(?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow() + 1);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			
			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(3);
				
				while (resultSet.next()) {
					int eventIdx = resultSet.getInt(1);
					int eventLocationidx = resultSet.getInt(2);
					String description = resultSet.getString(3);
					
					System.out.println(eventIdx);
					System.out.println(eventLocationidx);
					System.out.println(description);
					return eventIdx;
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
	
	public String getRandomEventReturnString(UsersVO user) {
		/* 이벤트가 존재하면 랜덤 이벤트 받기 */
		String runSP ="{ call game_pkg.get_random_event(?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow() + 1);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			
			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(3);
				
				while (resultSet.next()) {
//					int eventIdx = resultSet.getInt(1);
//					int eventLocationidx = resultSet.getInt(2);
					String description = resultSet.getString(3);
				
					return description;
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
		
		return null;
	}
	
	public int eventExecute(UsersVO user, int eventIdx) {
		/* 이벤트 실행 -> user정보 변경되므로 get_user_cursor 호출 필요 */
		String runSP ="{ call game_pkg.event_execute(?, ?, ?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow());
			callableStatement.setInt(3, eventIdx);
			callableStatement.registerOutParameter(4, OracleTypes.INTEGER);
			
			try {
				callableStatement.executeQuery();
				
				int flag = callableStatement.getInt(4);
				return flag;
		
			} catch (SQLException e) {
				System.out.println("");
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
	
	public void moveSpaceship(UsersVO user) {
		/* 우주선으로 팝업으로 원하는 곳 설정 후 이동 */
		String runSP ="{ call game_pkg.move_spaceship(?, ?) }";

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
	
//	public static void moveBank(UsersVO user) {
//		/* 은행에 1000원 반납 */
//		String runSP = " {call game_pkg.move_bank(?)} ";
//
//		try {
//			Connection conn = DBConnection.getConnection();
//			CallableStatement callableStatement = conn.prepareCall(runSP);
//			callableStatement.setInt(1, user.getIdx());
//
//			try {
//				callableStatement.executeUpdate();
//
//			} catch (SQLException e) {
//				System.out.println("프로시저에서 에러 발생!");
//				// System.err.format("SQL State: %s", e.getSQLState());
//				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//			} finally {
//				callableStatement.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void moveOne(UsersVO user) {
		/* 한 바퀴 돌면 2000원 획득 */
		String runSP = " {call game_pkg.move_one(?) }";
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setInt(1, user.getIdx());
			callableStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}