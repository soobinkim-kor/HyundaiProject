package com.kosa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class LocationDAO {
	public ArrayList<LocationVO> list() {
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		
		String runSP = "{ call sp_select_location(?) }";

		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

				while (resultSet.next()) {
					int idx = resultSet.getInt(1);
					String city = resultSet.getString(2);

					LocationVO data = new LocationVO();

					data.setIdx(idx);
					data.setCity(city);

					list.add(data);

					System.out.println();
					System.out.println("idx: " + idx);
					System.out.println("name: " + city);
					System.out.println();
					System.out.println("성공");
					// 추후 이곳에서 UI와 맵핑 예정.
				}

				// 넘겨줄 객체가 많을 경우 데이터를 VO에 담아서 출력하거나 전달.

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
