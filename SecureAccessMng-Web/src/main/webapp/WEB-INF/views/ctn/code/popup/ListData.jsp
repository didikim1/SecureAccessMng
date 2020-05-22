<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>


<style>
.ui-jqgrid tr.jqgrow td {font-size:0.8em}

#gbox_grid { padding-top:2px; padding-bottom:0px; padding-left:0px; padding-right:0px; margin: 0px;}
</style>

<div id="layout_content_popup_sub" style="width:579px;">
	<div class="content">

		<div class="border_sub">
			<form action="" name="RegisterForm">
<%-- 			<input type="hidden" name="pid" value="${parentMap.pid}"/> --%>
			<input type="hidden" name="refCode" value="${parentMap.refCode}"/>	<!-- 상위CODEID -->
			<input type="hidden" name="title" 	value="${parentMap.title}"/>	<!-- 분류코드명 -->
			<input type="hidden" name="type" 	value="${paramMap.type}"/>		<!-- 부모(A),자식(B) -->
			<table class="htable">
				<tr>
					<th scope="col">명칭</th>
					<td>
						<input type="text" class="userManageInput" style="width:200px" id="" name="name" autocomplete="off" value="" />
						<button type="button" style="height: 30px;" class="" onclick="fnCodeRegister()">등록</button>
					</td>
				</tr>
			</table>
			</form>
		</div>

		<table id="grid"></table>
		<div id="pager"></div>


		<div class="border margin_l7">
			<button type="button" class="userManageButton" onclick="fnPageEquipmentClose()">닫기</button>
		</div>

	</div>
</div>
<script type="text/javascript">

function fnPageEquipmentClose(){
	$("#RegisterSvModelPageEquipment").dialog('destroy').remove();
}

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function fn_loadGrid(gridId, searchDataUrl, searchData){
	jqGridUtils.searchProc({
	    gridId:gridId
		,postData:searchDataUrl
	   ,url:searchDataUrl
	});
}

function fnParentPageSetup(cellvalue){
	console.log(cellvalue)
	var _id ="${paramMap.inputName}";
	var registType = "${paramMap.registType}";

	if(registType == "A")
	{
		$.fun.ajax({
			type:'get',
			datatype:'json',
			url:"/ctn/code/SelectOneData.do?codeSeq="+cellvalue,
			success:function(data){
				var obj = JSON.parse(data);
				console.log(obj);

				$("#"+_id).val( obj.result.codeSeq);
				$("#"+_id+"Name").val( obj.result.name);
				$("#RegisterSvModelPageEquipment").dialog('destroy').remove();
			}
		});
	}
	else if(registType == "B")
	{
		$.fun.ajax({
			type:'get',
			datatype:'json',
			url:"/eqlist/SelectOneData.do?seq="+cellvalue,
			success:function(data){
				var obj = JSON.parse(data);
				console.log(obj);

				$("#"+_id).val( obj.result.seq);
				$("#"+_id+"Name").val( obj.result.name);
				$("#RegisterSvModelPageEquipment").dialog('destroy').remove();
			}
		});
	}

}


function fnCodeRegister(){

	$.fun.ajax({
		type:'post',
		data:$("form[name=RegisterForm]").serialize(),
		url:"/ctn/code/RegisterData.do",
		success:function(data){
			var obj = JSON.parse(data);
			
			$("[name=name]").val("");
			
			setTimeout(function(){
				var ptitle  = "${paramMap.ptitle}";
				var type    = "${paramMap.type}";
				var url 	= "/ctn/code/ListData.do";

				var paramMap = {};
				paramMap["page"] 	= 0;
				paramMap["ptitle"] 	= ptitle;
				paramMap["type"] 	= type;

				jqGridUtils.searchProc({
				     gridId:"#grid"
					,postData:paramMap
					,url:url
				});

			}, 500);
		}
	});
}

function selButton (cellvalue, options, rowObject) {
   	return '<input type="button" onclick="fnParentPageSetup('+cellvalue+')" value="선택"/>';
 };


$(document).ready(function() {

	var registType = "${paramMap.registType}";
	if(registType == "A")
	{
		$("#grid").jqGrid(jqGridUtils.fn_JQGridOption({
			datatype:'json',
			url:'/jqGrid/init',
			colNames: [
			           	 '명칭'
			           	,'최종등록ID'
			           	,''
			           ],
			colModel:[
					 {name:'name', 				index:'NAME',				width:35,	align:'center', search:false,  sortable:true}
					,{name:'lastUpdusrId', 		index:'LAST_UPDUSR_ID',		width:10,	align:'center', search:false,  sortable:true}
					,{name:'codeSeq', 			index:'CODE_SEQ',			width:10,	align:'center', search:false,  sortable:false, formatter: selButton}
			],
			pager:"#pager",
			rowNum:10,
			width:"579px",
			height:"400px",
			sortname:"lastUpdusrPnttm",
	   		sortorder:"desc"
		}));
	}
	else if(registType == "B")
	{
		$("#grid").jqGrid(jqGridUtils.fn_JQGridOption({
			datatype:'json',
			url:'/jqGrid/init',
			colNames: [
			           	 '명칭'
			           	,'최종등록ID'
			           	,''
			           ],
			colModel:[
					 {name:'name', 				index:'NAME',				width:35,	align:'center', search:false,  sortable:true}
					,{name:'lastUpdusrId', 		index:'LAST_UPDUSR_ID',		width:10,	align:'center', search:false,  sortable:true}
					,{name:'seq', 				index:'SEQ',				width:10,	align:'center', search:false,  sortable:false, formatter: selButton}
			],
			pager:"#pager",
			rowNum:10,
			width:"579px",
			height:"400px",
			sortname:"lastUpdusrPnttm",
	   		sortorder:"desc"
		}));
	}
	

	setTimeout(function(){
		var title  		= "${paramMap.title}";
		var registType  = "${paramMap.registType}";
		var type    	= "${paramMap.type}";
		var url 		= "/comtn/code/ListData.do";

		var paramMap = {};
		paramMap["page"] 		= 0;
		paramMap["title"] 		= title;
		paramMap["registType"] 	= registType;
		paramMap["type"] 		= type;

		jqGridUtils.searchProc({
		     gridId:"#grid"
			,postData:paramMap
		   ,url:"/ctn/code/ListData.do"
		});

	}, 1000);


});

</script>
