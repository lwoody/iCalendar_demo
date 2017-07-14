package com.example.demo;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NAVER on 2017-07-14.
 */
@Controller
@EnableAutoConfiguration
public class iCalController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){

        //사용자 기존 캘린더 입력정보 ics로부터 불러오기
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_test/target/classes/static/iCalData/iCalData.ics");

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
        return "index";
    }

    @PostMapping(value="/add")
    public String add(@ModelAttribute CalendarData data, Model model){

        //기존 데이터파일 불러오기(사용자의 고유 저장공간)
        File file = new File("C:/Users/NAVER/Desktop/iCalendar_test/target/classes/static/iCalData/iCalData.ics");
        ICalendar ical = null;
        try {
            ical = Biweekly.parse(file).first();//VCALENDAR component가 1개 존재한다고 가정
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

}
