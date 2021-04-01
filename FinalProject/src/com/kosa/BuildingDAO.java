package com.kosa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class BuildingDAO {
	public ArrayList<BuildingVO> list(int now_idx) {
		ArrayList<BuildingVO> list = new ArrayList<BuildingVO>();
		
		String runSP = " {call sp_init_users(?, ?) }";
		
		try { 
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			
			callableStatement.setInt(1, now_idx);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			
			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
				
				while (resultSet.next()) {
					int locationIdx = resultSet.getInt(1);
					int typeIdx = resultSet.getInt(2);
					int price = resultSet.getInt(3);

					BuildingVO data = new BuildingVO();

					data.setLocationIdx(locationIdx);
					data.setTypeIdx(typeIdx);
					data.setPrice(price);

					System.out.println("locationIdx: " + locationIdx + " " + "typeIdx: " + typeIdx + " " + price);
					System.out.println();
					// 추후 이곳에서 UI와 맵핑 예정.
				}
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
