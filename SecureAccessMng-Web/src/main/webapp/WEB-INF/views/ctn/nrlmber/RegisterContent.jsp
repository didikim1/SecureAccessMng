<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">
		
		<table id="grid"></table>
		<div id="pager"></div>
		
			<form name="FormComtngnrlmber">
				<input type="hidden" name="seq" value="${Info.seq}" />
				<div align="center" >
					<table class="htable">
<!-- 						<tr> -->
<!-- 							<th scope="col" width="120px">부서</th> -->
<!-- 							<td> -->
<!-- 								<select class="userManageInput" name="dpamentId"> -->
<%-- 									<c:forEach var="data" items="${ComtnDpamentList.list}" varStatus="status"> --%>
<%-- 										<option value="${data.dpamentId}" <c:if test="${Info.dpamentId eq data.dpamentId}">selected</c:if> >${data.dpamentName}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<th scope="col" width="120px">직위</th> -->
<!-- 							<td> -->
<!-- 								<select class="userManageInput" name=positionId> -->
<%-- 									<c:forEach var="data" items="${ComtnPositionList.list}" varStatus="status"> --%>
<%-- 										<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}">selected</c:if> >${data.positionName}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<th scope="col" width="120px">소유자</th>
							<td><input type="text" class="userManageInput" id="uniqId" name="uniqId" autocomplete="off" value="${Info.uniqId}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">비밀번호</th>
							<td><input type="text" class="userManageInput" id="uniqId" name="uniqId" autocomplete="off" value="${Info.uniqId}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">전화번호</th>
							<td><input type="text" class="userManageInput" id="uniqId" name="uniqId" autocomplete="off" value="${Info.uniqId}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">담당</th>
							<td>
							<select class="common_select" name=positionId> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}">selected</c:if> >선택</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >책임자</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >관리자</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >운영자</option> 
							</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">권한</th>
							<td>
							 <select>
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}">selected</c:if> >선택</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >읽기</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >읽기/쓰기</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >읽기/쓰기/선택</option> 
							</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">정/부</th>
							<td>
							<select class="common_select" name=positionId> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}">selected</c:if> >선택</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >정</option> 
									<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}"></c:if> >부</option> 
							</select>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>

		<div class="border margin_l7">
			<c:choose>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">수정</button>
				</c:when>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">해지</button>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">
function fnProcRegisterData(){
	$.fun.ajax({
		type:'post',
		data:$( "[name=FormComtngnrlmber]" ).serialize(),
		url:"/ctn/nrlmber/ProcRegisterData.do",
		dataType:"JSON",
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
	});
	

}

function fnClose(){
	$("#induacaAdd").dialog('destroy').remove();
}

$(document).ready(function(){
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
});


</script>
