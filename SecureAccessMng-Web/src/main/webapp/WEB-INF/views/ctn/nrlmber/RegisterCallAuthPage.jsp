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
	</div>
</div>