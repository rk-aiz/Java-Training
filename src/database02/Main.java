package database02;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		String filename = "TempData.csv";
		
		AccessDB adb = new AccessDB();
		adb.connect();
		
		// DBを初期化しておく
		adb.deleteAllData();
		
		CsvFileOperation cfr = new CsvFileOperation(filename);
		
		System.out.println(
				"ファイルからデータを読み込み、データベースに追加します。");
		String msg = cfr.importFromCsv(adb);
		System.out.println(msg);
		adb.printAllData();
		
		try (Scanner sc = new Scanner(System.in)) {

			
			System.out.println("4月または5月のデータをファイル出力します");
			int selectMonth = nextInt(
					sc,
					"4か5の数値を入力してください",
					"4か5の数値を入力してください", 4, 5);
			
			String monthFilename = selectMonth == 4 ? "AprilData.csv" : "MayData.csv";
			cfr.setFilepath(monthFilename);
			
			int count = adb.exportMonthlyData(String.format("%02d", selectMonth), cfr);
			System.out.println(
					monthFilename + "に" + count +"件出力しました");
		}
		
		adb.disConnect();
	}
	
    /**
     * 指定された範囲内の数値入力を受け付けます。
     * @param sc スキャナー
     * @param msg 初回メッセージ
     * @param retryMsg 再試行メッセージ
     * @param min 最小値
     * @param max 最大値
     * @return 入力された数値
     */
    private static int nextInt(Scanner sc, String msg, String retryMsg, int min, int max) {
        System.out.println(msg);
        while (true) {
            try {
                int input = Integer.parseInt(sc.nextLine());
                if (min <= input && input <= max) {
                    return input;
                }
            } catch (NumberFormatException ex) {
                // ignore
            }
            System.out.println(retryMsg);
        }
    }
}
