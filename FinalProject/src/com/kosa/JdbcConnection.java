package com.kosa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.pool.OracleDataSource;

public class JdbcConnection {
	@SuppressWarnings("unused")
	public static void main(String args[]) throws SQLException {
		/* ���� ���� + �̱��� ���� Ȱ�� ���� */
		Connection conn = DBConnection.getConnection();
		
		/* ���� ������ �ʱ�ȭ */
		UsersVO user1 = new UsersVO();
		UsersVO user2 = new UsersVO();
		String user1_name = "abc";
		String user2_name = "cba";
		UsersDAO.Init(user1, user1_name);
		UsersDAO.Init(user2, user2_name);
		/* ���� ������ �ʱ�ȭ */
		
		/* �� ������ �ʱ�ȭ */
		LocationDAO dao = new LocationDAO();
		ArrayList<LocationVO> list = dao.list();
		
		for (int i = 0; i < list.size(); i++) {
			LocationVO data = (LocationVO) list.get(i);
			int idx = data.getIdx();
			String city = data.getCity();

			System.out.println("idx : " + idx);
			System.out.println("city : " + city);
		}
		/* �� ������ �ʱ�ȭ */
		
	
	}

}