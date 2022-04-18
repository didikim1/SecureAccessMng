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
				<form name="FormSearchEqList" action="/eqlist/ListPagingData.do" method="get">
					<input type="hidden" name="page"/>
					<input type="hidden" name="excelResult"/>
					<table class="wtable_sub">
						<tr>
							<td>

								<!-- 등록일 -->
								<div class="common_div left">등록일</div>
								<input type="text" class="common_input2 pointer"  name="sDate" id="sDate" style="border-right:none;" autocomplete="off" placeholder="시작일" value="${paramMap.sDate}" readonly="readonly"/>
								<input type="text" class="common_input2 right pointer" name="eDate" id="eDate" autocomplete="off" placeholder="종료일" value="${paramMap.eDate}" readonly="readonly"/>
								<!-- // 입고일 -->

								<!-- IDC -->
								<div class="common_div left margin_l2">IDC</div>
								<select class="common_select" name="refEqIdc">
									<option value="" <c:if test="${paramMap.idcId  != '' || paramMap.idcId  ne null}">selected</c:if> >선택</option>
									<c:forEach var="data" items="${IdcInfoList.list}" varStatus="status">
										<option value="${data.idcSeq}" <c:if test="${paramMap.refEqIdc eq data.idcSeq}">selected</c:if> >${data.idcName}</option>
									</c:forEach>
								</select>
								<!-- // IDC -->

								<!-- 자산명 -->
								<div class="common_div left margin_l2">IP</div>
								<input type="text" class="common_input2 right" name="svIp1" id="svIp1" placeholder="자산명" value="${paramMap.svIp1}" autocomplete="off"/>
								<!-- //자산명 -->

								<button type="button" class="common_button2 margin_l2" onclick="fnProcSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div class="border margin_l6">
				<button type="button" class="btn_it02" onclick="fnOpenRegisterPage('A');">IDC 등록</button>
				<button type="button" class="btn_it03" onclick="fnOpenRegisterPage('B');">계정 등록</button>
				<button type="button" class="btn_it02" onclick="fnOpenRegisterPage('C');">서버 등록</button>
			</div>

			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
						<tr>
							<th scope="col" width="8%"></th>
							<th scope="col" width="8%">IDC</th>
							<th scope="col" width="8%">서버</th>
							<th scope="col" width="7%">IP</th>
							<th scope="col" width="7%">Port(SSH)</th>
							<th scope="col" width="7%">등록일</th>
							<th scope="col" width="7%">계정등록</th>
							<th scope="col" width="7%">삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${Data.list}" varStatus="status">
						<tr>
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
							<td>${board.idcName}</td>				<!-- IDC -->
							<td>${board.name}</td>					<!-- 서버 -->
							<td>${board.svIp1}</td>					<!-- IP -->
							<td>${board.svPort}</td>				<!-- Port(SSH) -->
							<td>${board.frstRegisterPnttm}</td>		<!-- 등록일 -->
							<td><input type="button" class="btn_it01" value="등록" onclick="fnAccountRegister('${board.seq}')"/></td>
							<td><input type="button" class="btn_it01" value="삭제" onclick="fnDeleteData('${board.seq}', '${board.name}')"/></td>
						</tr>
					</c:forEach>
					</tbody>

				<c:if test="${Data.paginationInfo.totalRecordCount == 0 }">
					<tr>
						<td colspan="6">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
			</table>

			<c:if test="${Data.paginationInfo.totalRecordCount > 0 }">
				<div class="border">
					<tag:Page formName="FormSearchEqList" pageing="${Data.paginationInfo}"/>
				</div>
			</c:if>
		</div>
	</div>
 <jsp:include page="ListPagingDataS.jsp" flush="false" />
</tag:layout>