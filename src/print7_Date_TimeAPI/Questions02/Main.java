package print7_Date_TimeAPI.Questions02;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		
		SimpleDateFormat format = 
				new SimpleDateFormat("yyyy年MM月dd日(E) HH時mm分ss秒");
		
		System.out.printf(format.format(c.getTime()));
	}
}
