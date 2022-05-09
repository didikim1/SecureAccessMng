<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<form name="FormEqList">
	<div align="center" >
		<table class="htable">

			<!-- 장비정보 -->
			<tr>
				<th scope="col" width="120px" class = "MainInfo">장비정보</th>
				<td>
					<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterAccountPage('REF_EQ_LIST', 'refEqList')" id="refEqListName" name="refEqListName" autocomplete="off" value="${Info.refEqListName}" readonly="readonly" />
					<input type="hidden" id="refEqList" name="refEqList" autocomplete="off" value="${Info.refEqListName}" readonly="readonly" />
				</td>
			</tr>
			<!-- //장비정보 -->
			
			<!-- 정/부 -->
			<tr>
				<th scope="col" width="120px"class = "MainInfo">정부</th>
				<td><input type="text" class="userManageInput" id="mberRating" name="mberRating" autocomplete="off" value="${paramMap.mberRating}" /></td>
			</tr>
			<!-- 정/부 -->
			
			<!-- 장비접속ID -->
			<tr>
				<th scope="col" width="120px" class = "MainInfo">계정명</th>
				<td><input type="text" class="userManageInput" id="id" name="id" autocomplete="off" value="${paramMap.id}" /></td>
			</tr>
			<!-- //장비접속ID -->

			<!-- 장비접속패스워드-->
			<tr>
				<th scope="col" width="120px" class = "MainInfo">패스워드</th>
				<td><input type="text" class="userManageInput" id="pwd" name="pwd" autocomplete="off" value="${paramMap.pwd}" /></td>
			</tr>
			<!-- //장비접속패스워드 -->

		</table>
	</div>
</form>