package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class InsertSample {

	public static void main(String[] args) {
		// 接続文字列の設定
		// DB のURL
		String url = "jdbc:postgresql://localhost:5432/postgres";
		// DB のユーザー名
		String user ="postgres";
		// DB のパスワード
		String password = "postgrestest";
		// SQL 文の作成
		String sql = "insert into 家計簿 values(?,?,?,?,?)";
		
		try (// PostgreSQL に接続
				Connection con = DriverManager.getConnection(url, user, password);
				// SQL を実行するためのインスタンスを生成
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダーに値をセットする
			Date d = new Date();
			long time = d.getTime();
			java.sql.Date d2 = new java.sql.Date(time);
			pstmt.setDate(1,d2);
			pstmt.setString(2,"食費");
			pstmt.setString(3,"マクドナルド");
			pstmt.setInt(4,0);
			pstmt.setInt(5,850);
			// SQL の実行し、実行件数結果を格納する
			int num = pstmt.executeUpdate();
			// 実行件数結果を出力
			System.out.println(num+"行追加されました");

		} catch (SQLException e) {
			//  SQLExceptionの例外処理
			e.printStackTrace();
		}
	}
}
