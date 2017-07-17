/**
 * Created by NAVER on 2017-07-17.
 */


$(document).ready(function() {

    $("#create-new-calendar-file").click(function () {
        $.post("http://localhost:8080/create-new-calendar-file")
            .done(function (data) {
                $("#result1").append(data);
            });
    });

    $("#parse-calendar-string").click(function () {
        $.post("http://localhost:8080/parse-calendar-string")
            .done(function (data) {
                $("#result2").append(data);
            });
    });

    $("#parse-calendar-file").click(function () {
        $.post("http://localhost:8080/parse-calendar-file")
            .done(function (data) {
                $("#result3").append(data);
            });
    });


    $("#create-allday-event").click(function () {
        $.post("http://localhost:8080/create-allday-event")
            .done(function (data) {
                $("#result4").append(data);
            });
    });

    $("#create-fourhour-event").click(function () {
        $.post("http://localhost:8080/create-fourhour-event")
            .done(function (data) {
                $("#result5").append(data);
            });
    });

});
