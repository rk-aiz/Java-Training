package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


//問題１クラス名はQuestions01
//家計簿テーブルの日付が今日の日付の行の出金額を900に更新

public class Question01 {
	public static void main(String[] args) {
		
		// 接続文字列の設定
		// DB のURL
		String url = "jdbc:postgresql://localhost:5432/postgres";
		// DB のユーザー名
		String user ="postgres";
		// DB のパスワード
		String password = "postgrestest";
		// SQL 文の作成
		String sql = "UPDATE 家計簿 SET 出金額 = ? WHERE 日付 = ?";
				
		try (// PostgreSQL に接続
				Connection con = DriverManager.getConnection(url, user, password);
				// SQL を実行するためのインスタンスを生成
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダーに値をセットする
			pstmt.setInt(1,900);
			pstmt.setDate(2, new java.sql.Date(new Date().getTime()));
			// SQL の実行し、実行件数結果を格納する
			int num = pstmt.executeUpdate();
			// 実行件数結果を出力
			System.out.println(num+"行更新されました");

		} catch (SQLException e) {
			//  SQLExceptionの例外処理
			e.printStackTrace();
		}
		
	}
}
