package database01;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 商品情報を保持するモデルクラスです。
 */
public class M_goods {
	
	private int itemCode;
	private String itemName;
	private int supCode;
	private int price;
	private Date updateDate;
	private M_supplier supplier;
	
	private static final SimpleDateFormat DATE_FORMAT = 
			new SimpleDateFormat("yyyy/MM/dd");
	
	/**
	 * デフォルトコンストラクタ
	 */
	public M_goods() {}
	
	/**
	 * 全フィールドを初期化するコンストラクタ
	 */
	public M_goods(
			int itemCode,
			String itemName,
			int supCode,
			int price,
			Date updateDate
			) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.supCode = supCode;
		this.price = price;
		this.updateDate = updateDate;
	}
	
	/**
	 * 商品情報を標準出力に表示します。
	 */
	public void show() {
		System.out.printf(
				"%d : %s : %d : %d : %s\n",
				this.getItemCode(),
				this.getItemName(),
				this.getSupCode(),
				this.getPrice(),
				this.getFormattedUpdateDate()
				);
	}
	
	/**
	 * 仕入先情報を含めて商品情報を表示します。
	 */
	public void showWithSupplier() {
		if (this.supplier == null) {
			System.out.println("仕入先情報がありません。");
			return;
		}
		System.out.printf(
				"商品名:%s 仕入先名:%s 住所:%s 値段:%d\n",
				this.getItemName(),
				this.getSupplier().getSupName(),
				this.getSupplier().getAddress(),
				this.getPrice()
				);
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getSupCode() {
		return supCode;
	}

	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	/**
	 * 更新日をフォーマット済みの文字列で取得します。
	 * @return yyyy/MM/dd 形式の文字列
	 */
	public String getFormattedUpdateDate() {
		if (this.getUpdateDate() == null)
			return "";
		else
			return DATE_FORMAT.format(this.getUpdateDate());
	}
	
	/**
	 * JDBC用のjava.sql.Dateを取得します。
	 * @return java.sql.Date
	 */
	public java.sql.Date getSqlUpdateDate() {
		if (this.getUpdateDate() == null) return null;
		return new java.sql.Date(this.getUpdateDate().getTime());
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public M_supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(M_supplier supplier) {
		this.supplier = supplier;
	}
}
