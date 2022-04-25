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
	$("[name=FormSearchEqList]").submit();
}
// 자산등록페이지
function fnOpenRegisterPage(registType)
{
	// A:서버등록, B:계정등록
	if(registType == "A")
	{
		fnEquipRegister(registType);
	}
	else if(registType == "B")
	{
		fnAccountRegister(registType);
	}
	// IDC등록
	else if(registType == "C")
	{
		fnIdcRegister(registType);
	}
}

// 서버등록
function fnEquipRegister(registType)
{
	$.fun.ajax({
		type:'get',
		url:"/eqlist/Register.do?registType="+registType,
		success:function(data){
			console.log(data);
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"서버등록",
				"width":475,
				"buttons":{}
			});
		}
	});
}

 /* $(document).ready(function () {
	  $(document).on("click", "button[name='ServerDelete']", function () {
		  fnDeleteData
	  });
	}); */


// 자산 삭제
function fnDeleteData(seq, name){

/* $.fun.ajax({
	id:"induacaAdd",
	type:'get',
	dataType:"JSON",
	url:"/eqlist/DeleteData.do?seq="+seq,
	success:function(data){
		console.log(data);
		if( "200" == data.code ) {
			$.fun.alert({content:"정상 처리되었습니다.", action:function(){
				location.reload();
			}});
		}else {
			$.fun.alert({content:"Error!!!!!", action:function(){
				location.reload();
			}});
		}
	}
}); */
	var title = "["+name+"] 서버를 삭제 하시겠습니까?"
		
		
 	$.fun.layout({
		id:"induacaAdd",
		"content":title,
		"title":"서버 삭제",
		"width":400,
		"buttons":{
			"확인": function() {
				$.fun.ajax({
					type:'get',
					dataType:"JSON",
					url:"/eqlist/DeleteData.do?seq="+seq,
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
				});// ajax
			}, 
			"닫기": function() {
				$(this).dialog('destroy').remove();
			}
		} //button
	}); 
} 
	
	
// 계정등록
function fnAccountRegister(eqlistSeq){
	$.fun.ajax({
		type:'get',
		url:"/eqidpwd/Register.do?seq="+eqlistSeq,
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


/* function fnProcDelete(mberId, uniqId, mberName){
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
} */




//IDC등록
function fnIdcRegister(registType)
{
	$.fun.ajax({
		type:'get',
		url:"/eqlist/Register.do?registType="+registType,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"IDC등록",
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


function fnProcExcel(){

	var data   = null;

	var data   = serializeObject($("[name=FormSearchEqList]"));
	var method = "get";
	var inputs = '';

	for (var k in data) {   
		inputs+='<input type="hidden" name="'+ k +'" value="'+ data[k]+'" />';
    }
	$('<form action="'+ "/eqlist/ListExcelData.do" +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();

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