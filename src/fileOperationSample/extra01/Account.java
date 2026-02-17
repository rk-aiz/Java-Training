package fileOperationSample.extra01;

import java.util.List;

public class Account {
	private String name;
	private int balance;
	private int accountNum;
	private static int counter = 1000;
	
	public Account(
			String name,
			int balance
			) {
		this.setName(name);
		this.setBalance(balance);
		this.setAccountNum(counter);
		setCounter(getCounter() + 1);
	}
	
	public Account(
			String name,
			int balance,
			int accountNum) {
		this(name, balance);
		this.setAccountNum(accountNum);
		if (accountNum > getCounter()) {
			setCounter(accountNum);
		}
	}
	
	public void deposit(int money) {
		if (money <= 0) {
			System.out.println("0以下の金額は扱えません");
			return;
		}
		
		this.setBalance(this.getBalance() + money);
	}
	
	public void withdraw(int money) {
		if (money <= 0) {
			System.out.println("0以下の金額は扱えません");
			return;
		}
		
		if (!chkMoney(this, money)) {
			System.out.println(
					this.getName() + "の預金残高が不足しています");
			return;
		}
		
		this.setBalance(this.getBalance() - money);
	}
	
	public void show() {
		System.out.printf(
				"口座名義：%s 口座番号：%d 預金残高：%d\n",
				this.getName(),
				this.getAccountNum(),
				this.getBalance());
	}
	
	public static void transfer(Account b1, Account b2, int money) {
		if (money <= 0) {
			System.out.println("0以下の金額は扱えません");
			return;
		}
		
		if (!chkMoney(b1, money)) {
			System.out.println(
					b1.getName() + "の預金残高が不足しています");
			return;
		}
		
		b1.withdraw(money);
		b2.deposit(money);
		System.out.printf(
				"%sから%sへ、%d円送金しました\n",
				b1.getName(),
				b2.getName(),
				money);
	}
	
	public static boolean chkMoney(Account account, int money) {
		if (money > account.getBalance()) {
			return false;
		} else {
			return true;
		}
	}
	
	public static int chkAccountNum(List<Account> accounts, int accountNum) {
		for(int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNum() == accountNum) {
				return i;
			}
		}
		
		return -1;
	}
	
	public String toCsvData() {
		return String.format("%d,%s,%d", 
				this.getAccountNum(),
				this.getName(),
				this.getBalance());
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return this.balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAccountNum() {
		return this.accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		Account.counter = counter;
	}
}
