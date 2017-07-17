/**
 * Created by NAVER on 2017-07-14.
 */

$(document).ready(function() {

    //이벤트 추가 저장
    $("#addData").click(function () {
        var eventSummary = $("#eventSummary").val();
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();

        $.post("http://localhost:8080/add",
            {
                "eventSummary": eventSummary,
                "startDate": startDate,
                "endDate": endDate

            }).done(function(){
                $("#post").append("<h3>summary: "+eventSummary+"</h3>"
                                +"<h3>startDate: "+startDate+"</h3>"
                                +"<h3>endDate: "+endDate+"</h3>"
                                +"<h3>========</h3>");
        });
    })

    //캘린더로 다시 돌아가기
    $("#back").click(function(){
        window.location.href = "http://localhost:8080/month_7";
    })
});