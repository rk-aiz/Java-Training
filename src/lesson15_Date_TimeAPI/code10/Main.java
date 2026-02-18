package lesson15_Date_TimeAPI.code10;

public class Main {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("処理にかかった時間は・・・" +
				(end - start) + "ミリ秒でした");
		
	}
	
}
