package fileOperationSample.extra01;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static final int READFILE = 0;
	static final int OPERATION_CONTINUE = 1;
	
	public static void main(String[] args) {
		
		//テスト用
		File testInput = new File(System.getProperty("user.home") + "/Desktop/inputAccount.txt");
		
		CsvFileOperation csvFileOperation = new CsvFileOperation(
				"file/bankdata.csv");
		
		List<Account> accounts = new ArrayList<Account>();
		
		try (Scanner sc = new Scanner(System.in)) {
			if (csvFileOperation.fileExists()) {
				String input = inputKey(READFILE, sc);
				if (input.equals("y")) {
					String msg = csvFileOperation.csvReader(accounts);
					System.out.println(msg);
				}
			}
			while(true) {
				System.out.println("口座操作入力をしてください。");
				System.out.println(
						"1:口座作成 2:入金 3:出金 4:送金 5：口座削除 6：定期口座解約 7：口座一覧表示");
				try {
					int select = Integer.parseInt(sc.nextLine());
					switch(select) {
						case 1 -> { createAccount(sc, accounts); }
						case 2 -> { depositAccount(sc, accounts); }
						case 3 -> { withdrawAccount(sc, accounts); }
						case 4 -> { transferAccount(sc, accounts); }
						case 5 -> { removeAccount(sc, accounts); }
						case 6 -> { cancelTimeAccount(sc, accounts); }
						case 7 -> { listAccoutns(accounts); }
						default -> { continue; }
					}
				} catch (InputMismatchException |
						NumberFormatException ex) {
					continue;
				}
				
				if (inputKey(OPERATION_CONTINUE, sc).equals("n")) {
					break;
				}
			}
		}
		
		if (accounts.size() > 0)
			csvFileOperation.CsvWriter(accounts);
		
		System.out.println("プログラムを終了します");
	}
	
	public static void createAccount(Scanner sc, List<Account> accounts) {

		int accountType = nextInt(
				sc,
				"口座を作成します\n普通預金口座を作成の時は1、定期預金口座を作成の時は2を入力してください",
				"1または2を入力してください",
				1, 2);
		String name = nextLine(sc, "口座名義を入力してください");
		int balance = nextInt(sc, 
				"預金残高を入力してください", 
				"0より大きい値を入力してください",
				0, Integer.MAX_VALUE);
		
		int accountNum = 0;
		switch(accountType) {
			case 1 -> {
				Account account = new Account(name, balance);
				accountNum = account.getAccountNum();
				accounts.add(account);
			}
			case 2 -> {
				int timeBalance = nextInt(sc, 
						"定期預金残高を入力してください", 
						"0より大きい値を入力してください",
						0, Integer.MAX_VALUE);
				TimeAccount timeAccount = new TimeAccount(
						name, balance, timeBalance);
				accountNum = timeAccount.getAccountNum();
				accounts.add(timeAccount);
			}
		}
		
		System.out.println(name + "の口座を作成しました。口座番号：" + accountNum);
	}
	
	public static void depositAccount(Scanner sc, List<Account> accounts) {

		int accountNum = nextInt(
				sc,
				"入金処理を行います\n入金したい口座番号を指定してください",
				"0以上の数値を入力してください",
				0, Integer.MAX_VALUE);
		
		int index = Account.chkAccountNum(accounts, accountNum);
		
		if (index < 0) {
			System.out.println("口座が存在しません");
		} else {
			int deposit = nextInt(sc, 
					"入金額を入力してください", 
					"0より大きい値を入力してください",
					0, Integer.MAX_VALUE);
			accounts.get(index).deposit(deposit);
		}
		System.out.println("入金処理を終了します");
	}
	
	public static void withdrawAccount(Scanner sc, List<Account> accounts) {

		int accountNum = nextInt(
				sc,
				"出金処理を行います\n出金したい口座番号を指定してください",
				"0以上の数値を入力してください",
				0, Integer.MAX_VALUE);
		
		int index = Account.chkAccountNum(accounts, accountNum);
		
		if (index < 0) {
			System.out.println("口座が存在しません");
		} else {
			int withdraw = nextInt(sc, 
					"出金額を入力してください", 
					"0より大きい値を入力してください",
					0, Integer.MAX_VALUE);
			accounts.get(index).withdraw(withdraw);
		}
		System.out.println("出金処理を終了します");
	}

	public static void transferAccount(Scanner sc, List<Account> accounts) {

		int senderNum = nextInt(
				sc,
				"送金処理を行います\n送金元の口座番号を指定してください",
				"0以上の数値を入力してください",
				0, Integer.MAX_VALUE);
		
		int receiverNum = nextInt(
				sc,
				"送金先の口座番号を指定してください",
				"0以上の数値を入力してください",
				0, Integer.MAX_VALUE);
		
		int senderIndex = Account.chkAccountNum(accounts, senderNum);
		int receiverIndex = Account.chkAccountNum(accounts, receiverNum);
		
		if (senderIndex < 0 || receiverIndex < 0) {
			System.out.println("送金元もしくは送金先の口座が存在しません");
		} else {
			int transfar = nextInt(sc, 
					"送金額を入力してください", 
					"0より大きい値を入力してください",
					0, Integer.MAX_VALUE);
			Account.transfer(
					accounts.get(senderIndex), 
					accounts.get(receiverIndex), transfar);
		}
		System.out.println("送金処理を終了します");
	}
	
	public static void removeAccount(Scanner sc, List<Account> accounts) {
		
		int accountNum = nextInt(
				sc,
				"削除したい口座番号を指定してください",
				"0以上の数値を入力してください",
				0, Integer.MAX_VALUE);
		
		int index = Account.chkAccountNum(accounts, accountNum);
		if (index < 0) {
			System.out.println("指定口座が存在しません");
		} else {
			System.out.println(
					accounts.get(index).getName() + "の口座を削除しました");
			accounts.remove(index);
		}
		
		System.out.println("口座削除処理を終了します");
	}
	
	public static void cancelTimeAccount(Scanner sc, List<Account> accounts) {
		
		int accountNum = nextInt(
				sc,
				"定期を解約する口座番号を指定してください",
				"0以上の数値を入力してください",
				0, Integer.MAX_VALUE);
		
		int index = Account.chkAccountNum(accounts, accountNum);
		if (index < 0) {
			System.out.println("指定口座が存在しません");
		} else if (accounts.get(index) instanceof TimeAccount ta) {
			System.out.println(
					accounts.get(index).getName() + 
					"の定期預金残高を解約しました");
			ta.cancel();
		} else {
			System.out.println("定期口座ではありません");
		}
		
		System.out.println("口座削除処理を終了します");
	}
	
	public static void listAccoutns(List<Account> accounts) {
		for(Account a : accounts) {
			a.show();
		}
	}
	
	public static int nextInt(
			Scanner sc, String msg, String retryMsg, int min, int max) {
		System.out.println(msg);
		while(true) {
			try {
				int input = Integer.parseInt(sc.nextLine());
				if (min <= input && input <= max) {
					return input;
				}
			} catch (InputMismatchException |
					NumberFormatException ex) {
				//ex.printStackTrace();
			}
			System.out.println(retryMsg);
		}
	}
	
	public static String nextLine(
			Scanner sc, String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}
	
	public static String inputKey(int mode, Scanner sc) {
		
		if (mode == 0) {
			System.out.println(
					"既にデータファイルが存在します。読み込みますか？ y/n");
		} else if(mode == 1) {
			System.out.println(
					"続けて操作を行いますか？ y/n");
		}
		
		while (true) {
			String input = sc.nextLine().toLowerCase();
			if (input.equals("y") ||
					input.equals("n")) {
				return input;
			} else {
				System.out.println("yまたはnを入力してください");
			}
		}
		
	}
}
