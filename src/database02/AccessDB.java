package database02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 気温データベースへのアクセスを担当するクラスです。
 */
public class AccessDB {

	private Connection conn;
	private final String url;
	private final String user;
	private final String password;
	
	/**
	 * コンストラクタ。接続情報を初期化します。
	 */
	public AccessDB() {
		this.url = "jdbc:postgresql://localhost:5432/postgres";
		this.user= "postgres";
		this.password = "postgrestest";
	}
	
	/**
	 * データベースに接続します。
	 */
	public void connect() {
		try {
			this.conn = DriverManager.getConnection(
					this.url, this.user, this.password);
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
				this.conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * データベース内の全気温データを標準出力に表示します。
	 */
	public void printAllData() {
		
		List<TempData> tempData = new ArrayList<>();
		
		String sql = """
				SELECT hiduke, avetemp, maxtemp, mintemp 
				FROM temperature
				""";
		
		try (PreparedStatement pstmt = 
				this.conn.prepareStatement(sql)) {

			try (ResultSet result = pstmt.executeQuery()) {
				while (result.next()) {
					tempData.add(new TempData(
							result.getDate("hiduke"),
							result.getFloat("avetemp"),
							result.getFloat("maxtemp"),
							result.getFloat("mintemp")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (TempData td : tempData) {
			System.out.println(td);
		}
	}
	
	/**
	 * 指定された月のデータを取得し、CSVファイルに出力します。
	 * @param month 月 (MM形式)
	 * @param cfr CSV操作オブジェクト
	 * @return 出力件数
	 */
	public int exportMonthlyData(String month, CsvFileOperation cfr) {
		
		List<TempData> tempData = new ArrayList<>();
		
		String sql = """
				SELECT hiduke, avetemp, maxtemp, mintemp
				  FROM temperature
				 WHERE to_char(hiduke, 'MM') = ?
				""";
		
		try (PreparedStatement pstmt = 
				this.conn.prepareStatement(sql)) {

			pstmt.setString(1, month);
			
			try (ResultSet result = pstmt.executeQuery()) {
				while (result.next()) {
					tempData.add(new TempData(
							result.getDate("hiduke"),
							result.getFloat("avetemp"),
							result.getFloat("maxtemp"),
							result.getFloat("mintemp")
							));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cfr.exportToCsv(tempData);
		
		return tempData.size();
	}
	
	/**
	 * 気温データを1件挿入します。
	 * @param date 日付
	 * @param average 平均気温
	 * @param max 最高気温
	 * @param min 最低気温
	 * @return 挿入件数
	 */
	public int insertData(Date date, double average, double max, double min) {
		
		int insCount = 0;
		
		String sql = """
				INSERT INTO temperature (hiduke, avetemp, maxtemp, mintemp)
				VALUES (?, ?, ?, ?)
				""";
				
		try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
					
			pstmt.setDate(1, new java.sql.Date(date.getTime()));
			pstmt.setFloat(2, (float)average);
			pstmt.setFloat(3, (float)max);
			pstmt.setFloat(4, (float)min);
			
			// SQL の実行し、実行件数結果を格納する
			insCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insCount;
	}
	
	/**
	 * 全ての気温データを削除します。
	 * @return 削除件数
	 */
	public int deleteAllData() {
		
		int delCount = 0;
		
		String sql = """
				DELETE FROM temperature
				""";
				
		try (PreparedStatement pstmt = this.conn.prepareStatement(sql)) {
			// SQL の実行し、実行件数結果を格納する
			delCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return delCount;
	}
}
