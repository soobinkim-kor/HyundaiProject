package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import monopoly.DBConnection;
import oracle.jdbc.OracleTypes;

public class LocationDAO {

	public ArrayList<LocationVO> list() {
		ArrayList<LocationVO> list = new ArrayList<LocationVO>();

		/* 도시 전체 정보 전달 */
		String runSP = "{ call game_pkg.get_building_location(?) }";

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

					LocationVO data = new LocationVO(idx - 1, city);
					list.add(data);
				}
				
				

			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				System.out.println("여기?");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} finally {
				callableStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
