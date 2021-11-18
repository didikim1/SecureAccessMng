<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>

<tag:layout>
      	<div id="layout_content">
		<div class="content">

			<div class="border">
				<h1 class="tit01">검색조건</h1>
				<form name="FormSearchGnrlmber" action="/gnrlmber/ListPagingData.do" method="get">
					<input type="hidden" name="excelResult"/>
					<table class="wtable_sub">
						<tr>
							<td>
								<div class="common_div left">부서</div>
								<select class="common_select" name="dpamentId">
									<option value="" <c:if test="${paramMap.dpamentId  != '' || paramMap.dpamentId  ne null}">selected</c:if> >선택</option>
									<c:forEach var="data" items="${ComtnDpamentList.list}" varStatus="status">
										<option value="${data.dpamentId}" <c:if test="${paramMap.dpamentId eq data.dpamentId}">selected</c:if> >${data.dpamentName}</option>
									</c:forEach>
								</select>

								<div class="common_div left margin_l2">직위</div>
								<select class="common_select" name="positionId">
									<option value="" <c:if test="${paramMap.positionId  != '' || paramMap.positionId  ne null}">selected</c:if> >선택</option>
									<c:forEach var="data" items="${ComtnPositionList.list}" varStatus="status">
										<option value="${data.positionId}" <c:if test="${paramMap.positionId eq data.positionId}">selected</c:if> >${data.positionName}</option>
									</c:forEach>
								</select>

								<div class="common_div left margin_l2">아이디</div>
								<input type="text" class="common_input2 right" name="uniqId" id="uniqId" placeholder="아이디" value="${paramMap.uniqId}" autocomplete="off"/>

								<div class="common_div left margin_l2">이름</div>
								<input type="text" class="common_input2 right" name="mberName" id="mberName" placeholder="이름" value="${paramMap.mberName}" autocomplete="off"/>

								<div class="common_div left margin_l2">휴대폰번호</div>
								<input type="text" class="common_input2 right" name="moblphonNo" id="moblphonNo" placeholder="휴대폰번호" value="${paramMap.moblphonNo}" autocomplete="off"/>

								<button type="button" class="common_button2 margin_l2" onclick="fnProcSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div class="border margin_l6">
				 <c:choose>
			         <c:when test = "${roleId eq 8}"> <!-- 권한관리자 -->
			    		<button type="button" class="btn_it02" onclick="fnOpenRegisterPage(0);">계정 생성</button>
			         </c:when>
			         <c:otherwise>
			         ${positionId}
		         	</c:otherwise>
		      </c:choose>

			</div>

			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
						<tr>
							<th scope="col" width="8%"></th>
<!-- 							<th scope="col" width="8%">부서</th> -->
<!-- 							<th scope="col" width="8%">직위</th> -->
							<th scope="col" width="7%">아이디</th>
							<th scope="col" width="7%">이름</th>
							<th scope="col" width="7%">휴대폰번호</th>
							<th scope="col" width="7%">이메일주소</th>
							<th scope="col" width="7%">상태</th>
							<th scope="col" width="7%">생성일</th>
							<th scope="col" width="7%">수정일</th>
							<th scope="col" width="13%">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${Data.list}" varStatus="status">
						<tr>
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
<%-- 							<td>${board.dpamentName}</td>		<!-- 부서 --> --%>
<%-- 							<td>${board.positionName}</td>		<!-- 직위 --> --%>
							<td>${board.uniqId}</td>			<!-- 아이디 -->
							<td>${board.mberName}</td>			<!-- 이름 -->
							<td>${board.moblphonNo}</td>		<!-- 휴대폰번호 -->
							<td>${board.emailAddress}</td>		<!-- 이메일주소 -->
							<td>
								<c:choose>
									<c:when test = "${board.mberSttus eq 'A'}"> <!-- 권한관리자 -->
										(정상)
									</c:when>
									<c:when test = "${board.mberSttus eq 'B'}"> <!-- 권한관리자 -->
										(잠김)
									</c:when>
									<c:when test = "${board.mberSttus eq 'C'}"> <!-- 권한관리자 -->
										(퇴사)
									</c:when>
									<c:otherwise>
										알수없음
									</c:otherwise>
								</c:choose>
								${board.positionName}
							</td>			<!-- 상태 -->
							<td>${board.frstRegisterPnttm}</td>		<!-- 생성일 -->
							<td>${board.lastUpdusrPnttm}</td>		<!-- 수정일 -->
							<td>
							<input type="button" class="btn_it01" onclick="fnOpenRegisterPage('${board.uniqId}');"  value="변경"/> <!-- 계정변경 -->
<%-- 							&nbsp;<input type="button" class="btn_it01" onclick="fnProcDelete('${board.mberId}', '${board.uniqId}', '${board.mberName}');"  value="삭제"/> <!-- 계정삭제 --> --%>
							</td>
						</tr>
					</c:forEach>
					</tbody>

				<c:if test="${Data.paginationInfo.totalRecordCount == 0 }">
					<tr>
						<td colspan="10">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
			</table>

			<c:if test="${Data.paginationInfo.totalRecordCount > 0 }">
				<div class="border">
					<tag:Page formName="recordSearchForm" pageing="${Data.paginationInfo}"/>
				</div>
			</c:if>
		</div>
	</div>
 <jsp:include page="ListPagingDataS.jsp" flush="false" />
</tag:layout>