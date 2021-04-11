package monopoly.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import monopoly.DBConnection;

public class ExitProcedure {
	public ExitProcedure() {
		/* 게임 종료 시 users, properties 테이블 데이터 제거 */
		String runSP = " {call game_pkg.exit_procedure } ";
		try {
			Connection conn = DBConnection.getConnection();
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("프로시저에서 에러 발생!");
			// System.err.format("SQL State: %s", e.getSQLState());
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} 
	}
}
