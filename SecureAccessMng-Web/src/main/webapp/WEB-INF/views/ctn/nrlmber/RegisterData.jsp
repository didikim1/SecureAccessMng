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
							<th scope="col" width="120px">상태</th>
							<td>
								<select class="userManageInput" name="mberSttus">
									<option value="A" <c:if test="${Info.mberSttus eq 'A'}">selected</c:if> >활성화</option>
									<option value="B" <c:if test="${Info.mberSttus eq 'B'}">selected</c:if> >잠김</option>
									<option value="C" <c:if test="${Info.mberSttus eq 'C'}">selected</c:if> >퇴사</option>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">아이디</th>
							<td><input type="text" class="userManageInput" id="uniqId" name="uniqId" autocomplete="off" value="${Info.uniqId}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">패스워드</th>
							<td><input type="password" class="userManageInput" id="password" name="password" autocomplete="off"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">패스워드확인</th>
							<td><input type="password" class="userManageInput" id="password_re" name="password_re" autocomplete="off"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">이름</th>
							<td><input type="text" class="userManageInput" id="mberName" name="mberName" autocomplete="off" value="${Info.mberName}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">휴대폰번호</th>
							<td><input type="text" class="userManageInput" id="moblphonNo" name="moblphonNo" autocomplete="off" value="${Info.moblphonNo}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">이메일주소</th>
							<td><input type="text" class="userManageInput" id="emailAddress" name="emailAddress" autocomplete="off" value="${Info.emailAddress}" /></td>
						</tr>
					</table>
				</div>
			</form>
		</div>

		<div class="border margin_l7">
			<c:choose>
				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">수정하기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록하기</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="userManageButton" onclick="fnClose()">닫기</button>
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
