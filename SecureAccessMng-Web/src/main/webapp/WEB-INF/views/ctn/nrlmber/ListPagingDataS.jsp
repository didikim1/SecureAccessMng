<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">



$(function(){


	$("table thead th:eq(0)").data("sorter", false);
	$("table thead th:eq(1)").data("sorter", false);
	$("table thead th:eq(2)").data("sorter", false);
	$("table thead th:eq(3)").data("sorter", false);
	$("table thead th:eq(4)").data("sorter", false);
	$("table thead th:eq(5)").data("sorter", false);
	$("table thead th:eq(6)").data("sorter", false);
	$("table thead th:eq(7)").data("sorter", false);
	$("table thead th:eq(8)").data("sorter", false);
	$("table thead th:eq(9)").data("sorter", false);
	$("table thead th:eq(10)").data("sorter", false);

	$("#myTable").tablesorter({
		usNumberFormat : false,
		sortReset      : true,
		sortRestart    : true
	});

});


function fnProcSearch(){
	$("[name=FormSearchGnrlmber]").submit();
}

function fnOpenRegisterPage(uniqId){
	// 엑셀은 ajax가 아니라 따로 파일 스트림을 응답받음
	$.fun.ajax({
		type:'get',
		url:"/ctn/nrlmber/RegisterData.do?uniqId="+uniqId,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"계정등록",
				"width":475,
				"buttons":{}
			});
		}
	});
}

function serializeObject(form){
	 var o = {};
    var a = form.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o
}

function fnOpenRegisterContentPage(uniqId){
	$.fun.ajax({
		type:'get',
		url:"/ctn/nrlmber/RegisterContent.do?uniqId="+uniqId,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"계정 상세내역",
				"width":475,
				"buttons":{}
			});
		}
	});
}

function fnProcExcel(){
	var data   = null;

	var data   = serializeObject($("[name=FormSearchEqAcclog]"));
	var method = "get";
	var inputs = '';

	for (var k in data) {
		inputs+='<input type="hidden" name="'+ k +'" value="'+ data[k]+'" />';
    }
	$('<form action="'+ "/eqacclog/ListExcelData" +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
	
}


function fnProcDelete(mberId, uniqId, mberName){
	var _mberId		= mberId;
	var _uniqId		= uniqId;
	var _mberName	= mberName;
	var _txt		= _mberName+"( " + _uniqId + " ) 의 계정의 상태를 변경 하시겠습니까? "
	$.fun.alert({
		content:_txt,
		"buttons":{
			"확인": function() {
				$(this).dialog('destroy').remove();
				$.fun.ajax({
					type:'get',
					dataType:"JSON",
					url:"./DeleteData.do?mberId="+_mberId,
					success:function(data){
						if( "200" == data.code ) {
							$.fun.alert({content:"정상 처리되었습니다.", action:function(){
								location.reload();
							}});
						} else {
							$.fun.alert({content:"Error!!!!!", action:function(){
								location.reload();
							}});
						}
					}
				});
			},
			"닫기": function() {
				$(this).dialog('destroy').remove();
			}
		}
	});
}

$(document).ready(function(){
	$("[name=sDate]").datepicker({
		 dayNamesMin : ['일', '월', '화', '수', '목', '금', '토']
		,monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
		,dateFormat : "yy-mm-dd"
	});
	$("[name=eDate]").datepicker({
		 dayNamesMin : ['일', '월', '화', '수', '목', '금', '토']
		,monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
		,dateFormat : "yy-mm-dd"
	});
});


</script>