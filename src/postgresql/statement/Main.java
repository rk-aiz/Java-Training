package postgresql.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "postgrestest";
		
		try (Connection con = 
				DriverManager.getConnection(
						url, user, password);
			Statement stmt = con.createStatement();){
			
			testStatement(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void testStatement(Connection con) throws SQLException {
		
		String sql = "SELECT * FROM m_goods";
		
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		
		while(result.next()) {
			
			System.out.println(
					result.getString("itemname"));
		}
		
	}
	
}
