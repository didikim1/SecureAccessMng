<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<style>
table.htable td {
    font-size: 13px;
    padding: 3px;
    line-height: 27px;
    padding-left:7px;
    border-bottom: solid 1px #e6e6e6;
    color: #404040;
}

</style>
<p style="text-align: left; color: red; font-weight: bold;">10~16자리, 특수문자 포함, 연속되지않는 문자</p>
<form name="FormModifyPassword">
	<input type="hidden" name="uniqid" value="${paramMap.uniqid}"/>
	<div align="center" >
		<table class="htable">
			<!-- 현재 비밀번호 -->
			<tr>
				<th scope="col" width="148px">현재 비밀번호</th>
				<td><input type="password" style="width: 100%" class="password" id="id" name="password" autocomplete="off" value="3417" /></td>
			</tr>
			<!-- //현재 비밀번호 -->

			<!-- 새로운 비밀번호-->
			<tr>
				<th scope="col" width="148px">새로운 비밀번호</th>
				<td><input type="password" style="width: 100%"  class="newpassword" id="pwd" name="newpassword" autocomplete="off" value="2008hyo3!*" /></td>
			</tr>
			<!-- //새로운 비밀번호 -->
			
			<!-- 새로운 비밀번호 확인-->
			<tr>
				<th scope="col" width="148px">새로운 비밀번호 확인</th>
				<td><input type="password" style="width: 100%"  class="newpassword2" id="pwd" name="newpassword2" autocomplete="off" value="2008hyo3!*" /></td>
			</tr>
			<!-- //새로운 비밀번호 확인 -->
		</table>
	</div>
</form>