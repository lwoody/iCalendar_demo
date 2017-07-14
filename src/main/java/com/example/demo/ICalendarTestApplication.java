package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ICalendarTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ICalendarTestApplication.class, args);

//		//1. reading an CalendarData - by String
//		String str =
//				"BEGIN:VCALENDAR\r\n" +
//						"VERSION:2.0\r\n" +
//						"PRODID:-//Microsoft Corporation//Outlook 14.0 MIMEDIR//EN\r\n" +
//						"BEGIN:VEVENT\r\n" +
//						"UID:0123\r\n" +
//						"DTSTAMP:20130601T080000Z\r\n" +
//						"SUMMARY;LANGUAGE=en-us:Team Meeting\r\n" +
//						"DTSTART:20130610T120000Z\r\n" +
//						"DURATION:PT1H\r\n" +
//						"RRULE:FREQ=WEEKLY;INTERVAL=2\r\n" +
//						"END:VEVENT\r\n" +
//						"END:VCALENDAR\r\n";
//
//		ICalendar ical = Biweekly.parse(str).first();
//
//		VEvent event = ical.getEvents().get(0);
//		String summary = event.getSummary().getValue();
//		System.out.println("1. reading an CalendarData");
//		System.out.println(summary);
//
//		//2. writing an CalendarData
//		ICalendar ical2 = new ICalendar();
//		VEvent event2 = new VEvent();
//		event.setSummary("the summary");
//
//		ical2.addEvent(event2);
//		File file = new File("C:/Users/NAVER/Desktop/iCalendar_test/target/classes/static/iCalData/iCalData.ics");
//		try {
//			Biweekly.write(ical2).go(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		//3. parsing icalendar file
//		File file3 = new File("C:/Users/NAVER/Desktop/icalendar_test.txt");
//		List<ICalendar> icals = null;
//		try {
//			icals = Biweekly.parse(file3).all();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally {
//
//			VEvent event3 = icals.get(0).getEvents().get(0);
//			String summary3 = event3.getSummary().getValue();
//			System.out.println("3. parsing icalendar file");
//			System.out.println(summary3);
//		}
//
//		//4. reading json format file
//		File file4 = new File("C:/Users/NAVER/Desktop/test2.json");
//		JCalReader reader = null;
//		try {
//			reader = new JCalReader(file4);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//			ICalendar ical3;
//			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//			try {
//				while ((ical = reader.readNext()) != null) {
//					for (VEvent event3 : ical.getEvents()) {
//						DateStart dateStart = event3.getDateStart();
//						String dateStartStr = (dateStart == null) ? null : df.format(dateStart.getValue());
//
//						Summary summary3 = event3.getSummary();
//						String summaryStr = (summary3 == null) ? null : summary3.getValue();
//
//						if (summaryStr != null && dateStartStr != null) {
//							System.out.println("4. reading json format file ");
//							System.out.println(dateStartStr + ": " + summaryStr);
//							continue;
//						}
//
//						if (summaryStr != null){
//							System.out.println(summaryStr);
//							continue;
//						}
//
//						if (dateStartStr != null){
//							System.out.println(dateStartStr);
//							continue;
//						}
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} finally {
//			try {
//				reader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
