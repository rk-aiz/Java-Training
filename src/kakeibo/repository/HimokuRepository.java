package kakeibo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kakeibo.entity.Entity;
import kakeibo.entity.Himoku;

public class HimokuRepository implements DatabaseOperation {

	@Override
	public List<Entity> findAll() {

		List<Entity> list = new ArrayList<>();
		
		String sql = """
				SELECT
						id,
						himoku,
						deleteflag
				FROM himoku
				ORDER BY id
						""";
		
		try(Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Himoku himoku = new Himoku();
				himoku.setId(result.getInt("id"));
				himoku.setHimoku(result.getString("himoku"));
				himoku.setDeleteflag(result.getBoolean("deleteflag"));
				list.add(himoku); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Entity findById(int id) {
		
		Himoku himoku = new Himoku();
		
		String sql = """
				SELECT
						id,
						himoku,
						deleteflag
				FROM himoku
				WHERE id = ?
						""";
		
		try(Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			if(result.next()) {
				himoku.setId(id);
				himoku.setHimoku(result.getString("himoku"));
				himoku.setDeleteflag(result.getBoolean("deleteflag"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return himoku;
	}

	@Override
	public int insertEntity(Entity entity) {
		if (entity instanceof Himoku himoku) {
			return insertHimoku(himoku);
		} else {
			throw new IllegalArgumentException("entity is not Himoku");
		}
	}

	@Override
	public int updateEntity(Entity entity) {
		if (entity instanceof Himoku himoku) {
			return updateHimoku(himoku);
		} else {
			throw new IllegalArgumentException("entity is not Himoku");
		}
	}

	@Override
	public int deleteEntity(Entity entity) {
		if (entity instanceof Himoku himoku) {
			return deleteHimoku(himoku.getId());
		} else {
			throw new IllegalArgumentException("entity is not Himoku");
		}
	}

	@Override
	public int getIdMaxValue() {
		
		int idMax = 0;
		
		String sql = """
				SELECT MAX(id)
				FROM himoku
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

	private int insertHimoku(Himoku himoku) {

		int insCount = 0;

		String sql = """
				INSERT INTO himoku (himoku)
				VALUES (?)
					""";
		
		try(Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, himoku.getHimoku());
			insCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insCount;
	}

	private int updateHimoku(Himoku himoku) {
		int updCount = 0;
		
		String sql = """
				UPDATE himoku
				SET himoku = ?,
					deleteflag = ?
				WHERE id = ?
						""";

		try(Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, himoku.getHimoku());
			pstmt.setBoolean(2, himoku.isDeleteflag());
			pstmt.setInt(3, himoku.getId());
			updCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updCount;
	}

	private int deleteHimoku(int id) {
		int delCount = 0;
		
		String sql = """
				DELETE FROM himoku
				WHERE id = ?
						""";

		try(Connection con = DatabaseConnection.connect();
			PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, id);
			delCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return delCount;
	}
}
