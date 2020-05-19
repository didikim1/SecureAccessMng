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

								<!-- 입고일 -->
								<div class="common_div left">입고일</div>
								<input type="text" class="common_input2 pointer"  name="sDate" id="sDate" style="border-right:none;" autocomplete="off" placeholder="시작일" value="${paramMap.sDate}" readonly="readonly"/>
								<input type="text" class="common_input2 right pointer" name="eDate" id="eDate" autocomplete="off" placeholder="종료일" value="${paramMap.eDate}" readonly="readonly"/>
								<!-- // 입고일 -->

								<!-- IDC -->
								<div class="common_div left margin_l2">IDC</div>
								<select class="common_select" name="idcId">
									<option value="" <c:if test="${paramMap.idcId  != '' || paramMap.idcId  ne null}">selected</c:if> >선택</option>
									<c:forEach var="data" items="${IdcInfoList.list}" varStatus="status">
										<option value="${data.idcId}" <c:if test="${paramMap.idcId eq data.idcId}">selected</c:if> >${data.idcName}</option>
									</c:forEach>
								</select>
								<!-- // IDC -->

								<!-- 자산명 -->
								<div class="common_div left margin_l2">자산명</div>
								<input type="text" class="common_input2 right" name="uniqId" id="propertyName" placeholder="자산명" value="${paramMap.propertyName}" autocomplete="off"/>
								<!-- //자산명 -->

								<button type="button" class="common_button2 margin_l2" onclick="fnProcSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div class="border margin_l6">
				<button type="button" class="btn_it02" onclick="fnOpenRegisterPage(0);">코드 등록</button>
			</div>

			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
						<tr>
							<th scope="col" width="8%"></th>
							<th scope="col" width="8%">코드접두사</th>
							<th scope="col" width="8%">분류코드명</th>
							<th scope="col" width="7%">코드타입</th>
							<th scope="col" width="7%">코드ID</th>
							<th scope="col" width="7%">설명</th>
							<th scope="col" width="7%">이름</th>
							<th scope="col" width="7%">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${Data.list}" varStatus="status">
						<tr>
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
							<td>${board.prefix}</td>					<!-- 코드접두사 -->
							<td>${board.title}</td>						<!-- 분류코드명 -->
							<td>${board.type}</td>						<!-- 코드타입 -->
							<td>${board.id}</td>						<!-- 코드ID -->
							<td>${board.remark}</td>					<!-- 설명 -->
							<td>${board.name}</td>						<!-- 이름 -->
							<td><input type="button" value="목록" /></td>						<!-- 이름 -->
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