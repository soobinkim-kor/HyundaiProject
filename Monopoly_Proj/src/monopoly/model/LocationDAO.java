package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import monopoly.DBConnection;
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

					LocationVO data = new LocationVO(idx, city);

					list.add(data);
				}

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
