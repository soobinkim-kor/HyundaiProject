package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import monopoly.DBConnection;

public class ExitProcedure {
	public ExitProcedure() {
		/* ���� ���� �� users, properties ���̺� ������ ���� */
		String runSP = " {call game_pkg.exit_procedure } ";
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("���ν������� ���� �߻�!");
			// System.err.format("SQL State: %s", e.getSQLState());
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} 
	}
}
