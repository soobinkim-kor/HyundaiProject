package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import monopoly.DBConnection;
import oracle.jdbc.OracleTypes;

public class EventDAO {
	public ArrayList<EventVO> list(int Idx) {
		ArrayList<EventVO> list = new ArrayList<EventVO>();
		
		String runSP = " {call sp_init_users(?, ?) }";
		
		try { 
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			
			callableStatement.setInt(1, Idx);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			
			try {
				callableStatement.executeQuery();
				ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
				
				while (resultSet.next()) {
					int eventIdx = resultSet.getInt(1);
					int locationIdx = resultSet.getInt(2);
					String description = resultSet.getString(3);

					EventVO data = new EventVO();

					data.setIdx(eventIdx);
					data.setLocationIdx(locationIdx);
					data.setDescription(description);
					
					list.add(data);

					System.out.println("Idx: " + eventIdx + " " + "locationIdx: " + locationIdx + " " + "description: " + description);
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
