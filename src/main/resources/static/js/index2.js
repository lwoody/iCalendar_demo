/**
 * Created by NAVER on 2017-07-17.
 */


$(document).ready(function() {

    $("#create-new-todo").click(function () {
        $.post("http://localhost:8080/create-new-todo")
            .done(function (data) {
                $("#result1").append(data);
            });
    });

    $("#create-new-freebusy").click(function () {
        $.post("http://localhost:8080/create-new-freebusy")
            .done(function (data) {
                $("#result2").append(data);
            });
    });

    $("#create-fourhour-event").click(function () {
        $.post("http://localhost:8080/create-fourhour-event")
            .done(function (data) {
                $("#result5").append(data);
            });
    });

});
