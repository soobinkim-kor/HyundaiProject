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

public class Main {
	@SuppressWarnings("unused")
	public static void main(String args[]) throws SQLException {
		/* 설정 파일 + 싱글턴 패턴 활용 접속 */
		Connection conn = DBConnection.getConnection();
		
		/* 시스템 데이터 초기화 */
		int turn = 0;
		boolean nowTurn = false;
		
		/* 유저 데이터 초기화 */
		UsersVO user1 = new UsersVO();
		UsersVO user2 = new UsersVO();
		String user1_name = "abc";
		String user2_name = "cba";
		UsersDAO.Init(user1, user1_name);
		UsersDAO.Init(user2, user2_name);
		
		/* 맵 데이터 초기화 */
		LocationDAO dao = new LocationDAO();
		ArrayList<LocationVO> list = dao.list();
		
		while (user1.getMoney() <= 0 || user2.getMoney() <= 0 ||
			   turn == 10) {
			if (nowTurn == false) {
				InitMonopoly.InitMonopolySystem(list, user1);
				nowTurn = true;
			}
			
			else if (nowTurn == true) {
				InitMonopoly.InitMonopolySystem(list, user2);
				nowTurn = false;
			}
			
			turn++;
		}
	
	}

}