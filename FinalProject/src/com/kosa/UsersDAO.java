package com.kosa;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
	public static void Init(UsersVO user, String name) {
		user.setName(name);
		user.setMoney(50000);
		user.setNow(0);
		
		String runSP = " {call sp_init_users(?, ?, ?) }";
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, user.getName());
			callableStatement.setBigDecimal(2, new BigDecimal(user.getMoney()));
			callableStatement.setBigDecimal(3, new BigDecimal(user.getNow()));
			callableStatement.executeUpdate();
			System.out.println("¼º°ø");
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
