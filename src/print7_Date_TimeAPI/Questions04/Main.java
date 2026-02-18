package print7_Date_TimeAPI.Questions04;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		
		LocalDateTime dt1 = LocalDateTime
				.of(1995, 1, 17, 5, 46, 52);
		
		LocalDateTime dt2 = LocalDateTime
				.of(2011, 3, 11, 14, 46, 18);
		
		LocalDate d1 = dt1.toLocalDate();
		LocalDate d2 = dt2.toLocalDate();
		
		LocalTime t1 = dt1.toLocalTime();
		LocalTime t2 = dt2.toLocalTime();
		
		Period period = Period.between(d1, d2);
		Duration duration = Duration.between(t1, t2);
		
		DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("yyyy年MM月dd日(E) HH時mm分ss秒");
		
		long seconds = duration.getSeconds();
		
		System.out.printf(
				"%d年%dか月%d日%d時間%d分%d秒差です",
				period.getYears(),
				period.getMonths(),
				period.getDays(),
				duration.toHoursPart(),
				duration.toMinutesPart(),
				duration.toSecondsPart()
			);
		
	}
}
