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
				<form name="FormSearchEqAcclog" action="/eqacclog/ListPagingData.do" method="get">
					<input type="hidden" name="page"/>
					<table class="wtable_sub">
						<tr>
							<td>
								<div class="common_div left">접속일자</div>
								<input type="text" class="common_input2 pointer"  name="sDate" id="sDate" style="border-right:none;" autocomplete="off" placeholder="시작일" value="${paramMap.sDate}" readonly="readonly"/>
								<input type="text" class="common_input2 right pointer" name="eDate" id="eDate" autocomplete="off" placeholder="종료일" value="${paramMap.eDate}" readonly="readonly"/>


								<div class="common_div left margin_l2">IDC</div>
								<select class="common_select" name="idcSeq" style="width: 160px;">
									<option value="" <c:if test="${paramMap.idcSeq  != '' || paramMap.idcSeq  ne null}">selected</c:if> >선택</option>
									<c:forEach var="data" items="${IdcInfoList}" varStatus="status">
										<option value="${data.idcSeq}" <c:if test="${paramMap.idcSeq eq data.idcSeq}">selected</c:if> >${data.idcName}</option>
									</c:forEach>
								</select>

								<div class="common_div left margin_l2">서버</div>
								<select class="common_select" id="eQListSeq" name="eQListSeq" style="width: 170px;">
									<option value="" <c:if test="${paramMap.eQListSeq  != '' || paramMap.eQListSeq  ne null}">selected</c:if> >선택</option>
									<c:forEach var="data" items="${EqListInfoList}" varStatus="status">
										<option value="${data.seq}" <c:if test="${paramMap.eQListSeq eq data.seq}">selected</c:if> >${data.name}</option>
									</c:forEach>
								</select>


								<button type="button" class="common_button2 margin_l2" onclick="fnProcSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
								<button type="button"  style="width: 120px;" class="common_button2 margin_l2" onclick="fnProcExcel();">&nbsp;엑셀다운로드</button>

							</td>

						</tr>
					</table>
				</form>
			</div>

			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
						<tr>
							<th scope="col" width="8%"></th>
							<th scope="col" width="8%">IDC</th>
							<th scope="col" width="8%">서버</th>
							<th scope="col" width="8%">업무</th>
							<th scope="col" width="7%">접속ID</th>
							<th scope="col" width="7%">접속IP</th>
							<th scope="col" width="7%">접속자</th>
							<th scope="col" width="7%">프로세스ID</th>
							<th scope="col" width="7%">현재상태</th>
							<th scope="col" width="7%">로그인일자</th>
							<th scope="col" width="7%">로그아웃일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${Data.list}" varStatus="status">
						<tr onclick="fnPopSelectOneData('${board.seq}')"  style="cursor: pointer;">
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
							<td>${board.eqIdcName}</td>											<!-- IDC -->
							<td>${board.eqListName}</td>										<!-- 서버 -->
							<td>${board.worktypecodeName}</td>									<!-- 업무 -->
							<td>${board.eqIdpwdID}(${board.eqListSvIp1})</td>										<!-- 접속ID -->
							<td>${board.eqAllowIpName}(${board.eqAllowIpAdd})</td>				<!-- 접속IP -->
							<td>${board.ctnNrlmberName}(${board.ctnNrlmberUniqID})</td>			<!-- 접속자 -->
							<td>${board.processid}</td>											<!-- 프로세스ID -->
							<td>																<!-- 현재상태 -->
							<c:choose>
								<c:when test="${board.sttus == 'A'}"><font style="color: green;font-weight: bold;">로그인</font></c:when>
								<c:otherwise>로그아웃</c:otherwise>
							</c:choose>
							</td>
							<td>${board.frstRegisterPnttm}</td>									<!-- 로그인일자 -->
							<td>${board.lastUpdusrPnttm}</td>									<!-- 로그아웃일자 -->
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
					<tag:Page formName="FormSearchEqAcclog" pageing="${Data.paginationInfo}"/>
				</div>
			</c:if>
		</div>
	</div>
 <jsp:include page="ListPagingDataS.jsp" flush="false" />
</tag:layout>