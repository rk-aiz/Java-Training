package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSample {
	public static void main(String[] args) {

		// 接続文字列の設定
		// DB のURL
		String url = "jdbc:postgresql://localhost:5432/";
		// DB のユーザー名postgres
		String user ="postgres";
		// DB のパスワード
		String password = "postgrestest";
		//日付の型フォーマットの設定 家計簿テーブルに日付があるため
		SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd");
		// SQL 文の作成
		String sql = "select * from 家計簿 where 費目 = ?";
		
		try (	// PostgreSQL に接続
				Connection con = DriverManager.getConnection(url, user, password);
				// SQL を実行するためのインスタンスを生成
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			//プレースホルダーに値をセットする
			pstmt.setString(1,"食費");
			// SQL の実行結果を格納する
			ResultSet result = pstmt.executeQuery();
			// 実行結果からデータを取得
			while (result.next()) {
				 // 日付
				Date colDate = result.getTimestamp(1);
				 // 費目
				String colItem = result.getString(2);
				 // メモ
				String colMemo = result.getString(3);
				 // 入金額
				int colDeposit = result.getInt(4);
				 // 出金額
				int colWithdrawal = result.getInt(5);
				 // 表示
				System.out.println(df.format(colDate) + ":" + colItem
					+ ":" + colMemo + ":" + colDeposit + ":" + colWithdrawal);
				}
		} catch (SQLException e) {
			// SQLExceptionの例外処理
			e.printStackTrace();
		}
	}
}