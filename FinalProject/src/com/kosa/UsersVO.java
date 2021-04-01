package com.kosa;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UsersVO {
	private int idx;
	private String name;
	private int money;
	private int now;

	public UsersVO() {
	}

	public UsersVO(int idx, String name, int money, int now) {
		this.idx = idx;
		this.name = name;
		this.money = money;
		this.now = now;
	}

	public int getIdx() {
		return idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public static void Init(UsersVO user, String name) {
		user.setName(name);
		user.setMoney(50000);
		user.setNow(1);
		
		String runSP = " {call sp_insert_users(?, ?, ?) }";
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, user.getName());
			callableStatement.setBigDecimal(2, new BigDecimal(user.getMoney()));
			callableStatement.setBigDecimal(3, new BigDecimal(user.getNow()));
			callableStatement.executeUpdate();
			System.out.println("����");
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
