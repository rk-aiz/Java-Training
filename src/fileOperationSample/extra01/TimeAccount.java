package fileOperationSample.extra01;

public class TimeAccount extends Account {

	private int timeBalance;
	
	public TimeAccount(
			String name,
			int balance,
			int timeBalance,
			int accountNum) {
		super(name, timeBalance, accountNum);
		this.setTimeBalance(timeBalance);
	}
	
	public TimeAccount(
			String name,
			int balance,
			int timeBalance) {
		super(name, timeBalance);
		this.setTimeBalance(timeBalance);
	}
	
	public void cancel() {
		this.setBalance(this.getBalance() + this.getTimeBalance());
		this.setTimeBalance(0);
	}
	
	@Override
	public void show() {
		System.out.printf(
				"口座名義：%s 口座番号：%d 預金残高：%d 定期預金残高：%d\n",
				this.getName(),
				this.getAccountNum(),
				this.getBalance(),
				this.getTimeBalance());
	}
	
	@Override
	public String toCsvData() {
		return super.toCsvData() + "," + this.getTimeBalance();
	}
	
	public int getTimeBalance() {
		return this.timeBalance;
	}

	public void setTimeBalance(int timeBalance) {
		this.timeBalance = timeBalance;
	}
	
	
}
