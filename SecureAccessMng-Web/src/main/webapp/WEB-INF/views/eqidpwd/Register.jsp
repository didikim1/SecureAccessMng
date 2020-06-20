<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<style>
.ui-jqgrid tr.jqgrow td {font-size:0.8em}

#gbox_grid { padding-top:2px; padding-bottom:0px; padding-left:0px; padding-right:0px; margin: 0px;}
</style>


<div id="layout_content_popup_sub" style="width:460px;">

<table id="grid"></table>
<div id="pager"></div>

<form name="FormEqList">
	<input type="hidden" name="refEqList" value="${infoMap.seq}" >
	<input type="hidden" name="seq" value="" >
	<div align="center" >
		<table class="htable">

			<!-- IDC 명칭 -->
			<tr>
				<th scope="col" width="120px">IDC</th>
				<td>${infoMap.idcName}</td>
			</tr>
			<!-- //IDC 명칭 -->

			<!-- 서버 IP -->
			<tr>
				<th scope="col" width="120px">서버 IP</th>
				<td>${infoMap.svIp1}</td>
			</tr>
			<!-- //서버 IP -->

			<!-- 장비접속ID -->
			<tr>
				<th scope="col" width="120px">계정명</th>
				<td><input type="text" class="userManageInput" id="id" name="id" autocomplete="off" value="" /></td>
			</tr>
			<!-- //장비접속ID -->

			<!-- 장비접속패스워드-->
			<tr>
				<th scope="col" width="120px">패스워드</th>
				<td><input type="password" class="userManageInput" id="pwd" name="pwd" autocomplete="off" value="" /></td>
			</tr>
			<!-- //장비접속패스워드 -->
		</table>
	</div>
</form>


<div class="border margin_l7">
	<button type="button" class="btn_it01" id="btnRegisterData" onclick="">추가</button>
	<button type="button" class="btn_it01" id="btnModifyData"   onclick="">수정</button>
	<button type="button" class="btn_it01" id="btnDeleteData"   onclick="">삭제</button>
	<button type="button" class="btn_it01" id="btnClose"   		onclick="">닫기</button>
</div>

</div>
<script type="text/javascript">
$(function(){

	$("#btnRegisterData").click(function(){
		var title = "["+$("[name=id]").val()+"][등록] 하시겠습니까?"

		$.fun.alert({
			content:title,
			action:function(){
						$.fun.ajax({
							type:'post',
							data:$("[name=FormEqList]").serialize(),
							dataType:"JSON",
							url:"/eqidpwd/RegisterData.do",
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
				} // action function
			}) // alert
	});

	$("#btnModifyData").click(function(){
		var title = "["+$("[name=id]").val()+"][수정] 하시겠습니까?"
		$.fun.alert({
			content:title,
			action:function(){
						$.fun.ajax({
							type:'post',
							data:$("[name=FormEqList]").serialize(),
							dataType:"JSON",
							url:"/eqidpwd/ModifyData.do",
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
			} // action function
		}) // alert

	});


	$("#btnDeleteData").click(function(){
		var title = "["+$("[name=id]").val()+"][삭제] 하시겠습니까?"

				$.fun.alert({
					content:title,
					action:function(){
								$.fun.ajax({
									type:'post',
									data:$("[name=FormEqList]").serialize(),
									dataType:"JSON",
									url:"/eqidpwd/DeleteData.do",
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
					} // action function
				}) // alert
	});



	$("#grid").jqGrid(jqGridUtils.fn_JQGridOption({
		datatype:'json',
		url:'/jqGrid/init',
		colNames: [
		           	 '고유ID'
		           	,'패스워드'
		           	,'아이디'
		           	,'최종등록자'
		           	,'최종등록일'
		           ],
		colModel:[
				 {name:'seq', 				index:'SEQ',				width:10,	align:'center', search:false,  sortable:true, hidden:true}
				,{name:'pwd', 				index:'PWD',				width:10,	align:'center', search:false,  sortable:true, hidden:true}
				,{name:'id', 				index:'ID',					width:10,	align:'center', search:false,  sortable:true}
				,{name:'lastUpdusrId', 		index:'LAST_UPDUSR_ID',		width:10,	align:'center', search:false,  sortable:true}
				,{name:'lastUpdusrPnttm', 	index:'LAST_UPDUSR_PNTTM',	width:10,	align:'center', search:false,  sortable:true}
		],
		pager:"#pager",
		rowNum:10,
		width:"300px",
		height:"150px",
		sortname:"LAST_UPDUSR_PNTTM",
   		sortorder:"desc",
   		onSelectRow:function(rowid, status, e){

   			var rowval = $('#grid').jqGrid('getRowData', rowid);
   			console.log(rowval)

   			$("[name=seq]").val(rowval.seq);
   			$("[name=id]").val(rowval.id);
   			$("[name=pwd]").val(rowval.pwd);
   		}
	}));


	setTimeout(function(){
		var paramMap = {};
		paramMap["refEqList"] 		= '${infoMap.seq}';

		jqGridUtils.searchProc({
		     gridId:"#grid"
			,postData:paramMap
		   ,url:"/eqidpwd/ListData.do"
		});
	}, 1000);


});
</script>