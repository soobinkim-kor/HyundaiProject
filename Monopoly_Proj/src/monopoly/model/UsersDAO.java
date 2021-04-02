package monopoly.model;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import monopoly.DBConnection;

public class UsersDAO {
	public static void Init(UsersVO user, String name) {
		user.setName(name);
		user.setMoney(50000);
		user.setNow(0);
		user.setTurn(0);
		
		/* 초기 플레이어 설정 */
		String runSP = " {call init_users(?, ?) }";
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, user.getName());
			callableStatement.setBigDecimal(2, new BigDecimal(user.getMoney()));
			callableStatement.executeUpdate();
			
			System.out.println("Name: " + user.getName() + ", Money: " + user.getMoney());
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
