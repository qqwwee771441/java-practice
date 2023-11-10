package com.fxmx.exam;

import java.time.Instant;
import java.time.Duration;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Period;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

import java.time.format.DateTimeFormatter;

public class DateTimeMain {
	public static void main(String[] args) {
		printDurationFor(100);
		
		LocalDate today = LocalDate.now();
		System.out.println(today);
		System.out.println(today.getYear() + " " + today.getMonthValue() + " " + today.getDayOfMonth());
		LocalDate xmas = LocalDate.of(2022, 12, 25);
		LocalDate eve = xmas.minusDays(1);
		Period left = Period.between(today, eve);
		System.out.println(left.getMonths() + " " + left.getDays());
		
		LocalTime now = LocalTime.now();
		LocalTime mt = LocalTime.of(20, 30);
		LocalTime cmt = mt.plusHours(2);
		Duration between = Duration.between(now, cmt);
		System.out.println(between.toHoursPart() + " " + between.toMinutesPart());
		
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime dt1 = LocalDateTime.of(2022, 1, 25, 14, 25);
		LocalDateTime dt2 = LocalDateTime.of(2022, 1, 24, 18, 40);
		LocalDateTime dt = dt1.isBefore(dt2)?dt1:dt2;
		Period day = Period.between(ldt.toLocalDate(), dt.toLocalDate());
		Duration time = Duration.between(ldt.toLocalTime(), dt.toLocalTime());
		System.out.println(day + " " + time);
		
		ZoneId.getAvailableZoneIds()
			  .stream()
			  .filter(s->s.startsWith("Asia"))
			  .sorted()
			  .forEach(System.out::println);
		ZoneId seoul = ZoneId.of("Asia/Seoul");
		System.out.println(seoul);
		
		ZonedDateTime here = ZonedDateTime.now();
		System.out.println(here);
		ZonedDateTime paris = ZonedDateTime.of(here.toLocalDateTime(), ZoneId.of("Europe/Paris"));
		System.out.println(paris);
		System.out.println(Duration.between(here, paris));
		
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");
		System.out.println(here.format(fm));
	}
	public static void printDurationFor(int num) {
		Instant start = Instant.now();
		System.out.println(start.getEpochSecond());
		
		for(int i=0; i<num; i++) 
			System.out.print(i + " "); 
		System.out.println();
		
		Instant end = Instant.now();
		System.out.println(end.getEpochSecond());
		Duration between = Duration.between(start, end);
		System.out.println(between.toMillis());
	}
}
