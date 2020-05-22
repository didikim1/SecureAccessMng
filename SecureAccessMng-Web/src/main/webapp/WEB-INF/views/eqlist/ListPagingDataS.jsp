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
// 자산등록페이지
function fnOpenRegisterPage(registType)
{
	// A:자산등록, B:계정등록
	if(registType == "A")
	{
		fnEquipRegister(registType);
	}
	else if(registType == "B")
	{
		fnAccountRegister(registType);
	}
}

// 자산등록
function fnEquipRegister(registType)
{
	$.fun.ajax({
		type:'get',
		url:"/eqlist/Register.do?registType="+registType,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"자산 등록",
				"width":475,
				"buttons":{}
			});
		}
	});
}

// 계정등록
function fnAccountRegister(registType)
{
	$.fun.ajax({
		type:'get',
		url:"/eqlist/Register.do?registType="+registType,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"계정 등록",
				"width":475,
				"buttons":{}
			});
		}
	});
}

function fnProcDelete(mberId, uniqId, mberName){
	var _mberId		= mberId;
	var _uniqId		= uniqId;
	var _mberName	= mberName;
	var _txt		= _mberName+"( " + _uniqId + " ) 의 계정을 삭제 하시겠습니까? "
	$.fun.alert({
		content:_txt,
		"buttons":{
			"확인": function() {
				$(this).dialog('destroy').remove();
				$.fun.ajax({
					type:'get',
					dataType:"JSON",
					url:"./ProcDeleteData.do?mberId="+_mberId,
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


</script>