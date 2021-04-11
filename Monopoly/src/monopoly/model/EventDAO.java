package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import monopoly.DBConnection;
import oracle.jdbc.OracleTypes;

public class EventDAO {
	public int eventCheck(UsersVO user) {
		/* �̺�Ʈ üũ */
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
				System.out.println("���ν������� ���� �߻�!");
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
		/* �̺�Ʈ�� �����ϸ� ���� �̺�Ʈ �ޱ� */
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
				System.out.println("���ν������� ���� �߻�!");
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
		/* �̺�Ʈ�� �����ϸ� ���� �̺�Ʈ �ޱ� */
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
				System.out.println("���ν������� ���� �߻�!");
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
		/* �̺�Ʈ ���� -> user���� ����ǹǷ� get_user_cursor ȣ�� �ʿ� */
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
				System.out.println("���ν������� ���� �߻�!");
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
		/* ���ּ����� �˾����� ���ϴ� �� ���� �� �̵� */
		String runSP ="{ call game_pkg.move_spaceship(?, ?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.setInt(1, user.getIdx());
			callableStatement.setInt(2, user.getNow());
			
			try {
				callableStatement.executeUpdate();
		
			} catch (SQLException e) {
				System.out.println("���ν������� ���� �߻�!");
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
//		/* ���࿡ 1000�� �ݳ� */
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
//				System.out.println("���ν������� ���� �߻�!");
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
		/* �� ���� ���� 2000�� ȹ�� */
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