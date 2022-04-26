<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<form name="formIdcinfo">
	<div align="center" >
		<table class="htable">

			<!-- 명칭 -->
 			<tr>
				<th scope="col" width="120px" class = "MainInfo">명칭</th>
				<td><input type="text" class="userManageInput" id="idcName" name="idcName" autocomplete="off" value="${Info.idcName}" /></td>
			</tr>
			<!--명칭-->


			<!-- 주소-->
			<tr>
				<th scope="col" width="120px" class = "MainInfo">주소</th>
				<td><input type="text" class="userManageInput" id="idcAddr" name="idcAddr" autocomplete="off" value="${Info.idcAddr}" /></td>
			</tr>
			<!-- 주소 -->

		</table>
	</div>
</form>