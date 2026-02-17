package database02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 気温データを保持するモデルクラスです。
 */
public class TempData {
	
	private Date date;
	private double averageTemp;
	private double maxTemp;
	private double minTemp;
	
	private static final SimpleDateFormat DISPLAY_FORMAT = 
			new SimpleDateFormat("MM月dd日");
	
	private static final SimpleDateFormat CSV_FORMAT = 
			new SimpleDateFormat("yyyy/MM/dd");
	
	private static final String DISPLAY_TEMPLATE = 
			"%s 平均気温：%.1f 最高気温：%.1f 最低気温：%.1f";
	
	/**
	 * デフォルトコンストラクタ
	 */
	public TempData() {}
	
	/**
	 * 全フィールドを初期化するコンストラクタ
	 */
	public TempData(
			Date date,
			double averageTemp,
			double maxTemp,
			double minTemp) {
		
		this.date = date;
		this.averageTemp = averageTemp;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
	}
	
	@Override
	public String toString() {
		return DISPLAY_TEMPLATE.formatted(
				DISPLAY_FORMAT.format(this.getDate()),
				this.getAverageTemp(),
				this.getMaxTemp(),
				this.getMinTemp());
	}
	
    /**
     * CSV形式の文字列1行から、TempDataインスタンスに変換します
     * @param line CSVの1行
     * @return TempDataインスタンス
     */
    public static TempData fromCsv(String line) {
        try {
            String[] data = line.split(",", 4);
            if (data.length != 4) {
                throw new IllegalArgumentException("CSVのフォーマットが不正です: " + line);
            }
            Date date = CSV_FORMAT.parse(data[0]);
            double averageTemp = Double.parseDouble(data[1]);
            double maxTemp = Double.parseDouble(data[2]);
            double minTemp = Double.parseDouble(data[3]);
            return new TempData(date, averageTemp, maxTemp, minTemp);
        } catch (NumberFormatException | ParseException e) {
            throw new IllegalArgumentException("CSVの行からTempDataインスタンスを生成できません: " + line, e);
		}
    }

    /**
     * 気温のデータをCSV形式の文字列1行に変換します
     * @return CSV形式の文字列
     */
    public String toCsvLine() {
        return String.format("%s,%.1f,%.1f,%.1f",
                CSV_FORMAT.format(this.getDate()),
                this.getAverageTemp(),
                this.getMaxTemp(),
                this.getMinTemp());
    }
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAverageTemp() {
		return averageTemp;
	}
	public void setAverageTemp(double averageTemp) {
		this.averageTemp = averageTemp;
	}
	public double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}
	public double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}
}
