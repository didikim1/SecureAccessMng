<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<form name="FormEqList">
	<div align="center" >
		<table class="htable">

			<!-- IDC -->
			<tr>
				<th scope="col" width="120px">IDC</th>
				<td>${info.eqIdcName}</td>
			</tr>
			<!-- //IDC -->

			<!-- 서버ID -->
			<tr>
				<th scope="col" width="120px">서버</th>
				<td>${info.eqListName}</td>
			</tr>
			<!-- //서버 -->

			<!-- 접속ID-->
			<tr>
				<th scope="col" width="120px">접속 ID</th>
				<td>${info.eqIdpwdID}(${info.eqListSvIp1})</td>
			</tr>
			<!-- //접속ID -->
			
			<!-- 접속IP-->
			<tr>
				<th scope="col" width="120px">접속 IP</th>
				<td>${info.eqAllowIpName}(${info.eqAllowIpAdd})</td>
			</tr>
			<!-- //접속IP -->
			
			<!-- 접속자-->
			<tr>
				<th scope="col" width="120px">접속자</th>
				<td>${info.ctnNrlmberName}(${info.ctnNrlmberUniqID})</td>
			</tr>
			<!-- //접속자 -->
			
			<!-- 접속자-->
			<tr>
				<th scope="col" width="120px">정/부</th>
				<td>${info.mberRatingName}</td>
			</tr>
			<!-- //접속자 -->
			
			<!-- 프로세스ID-->
			<tr>
				<th scope="col" width="120px">프로세스ID</th>
				<td>${info.processid}</td>
			</tr>
			<!-- //프로세스ID -->
			
			<!-- 현재상태-->
			<tr>
				<th scope="col" width="120px">현재상태</th>
				<td>
				<c:choose>
					<c:when test="${info.sttus == 'A'}"><font style="color: green;font-weight: bold;">로그인</font></c:when>
					<c:otherwise>로그아웃</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<!-- //프로세스ID -->
			
			<!-- 로그인일자-->
			<tr>
				<th scope="col" width="120px">로그인일자</th>
				<td>${info.frstRegisterPnttm}</td>
			</tr>
			<!-- //로그인일자 -->
			
			<!-- 로그아웃일자-->
			<tr>
				<th scope="col" width="120px">로그아웃일자</th>
				<td>${info.lastUpdusrPnttm}</td>
			</tr>
			<!-- //로그아웃일자 -->
			
			<!-- 작업구분-->
			<tr>
				<th scope="col" width="120px">작업구분</th>
				<td>${info.worktypecodeName}</td>
			</tr>
			<!-- //작업구분 -->
			
			<!-- 사유-->
			<tr>
				<th scope="col" width="120px">사유</th>
				<td>${info.reason}</td>
			</tr>
			<!-- //작업구분 -->
		</table>
	</div>
</form>