package database02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CSVファイルの読み書きを担当するクラスです。
 */
public class CsvFileOperation{
    private String filePath;
    private File file;

    /**
     * コンストラクタ。ファイル名を指定します。
     * @param fileName ファイル名
     */
    public CsvFileOperation(String filename) {
        this.setFilepath(filename);
    }

    /**
     * 操作対象のファイルパスを設定します。
     * @param fileName ファイル名
     */
    public void setFilepath(String filename) {
    	this.filePath = System.getProperty("user.dir") + "/file/" + filename;
    	this.file = new File(this.filePath);
    }
    
    /**
     * ファイルが存在するかを確認します
     */
    public boolean exists() {
        return this.file.exists();
    }

    /**
     * CSVファイルからデータを読み込み、データベースに登録します。
     * @param adb データベースアクセスオブジェクト
     * @return 処理結果メッセージ
     */
    public String importFromCsv(AccessDB adb) {
        
    	List<TempData> tempData = new ArrayList<>();

        try (FileReader fr = new FileReader(this.filePath);
                BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty() || line.charAt(0) == '#') continue;

                TempData newTempData = createTempData(line);
                if (newTempData != null) {
                    tempData.add(newTempData);
                } else {
                    System.err.println("データ変換エラーのため行をスキップしました: " + line);
                }
            }
        } catch (IOException ex) {
            System.err.println("ファイル読み込み中にエラーが発生しました");
            return "ファイル読み込みエラー";
        }
        
        for (TempData td : tempData) {
        	adb.insertData(
        			td.getDate(), 
        			td.getAverageTemp(), 
        			td.getMaxTemp(), 
        			td.getMinTemp());
        }

        return tempData.size() + "件読み込みを完了しました";
    }

    /**
     * リスト形式のデータをCSVファイルに出力します。
     * @param tempData 気温データリスト
     * @return 処理結果メッセージ
     */
    public String exportToCsv(List<TempData> tempData) {
    	
    	writeHeader();
    	
        int countLines = 0;

        try (FileWriter fw = new FileWriter(this.filePath, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            for (TempData td : tempData) {
                bw.write(td.toCsvLine());
                bw.newLine();
                countLines++;
            }
        } catch (IOException e) {
            return "ファイル書き込みエラー";
        }

        return countLines + "件書き込みを完了しました";
    }
    
    /**
     * 単一のデータをCSVファイルに追記します。
     * @param date 日付
     * @param average 平均気温
     * @param max 最高気温
     * @param min 最低気温
     */
    public void exportToCsv(Date date, double average, double max, double min) {
        TempData td = new TempData(date, average, max, min);

        try (FileWriter fw = new FileWriter(this.filePath, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(td.toCsvLine());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * CSVのヘッダー行を書き込みます（既存の内容は上書きされます）。
     */
    public void writeHeader() {

        try (FileWriter fw = new FileWriter(this.filePath);
                BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write("#年月日,平均気温(℃),最高気温(℃),最低気温(℃)"); // ヘッダー書き込み
            bw.newLine();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    /**
     * CSVの行からTempDataインスタンスを取得します
     */
    private TempData createTempData(String line) {
        try {
            String[] parts = line.split(",");
            return switch (parts.length) {
                case 4 -> TempData.fromCsv(line); // 内部で再度splitされるが安全のため
                default -> null;
            };
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getLocalizedMessage());
            return null;
        }
    }
}
