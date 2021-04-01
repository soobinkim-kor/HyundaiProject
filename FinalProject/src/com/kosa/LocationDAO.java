package com.kosa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationDAO {
	public ArrayList<LocationVO> list() {
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();
		
		String runSP = "{ call sp_select_location(?, ?) }";
		
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);

			callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);

			try {
				callableStatement.executeQuery();
					
				int idx = callableStatement.getInt(1);
				String city = callableStatement.getString(2);
				
				LocationVO data = new LocationVO();

				data.setIdx(idx);
				data.setCity(city);

				list.add(data);

				System.out.println();
				System.out.println("idx: " + idx);
				System.out.println("name: " + city);
				System.out.println();
				System.out.println("����");

			// �Ѱ��� ��ü�� ���� ��� �����͸� VO�� ��Ƽ� ����ϰų� ����.

			} catch (SQLException e) {
				System.out.println("���ν������� ���� �߻�!");
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
