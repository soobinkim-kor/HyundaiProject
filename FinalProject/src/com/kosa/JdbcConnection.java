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
		/* 설정 파일 + 싱글턴 패턴 활용 접속 */
		Connection conn = DBConnection.getConnection();
		UsersVO user1 = new UsersVO();
		UsersVO user2 = new UsersVO();
		
		LocationDAO dao = new LocationDAO();
		ArrayList<LocationVO> list = dao.list();
		
		for (int i = 0; i < list.size(); i++) {
			LocationVO data = (LocationVO) list.get(i);
			int idx = data.getIdx();
			String city = data.getCity();

			System.out.println("idx : " + idx);
			System.out.println("city : " + city);
		}
		
	
	}

}