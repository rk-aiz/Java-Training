package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


//問題2 クラス名はQuestions02
//家計簿テーブルの日付が今日の日付の行を削除

public class Question02 {
	public static void main(String[] args) {
		
		// 接続文字列の設定
		// DB のURL
		String url = "jdbc:postgresql://localhost:5432/postgres";
		// DB のユーザー名
		String user ="postgres";
		// DB のパスワード
		String password = "postgrestest";
		// SQL 文の作成
		String sql = "DELETE FROM 家計簿 WHERE 日付 = ?";
				
		try (// PostgreSQL に接続
				Connection con = DriverManager.getConnection(url, user, password);
				// SQL を実行するためのインスタンスを生成
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//プレースホルダーに値をセットする
			pstmt.setDate(1, new java.sql.Date(new Date().getTime()));
			// SQL の実行し、実行件数結果を格納する
			int num = pstmt.executeUpdate();
			// 実行件数結果を出力
			System.out.println(num+"行削除されました");

		} catch (SQLException e) {
			//  SQLExceptionの例外処理
			e.printStackTrace();
		}
		
	}
}
