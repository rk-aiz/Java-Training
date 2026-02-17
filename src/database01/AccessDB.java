package database01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessDB {

	private Connection conn;
	private final String url;
	private final String user;
	private final String password;
	
	public AccessDB() {
		this.url = "jdbc:postgresql://localhost:5432/postgres";
		this.user= "postgres";
		this.password = "postgrestest";
	}
	
	public void connect() {
		try {
			this.conn = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * データベース接続を閉じます。
	 */
	public void disConnect() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 商品マスタの全件を取得します。
	 * @return 商品リスト
	 */
	public List<M_goods> getGoodsList() {
		
		List<M_goods> goods = new ArrayList<>();
		
		String sql = """
				SELECT * FROM m_goods
				""";
		
		try (PreparedStatement pstmt = 
				this.conn.prepareStatement(sql)) {

			try (ResultSet result = pstmt.executeQuery()) {
				while (result.next()) {
					int itemCode = result.getInt("itemcode");
					String itemName = result.getString("itemname");
					int supCode = result.getInt("supcode");
					int price = result.getInt("price");
					Date updateDate = result.getDate("update_date");
					
					goods.add(new M_goods(
							itemCode,
							itemName,
							supCode,
							price,
							updateDate));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return goods;
	}
	
	/**
	 * 仕入先マスタの全件を取得します。
	 * @return 仕入先リスト
	 */
	public List<M_supplier> getSupplierList() {
		
		List<M_supplier> suppliers = new ArrayList<>();
		
		String sql = """
				SELECT * FROM m_supplier
				""";
		
		try (PreparedStatement pstmt = 
				this.conn.prepareStatement(sql)) {

			try (ResultSet result = pstmt.executeQuery()) {
				while (result.next()) {
					int supCode = result.getInt("supcode");
					String supName = result.getString("supname");
					String address = result.getString("address");
					String tel = result.getString("tel");
					String repName = result.getString("rep_name");
					Date updateDate = result.getDate("update_date");
					
					suppliers.add(new M_supplier(
							supCode,
							supName,
							address,
							tel,
							repName,
							updateDate));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return suppliers;
	}
	
	/**
	 * 指定された商品番号の商品情報を取得します（仕入先情報を含む）。
	 * @param itemCode 商品番号
	 * @return 商品情報（見つからない場合はnull）
	 */
	public M_goods getGoods(int itemCode) {

		String sql = """
				SELECT g.itemname,
				       g.supcode,
					   g.price,
					   s.supname,
					   s.address,
					   s.tel,
					   s.rep_name,
					   g.update_date as g_update,
					   s.update_date as s_update
				  FROM m_goods as g
				  JOIN m_supplier as s
				    ON g.supcode = s.supcode
				 WHERE itemcode = ?
				""";
		
		try (PreparedStatement pstmt = 
				this.conn.prepareStatement(sql)) {

			pstmt.setInt(1, itemCode);
			
			try (ResultSet result = pstmt.executeQuery()) {
				if (result.next()) {
					M_goods goods = new M_goods();
					goods.setItemCode(itemCode);
					goods.setItemName(result.getString("itemname"));
					goods.setSupCode(result.getInt("supcode"));
					goods.setPrice(result.getInt("price"));
					goods.setUpdateDate(result.getDate("g_update"));

					M_supplier supplier = new M_supplier();
					supplier.setSupCode(result.getInt("supcode"));
					supplier.setSupName(result.getString("supname"));
					supplier.setAddress(result.getString("address"));
					supplier.setTel(result.getString("tel"));
					supplier.setRepName(result.getString("rep_name"));
					supplier.setUpdateDate(result.getDate("s_update"));
					
					goods.setSupplier(supplier);
					return goods;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 商品マスタから指定された商品を削除します。
	 * @param itemCode 商品番号
	 * @return 削除件数
	 */
	public int deleteFromM_goods(int itemCode) {
		
		int delCount = 0;
		
		String sql = """
				DELETE FROM m_goods
				 WHERE itemcode = ?
				""";
				
		try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
			pstmt.setInt(1, itemCode);
			
			// SQL の実行し、実行件数結果を格納する
			delCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return delCount;
	}
	
	/**
	 * 商品マスタに1件追加します。
	 * @param goods 追加する商品情報
	 * @return 追加件数
	 */
	public int insertM_goods(M_goods goods) {
		
		int insCount = 0;
		
		String sql = """
				INSERT INTO m_goods
				VALUES (?, ?, ?, ?, CURRENT_DATE)
				""";
				
		try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
					
			pstmt.setInt(1, goods.getItemCode());
			pstmt.setString(2, goods.getItemName());
			pstmt.setInt(3, goods.getSupCode());
			pstmt.setInt(4, goods.getPrice());
			
			
			// SQL の実行し、実行件数結果を格納する
			insCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insCount;
	}
	
	/**
	 * 商品マスタの情報を更新します。
	 * @param goods 更新する商品情報
	 * @return 更新件数
	 */
	public int updateM_goods(M_goods goods) {
		
		int updCount = 0;
		
		String sql = """
				UPDATE m_goods
				   SET itemname = ?,
				       supcode = ?,
				       price = ?,
				       update_date = CURRENT_DATE
				 WHERE itemcode = ?
				""";
				
		try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
					
			pstmt.setString(1, goods.getItemName());
			pstmt.setInt(2, goods.getSupCode());
			pstmt.setInt(3, goods.getPrice());
			pstmt.setInt(4, goods.getItemCode());
			
			// SQL の実行し、実行件数結果を格納する
			updCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updCount;
	}
}
