<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">
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
							<th scope="col" width="120px">처리자</th>
							<td><input type="text" class="userManageInput" id="uniqId" name="uniqId" autocomplete="off" value="${Info.uniqId}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">소유자</th>
							<td><input type="text" class="userManageInput" id="mberName" name="mberName" autocomplete="off" value="${Info.mberName}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">비밀번호</th>
							<td><input type="password" class="userManageInput" id="password" name="password" autocomplete="off"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">전화번호</th>
							<td><input type="text" class="userManageInput" id="moblphonNo" name="moblphonNo" autocomplete="off" value="${Info.moblphonNo}"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">계정(ID)</th>
							<td><input type="text" class="userManageInput" id="emailAddress" name="emailAddress" autocomplete="off" value="${Info.emailAddress}"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">담당</th>
							<td>
							<select class="common_select" name="chargeId"> 
								<option value="" <c:if test="${Info.chargeId eq ''}">selected</c:if> >선택</option> 
								<option value="1">책임자</option> 
								<option value="2">관리자</option> 
								<option value="3">운영팀</option> 
								<option value="4">정산팀</option> 
								<option value="5">CS팀</option> 
							</select> 
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">권한</th>
							<td>
							 <select class="common_select" name="roleId">
									<option value="${data.role}" <c:if test="${Info.role eq data.role}">selected</c:if> >선택</option> 
										<option value="1" >읽기</option> 
										<option value="2">읽기/쓰기</option> 
										<option value="3">읽기/쓰기/수정</option> 
										<option value="4">읽기/쓰기/수정/삭제</option> 
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
						<tr>
							<th scope="col" width="120px">상태</th>
							<td>
							<select class="common_select" name="mberSttus"> 
									<option value="" <c:if test="${Info.mberSttus eq ''}">selected</c:if> >선택</option> 
									<option value="A" >활성화</option> 
									<option value="C" >중지</option> 
							</select>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>

	<%-- 	<div class="border margin_l7">
			<c:choose>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">수정하기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="userManageButton" onclick="fnClose()">닫기</button> 
		</div>
	--%>
		<div class="border margin_l7">
			<c:choose>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록</button>
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


</script>
