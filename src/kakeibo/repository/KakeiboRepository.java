package kakeibo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kakeibo.entity.Entity;

public class KakeiboRepository implements DatabaseOperation {

	private Connection connection;
	
	KakeiboRepository() {
		connection = DatabaseConnection.connect();
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
	

	@Override
	public List<Entity> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Entity findById() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int insertEntity(Entity entity) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int updateEntity(Entity entity) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int deleteEntity(Entity entity) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int getIdMaxValue() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
