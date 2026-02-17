package database01;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		AccessDB adb = new AccessDB();
		adb.connect();
		
		List<M_goods> goodsList = adb.getGoodsList();
		List<M_supplier> supplierList = adb.getSupplierList();
		
		for(M_goods g : goodsList) {
			g.show();
		}
		
		for(M_supplier s : supplierList) {
			s.show();
		}
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("商品情報を取得します\n商品番号を入力してください");
			int itemCode = readInt(sc);
			M_goods goods = adb.getGoods(itemCode);
			if (goods == null) {
				System.out.println("該当する商品が見つかりませんでした。");
			} else {
				goods.showWithSupplier();
			}
			
			addGoods(adb, sc, supplierList);
			
			delGoods(adb, sc);
			
			renameGoods(adb, sc);
		}
		
		adb.disConnect();
	}
	
	/**
	 * 商品リストの中から最大の商品番号を取得します。
	 * @param goodsList 商品リスト
	 * @return 最大の商品番号
	 */
	public static int getMaxGoodsCode(List<M_goods> goodsList) {
		int max = 0;
		for (M_goods g : goodsList) {
			max = Math.max(max, g.getItemCode());
		}
		return max;
	}
	
	/**
	 * ユーザー入力に基づいて商品を追加します。
	 * @param adb データベースアクセスオブジェクト
	 * @param sc スキャナー
	 * @param supList 仕入先リスト
	 */
	public static void addGoods(
			AccessDB adb, 
			Scanner sc,
			List<M_supplier> supList) {
		
		System.out.println(
				"商品マスタに1件追加します\n商品名を入力してください");
		String itemname = sc.nextLine();
		
		int supCode = 0;
		while(true) {
			System.out.println(
					"仕入れ先番号を指定してください");
			int tempSupCode = readInt(sc);
			if (supList.stream().anyMatch(
					s -> s.getSupCode() == tempSupCode)) {
				supCode = tempSupCode;
				break;
			} else {
				System.out.println("有効な仕入先番号を入力してください。");
			}
		}
		
		System.out.println(
				"金額を設定してください");
		int price = readInt(sc);
		
		int nextItemCode = getMaxGoodsCode(adb.getGoodsList()) + 1;

		M_goods newGoods = new M_goods();
		newGoods.setItemCode(nextItemCode);
		newGoods.setItemName(itemname);
		newGoods.setSupCode(supCode);
		newGoods.setPrice(price);
		
		int insert = adb.insertM_goods(newGoods);
		if (insert == 0) {
			System.out.println(
					"商品マスタに登録できませんでした");
		} else {
			System.out.println(
					"商品マスタに登録しました。");
		}
	}
	
	/**
	 * ユーザー入力に基づいて商品を削除します。
	 * @param adb データベースアクセスオブジェクト
	 * @param sc スキャナー
	 */
	public static void delGoods(
			AccessDB adb, 
			Scanner sc) {
		
		System.out.println(
				"商品マスタから1件削除します\n商品番号を入力してください");
		int itemCode = readInt(sc);
		
		int delete = adb.deleteFromM_goods(itemCode);
		if (delete == 0) {
			System.out.println(
					"商品マスタに存在しない商品番号です");
		} else {
			System.out.println(
					"商品マスタから削除しました");
		}
	}
	
	/**
	 * ユーザー入力に基づいて商品の情報を更新します。
	 * @param adb データベースアクセスオブジェクト
	 * @param sc スキャナー
	 */
	public static void renameGoods(
			AccessDB adb, 
			Scanner sc) {
		
		System.out.println(
				"商品マスタの商品名を変更します\n商品番号を入力してください");
		int itemCode = readInt(sc);
		
		M_goods goods = adb.getGoods(itemCode);
		if (goods == null) {
			System.out.println("該当する商品が見つかりませんでした。");
			return;
		}
		
		System.out.println(
				"商品名を変更します。名前を入力してください");
		goods.setItemName(sc.nextLine());
		System.out.println(
				"商品の金額を変更します。値を入力してください");
		goods.setPrice(readInt(sc));
		
		int update = adb.updateM_goods(goods);
		if (update == 0) {
			System.out.println(
					"商品マスタを更新できませんでした");
		} else {
			System.out.println(
					"商品マスタを更新しました。");
		}
	}

	/**
	 * Scannerから整数を安全に読み取ります。
	 * @param sc スキャナー
	 * @return 入力された整数
	 */
	private static int readInt(Scanner sc) {
		while (true) {
			try {
				String line = sc.nextLine();
				return Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.out.println("数値を入力してください。");
			}
		}
	}
}
