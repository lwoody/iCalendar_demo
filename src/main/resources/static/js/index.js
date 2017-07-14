/**
 * Created by NAVER on 2017-07-14.
 */

$(document).ready(function() {

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
                                +"<h2>========</h2>");
        });
    })
});