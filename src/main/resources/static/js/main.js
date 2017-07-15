/**
 * Created by NAVER on 2017-07-07.
 */

var previewForm = document.getElementById('form-print');

previewForm.onsubmit = function() {
    var w = window.open('/preview','Popup_Window','resizable=1,width=526,height=715');
    this.target = 'Popup_Window';
};

$(document).ready(function() {


    // 1. class : _month_cell click 시 index로 넘어가서 입력하게 함
    $('td').click(function(){

        window.location.href = "http://localhost:8080/index";

    })



    // 2. 입력된 이벤트 summary를 해당 셀에 추가해줌

    // 3. 인쇄버튼 누르면 프리뷰 이미지로 렌더링 해줌

});




