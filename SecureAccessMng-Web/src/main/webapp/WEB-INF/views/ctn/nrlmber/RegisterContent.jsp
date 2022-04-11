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
				<input type="hidden" name="seq" 		value="${Info.seq}" />
				<input type="hidden" name="nrlmberId" 	value="${Info.seq}" />
				<input type="hidden" name=mberSttus 	value="${Info.mberSttus}" />
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
							<td><input type="text" class="userManageInput" id="mberName" name="mberName" autocomplete="off" value="${Info.mberName}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">비밀번호</th>
							<td><input type="text" class="userManageInput" id="password" name="password" autocomplete="off" value="" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">전화번호</th>
							<td><input type="text" class="userManageInput" id="moblphonNo" name="moblphonNo" autocomplete="off" value="${Info.moblphonNo}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">담당</th>
							<td>
							
							<select class="common_select" name="chargeId"> 	
								<option value="" <c:if test="${Info.chargeId eq ''}">selected</c:if> >선택</option> 
									<c:forEach var="charge" items="${ChargeList}" varStatus="status">
											<option value="${charge.seq}" <c:if test="${Info.chargeId eq charge.seq}">selected</c:if> >${charge.name}</option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">권한</th>
							<td>
								<select class="common_select" name="roleId"> 	
									<option value="" <c:if test="${Info.roleId eq ''}">selected</c:if> >선택</option> 
									<c:forEach var="role" items="${RoleList}" varStatus="status">
											<option value="${role.roleId}" <c:if test="${Info.chargeId eq role.roleId}">selected</c:if> >${role.roleName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">정/부</th>
							<td>
							<select class="common_select" name="mberRating"> 
									<option value="" <c:if test="${Info.mberRating eq ''}">selected</c:if> >선택</option> 
									<option value="M">정</option> 
									<option value="D">부</option> 
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
					<button type="button" class="userManageButton" onclick="fnDeleteData()">해지</button>
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

function fnDeleteData(){
	$.fun.ajax({
		type:'post',
		data:$( "[name=mberSttus]" ).serialize(),
		url:"/ctn/nrlmber/DeleteData.do",
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
		           	,'권한'
		           	,'담당'
		           	,'계정'
		           	,'상태'
		           	,'정/부'
		           	,'소유자'
		           	,'휴대폰번호'
		           ],
		colModel:[
				 {name:'seq',				index:'SEQ',					width:10,	align:'center', search:false,  sortable:true, hidden:true}
				,{name:'roleId', 			index:'ROLE_ID',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'chargeId', 			index:'CHARGE_ID',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'uniqId', 			index:'UNIQ_ID',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'mberSttus', 		index:'MBER_STTUS',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'mberRating', 		index:'MBER_RATING',			width:10,	align:'center', search:false,  sortable:true}
				,{name:'mberName', 		 	index:'MBER_NAME',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'moblphonNo', 		index:'MOBLPHON_NO',			width:10,	align:'center', search:false,  sortable:true}
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
