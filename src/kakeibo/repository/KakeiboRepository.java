package kakeibo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kakeibo.entity.Entity;
import kakeibo.entity.Kakeibo;

public class KakeiboRepository implements DatabaseOperation {
	
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
		
		List<Entity> list = new ArrayList<>();

		String sql = """
				SELECT
						id,
						himokuid,
						memo,
						nyuukingaku,
						syukkingaku,
						hiduke
				FROM kakeibo
						""";
		
		try (Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Kakeibo kakeibo = new Kakeibo();
				kakeibo.setId(result.getInt("id"));
				kakeibo.setHimokuid(result.getInt("himokuid"));
				kakeibo.setMemo(result.getString("memo"));
				kakeibo.setNyuukingaku(result.getInt("nyuukingaku"));
				kakeibo.setSyukkingaku(result.getInt("syukkingaku"));
				kakeibo.setHiduke(result.getTimestamp("hiduke").toLocalDateTime());
				list.add(kakeibo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Entity findById(int id) {
		
		String sql = """
				SELECT
						id,
						himokuid,
						memo,
						nyuukingaku,
						syukkingaku,
						hiduke
				FROM kakeibo
				WHERE id = ?
						""";
		
		try (Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				Kakeibo kakeibo = new Kakeibo();
				kakeibo.setId(id);

				kakeibo.setHimokuid(result.getInt("himokuid"));
				kakeibo.setMemo(result.getString("memo"));
				kakeibo.setNyuukingaku(result.getInt("nyuukingaku"));
				kakeibo.setSyukkingaku(result.getInt("syukkingaku"));
				kakeibo.setHiduke(result.getTimestamp("hiduke").toLocalDateTime());
				return kakeibo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insertEntity(Entity entity) {
		if (entity instanceof Kakeibo kakeibo) {
			return insertKakeibo(kakeibo);
		} else {
			throw new IllegalArgumentException("entity is not Kakeibo");
		}
	}

	@Override
	public int updateEntity(Entity entity) {
		if (entity instanceof Kakeibo kakeibo) {
			return updateKakeibo(kakeibo);
		} else {
			throw new IllegalArgumentException("entity is not Kakeibo");
		}
	}

	@Override
	public int deleteEntity(Entity entity) {
		if (entity instanceof Kakeibo kakeibo) {
			return deleteKakeibo(kakeibo.getId());
		} else {
			throw new IllegalArgumentException("entity is not Kakeibo");
		}
	}

	@Override
	public int getIdMaxValue() {
		
		int idMax = 0;

		String sql = """
				SELECT MAX(id)
				FROM kakeibo
						""";

		try (Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

				ResultSet result = pstmt.executeQuery();
				if (result.next()) {
					idMax = result.getInt(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idMax;
	}

	private int insertKakeibo(Kakeibo kakeibo) {

		int insCount = 0;

		String sql = """
				INSERT INTO kakeibo (
						himokuid,
						memo,
						nyuukingaku,
						syukkingaku,
						hiduke)
				VALUES (?, ?, ?, ?, ?)
						""";

		try (Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setInt(1, kakeibo.getHimokuid());
			pstmt.setString(2, kakeibo.getMemo());
			pstmt.setInt(3,kakeibo.getNyuukingaku());
			pstmt.setInt(4, kakeibo.getSyukkingaku());
			pstmt.setTimestamp(5, java.sql.Timestamp.valueOf(kakeibo.getHiduke()));
			insCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insCount;
	}

	private int updateKakeibo(Kakeibo kakeibo) {

		int updCount = 0;

		String sql = """
				UPDATE kakeibo
				SET himokuid = ?,
						memo = ?,
						nyuukingaku = ?,
						syukkingaku = ?,
						hiduke = ?
				WHERE id = ?
						""";

		try (Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

				pstmt.setInt(1, kakeibo.getHimokuid());
				pstmt.setString(2, kakeibo.getMemo());
				pstmt.setInt(3,kakeibo.getNyuukingaku());
				pstmt.setInt(4, kakeibo.getSyukkingaku());
				pstmt.setTimestamp(5, java.sql.Timestamp.valueOf(kakeibo.getHiduke()));
				pstmt.setInt(6, kakeibo.getId());
				updCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updCount;
	}

	private int deleteKakeibo(int id) {

		int delCount = 0;

		String sql = """
				DELETE FROM kakeibo
				WHERE id = ?
						""";

		try (Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

				pstmt.setInt(1, id);
				delCount = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		return delCount;
	}

}