package com.example.demo;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.io.TimezoneAssignment;
import net.fortuna.ical4j.data.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by NAVER on 2017-07-14.
 */
@Controller
public class BiweeklyController {

    @GetMapping("/index2")
    public String index2(Model model) throws IOException, ParserException {

        return "index2";
    }

    @RequestMapping(value="/month_6")
    public String month_6(Model model){

        //사용자 기존 캘린더 입력정보 ics로부터 불러오기
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/iCalData/iCalData.ics");

        //기존 입력정보의 이벤트들 리스트로 담기
        ICalendar ical = null;
        List<CalendarData> dataList = new ArrayList<>();
        try {
            ical = Biweekly.parse(file).first();//VCALENDAR는 유일하다 가정
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            //각 이벤트의 정보(내용,날짜)를 Calendar오브젝트에 담기
            for(VEvent event:ical.getEvents()){
                CalendarData data = new CalendarData();
                data.setEventSummary(event.getSummary().getValue());
                data.setStartDate(event.getDateStart().getValue());
                data.setEndDate(event.getDateEnd().getValue());
                dataList.add(data);
            }
        }

        model.addAttribute("dataList",dataList);
        return "month_6";
    }

    @RequestMapping(value = "/month_7")
    public String month_7(Model model){

        //사용자 기존 캘린더 입력정보 ics로부터 불러오기
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/iCalData/iCalData.ics");

        //기존 입력정보의 이벤트들 리스트로 담기
        ICalendar ical = null;
        List<CalendarData> dataList = new ArrayList<>();
        try {
            ical = Biweekly.parse(file).first();//VCALENDAR는 유일하다 가정
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            //각 이벤트의 정보(내용,날짜)를 Calendar오브젝트에 담기
            for(VEvent event:ical.getEvents()){
                CalendarData data = new CalendarData();
                data.setEventSummary(event.getSummary().getValue());
                data.setStartDate(event.getDateStart().getValue());
                data.setEndDate(event.getDateEnd().getValue());
                dataList.add(data);
            }
        }

        model.addAttribute("dataList",dataList);
        return "month_7";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){

        //사용자 기존 캘린더 입력정보 ics로부터 불러오기
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/iCalData/iCalData.ics");

        //기존 입력정보의 이벤트들 리스트로 담기
        ICalendar ical = null;
        List<CalendarDataString> dataList = new ArrayList<>();
        try {
            ical = Biweekly.parse(file).first();//VCALENDAR는 유일하다 가정
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            //각 이벤트의 정보(내용,날짜)를 Calendar오브젝트에 담기

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            for(VEvent event:ical.getEvents()){
                CalendarDataString data = new CalendarDataString();
                data.setEventSummary(event.getSummary().getValue());
                data.setStartDate(formatter.format(event.getDateStart().getValue()));
                data.setEndDate(formatter.format(event.getDateEnd().getValue()));
                dataList.add(data);
            }
        }

        model.addAttribute("dataList",dataList);
        return "index";
    }

    @PostMapping(value="/add")
    public String add(@ModelAttribute CalendarData data, Model model){

        //기존 데이터파일 불러오기(사용자의 고유 저장공간)
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/iCalData/iCalData.ics");
        ICalendar ical = null;
        try {
            ical = Biweekly.parse(file).first();//VCALENDAR component가 1개 존재한다고 가정

            //timezone 적용
            //TimezoneAssignment object 생성
            TimezoneAssignment seoul = TimezoneAssignment.download(
                    TimeZone.getTimeZone("Asia/Seoul"),
                    true
            );
            //iCalendar object에 설정
            ical.getTimezoneInfo().setDefaultTimezone(seoul);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //새로운 이벤트 생성
        VEvent event = new VEvent();

        //setting event infromation
        event.setSummary(data.getEventSummary());//event summary set
        event.setDateStart(data.getStartDate());//event startDate set
        event.setDateEnd(data.getEndDate());//event endDate set
        ical.addEvent(event);

        try {
            Biweekly.write(ical).go(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("resultData", data);

        return "index";
    }

    @Autowired
    private PrintConverter converter;

    @PostMapping("/preview")
    public String viewPreview(@RequestParam String month, Model model){

        PrintRequest print = new PrintRequest();

        String extendIn = "http://localhost:8080/month_" + month;
        String extendOut = "C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/images/sample" + month + ".png";

        print.setIn(extendIn);
        print.setOut(extendOut);

        model.addAttribute("month", month);

        //가로방향 미리보기 이미지
        converter.createImage(print,0);

        //세로방향 미리보기 이미지 생성해둠
        String tempExtendOut = "C:/Users/NAVER/Desktop/iCalendar_demo/target/classes/static/images/sample_vertical.png";
        print.setOut(tempExtendOut);
        converter.createImage(print,1);

        return "preview";
    }

    //converter for pdf save and print
    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public String convert(
            @RequestParam("startMonth") int startMonth,
            @RequestParam("endMonth") int endMonth,
            @RequestParam("orientation") int orientation
    ){
        //converting html to pdf - by url
        try {
            converter.makeAPdf(startMonth, endMonth, orientation);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "preview";
    }

}
