package print7_Date_TimeAPI.Questions03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("yyyy年MM月dd日(E) HH時mm分ss秒");
		
		System.out.println(now.format(formatter));
		
	}
}
