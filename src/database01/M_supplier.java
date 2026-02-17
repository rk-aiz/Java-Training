package database01;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 仕入先情報を保持するモデルクラスです。
 */
public class M_supplier {
	
	private int supCode;
	private String supName;
	private String address;
	private String tel;
	private String repName;
	private Date updateDate;
	
	private static final SimpleDateFormat DATE_FORMAT = 
			new SimpleDateFormat("yyyy/MM/dd");
	
	/**
	 * デフォルトコンストラクタ
	 */
	public M_supplier() {}
	
	/**
	 * 全フィールドを初期化するコンストラクタ
	 */
	public M_supplier(
			int supCode,
			String supName,
			String address,
			String tel,
			String repName,
			Date updateDate) {
		this.supCode = supCode;
		this.supName = supName;
		this.address = address;
		this.tel = tel;
		this.repName = repName;
		this.updateDate = updateDate;
	}
	
	/**
	 * 仕入先情報を標準出力に表示します。
	 */
	public void show() {
		System.out.printf(
				"%d : %s : %s : %s : %s : %s\n",
				this.getSupCode(),
				this.getSupName(),
				this.getAddress(),
				this.getTel(),
				this.getRepName(),
				getFormattedUpdateDate()
				);
	}

	public int getSupCode() {
		return supCode;
	}

	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	public String getFormattedUpdateDate() {
		if (this.getUpdateDate() == null)
			return "";
		else
			return DATE_FORMAT.format(this.getUpdateDate());
	}
	
	public java.sql.Date getSqlUpdateDate() {
		if (this.getUpdateDate() == null) return null;
		return new java.sql.Date(this.getUpdateDate().getTime());
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
