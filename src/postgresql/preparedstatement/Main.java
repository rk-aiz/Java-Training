package postgresql.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "postgrestest";
		
		String sql = "SELECT * FROM m_goods where price >=?";
		
		try (Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			testPreparedStatement(con, pstmt);
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	public static void testPreparedStatement(
			Connection con, PreparedStatement pstmt) throws SQLException {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		pstmt.setInt(1, 100);
		ResultSet result = pstmt.executeQuery();
		
		while(result.next()) {
			Date updateDate = result.getTimestamp("update_date");
			String name = result.getString("itemname");
			String price = result.getString("price");
			System.out.println(df.format(updateDate) + ":" + name
			+ ":" + price);
		}
	}
	
}
