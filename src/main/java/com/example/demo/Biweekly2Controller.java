package com.example.demo;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VFreeBusy;
import biweekly.component.VTodo;
import biweekly.io.TimezoneAssignment;
import biweekly.parameter.FreeBusyType;
import biweekly.parameter.ParticipationLevel;
import biweekly.property.Attendee;
import biweekly.property.FreeBusy;
import biweekly.property.Status;
import biweekly.property.Uid;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by NAVER on 2017-07-17.
 */

@RestController
@EnableAutoConfiguration
public class Biweekly2Controller {

    @PostMapping("/create-new-todo")
    public String createNewCalendarFile() throws IOException{

        //기존 데이터파일 불러오기(사용자의 고유 저장공간)
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/iCalData/iCalData2.ics");
        ICalendar ical = Biweekly.parse(file).first();//VCALENDAR component가 1개 존재한다고 가정

        //timezone 적용
        TimezoneAssignment seoul = TimezoneAssignment.download(TimeZone.getTimeZone("Asia/Seoul"),true);
        ical.getTimezoneInfo().setDefaultTimezone(seoul);

        VTodo todo = new VTodo();
        todo.setSummary("과제");
        Date due = new Date();
        todo.setDateDue(due);
        todo.setStatus(Status.confirmed());

        //save Todo
        ical.addTodo(todo);

        //save in iCalData2.ics
        Biweekly.write(ical).go(file);

        return todo.toString();
    }

    @PostMapping("/create-new-freebusy")
    public String parseCalendarString() throws IOException{

        //기존 데이터파일 불러오기(사용자의 고유 저장공간)
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/iCalData/iCalData2.ics");
        ICalendar ical = Biweekly.parse(file).first();//VCALENDAR component가 1개 존재한다고 가정

        //timezone 적용
        TimezoneAssignment seoul = TimezoneAssignment.download(TimeZone.getTimeZone("Asia/Seoul"),true);
        ical.getTimezoneInfo().setDefaultTimezone(seoul);

        VFreeBusy freebusy = new VFreeBusy();

        FreeBusy fb = new FreeBusy();
        fb.setType(FreeBusyType.BUSY);
        //date오브젝트에 시간 설정하기
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,20);
        Date start = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,25);
        Date end = cal.getTime();
        freebusy.setDateStart(start);
        freebusy.setDateEnd(end);
        freebusy.addFreeBusy(fb);

        ical.addFreeBusy(freebusy);

        //save in iCalData2.ics
        Biweekly.write(ical).go(file);

        return freebusy.toString();
    }

    @PostMapping("/create-fourhour-event")
    public String createFourHourEvent() throws IOException{

        //기존 데이터파일 불러오기(사용자의 고유 저장공간)
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/iCalData/iCalData2.ics");
        ICalendar ical = Biweekly.parse(file).first();//VCALENDAR component가 1개 존재한다고 가정

        //timezone 적용
        TimezoneAssignment seoul = TimezoneAssignment.download(TimeZone.getTimeZone("Asia/Seoul"),true);
        ical.getTimezoneInfo().setDefaultTimezone(seoul);

        //4시간짜리 이벤트
        //Start time
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,13);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date start = cal.getTime();
        //End time
        cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,17);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date end = cal.getTime();

        //이벤트 생성
        VEvent event = new VEvent();
        event.setDateStart(start);
        event.setDateEnd(end);
        event.setSummary("미팅");
        event.setUid(Uid.random());

        // attendees 추가
        Attendee dev1 = new Attendee("참가자1","dev1@mycompany.com");
        dev1.setParticipationLevel(ParticipationLevel.REQUIRED);
        event.addAttendee(dev1);

        Attendee dev2 = new Attendee("참가자2","dev2@mycompany.com");
        dev2.setParticipationLevel(ParticipationLevel.OPTIONAL);
        event.addAttendee(dev2);

        //ical에 이벤트 추가
        ical.addEvent(event);

        //save in iCalData2.ics
        Biweekly.write(ical).go(file);

        return event.toString();
    }
}