package print7_Date_TimeAPI.Questions01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		
		Date now = new Date();
		System.out.println(now);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		System.out.printf(format.format(now));
	}
}
