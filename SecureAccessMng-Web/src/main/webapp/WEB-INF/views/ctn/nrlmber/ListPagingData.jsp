<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>

<tag:layout>
      	<div id="layout_content">
		<div class="content">

			<div class="border">
				<h1 class="tit01">검색조건</h1>
				<form name="FormSearchGnrlmber" action="/ctn/nrlmber/ListPagingData.do" method="get">
					<input type="hidden" name="page"/>
					<input type="hidden" name="excelResult"/>
					<table class="wtable_sub">
						<tr>
							<td>
								<div class="common_div left">처리일자</div>
								<input type="text" class="common_input2 pointer"  name="sDate" id="sDate" style="border-right:none;" autocomplete="off" placeholder="시작일" value="${paramMap.sDate}" readonly="readonly"/>
								<input type="text" class="common_input2 right pointer" name="eDate" id="eDate" autocomplete="off" placeholder="종료일" value="${paramMap.eDate}" readonly="readonly"/>


								<div class="common_div left margin_l2">소유자</div>
								<input type="text" class="common_input2 right" name="mberName" id="mberName" placeholder="소유자명" value="${paramMap.mberName}" autocomplete="off"/>

								<div class="common_div left margin_l2">담당</div>
								<select class="common_select" name="chargeId">
									<option value="" <c:if test="${Info.chargeId eq ''}">selected</c:if> >선택</option>
										<c:forEach var="charge" items="${ChargeList}" varStatus="status" >
											<option value="${charge.seq}" <c:if test="${Info.chargeId eq charge.seq}">selected</c:if> >${charge.name}</option>
										</c:forEach>
								</select>

								<div class="common_div left margin_l2">정/부</div>
								<select class="common_select" name="mberRating">
										<option value="" <c:if test="${Info.mberRating eq ''}">selected</c:if> >선택</option>
										<option value="M"  <c:if test="${Info.mberRating eq 'M'}">selected</c:if>  >정</option>
										<option value="D"  <c:if test="${Info.mberRating eq 'D'}">selected</c:if>  >부</option>
								</select>

								<div class="common_div left margin_l2">상태</div>
								<select class="common_select" name="mberSttus">
										<option value="" <c:if test="${Info.mberSttus eq ''}">selected</c:if> >선택</option>
										<option value="A"  <c:if test="${Info.mberRating eq 'A'}">selected</c:if>  >활성화</option>
										<option value="C"  <c:if test="${Info.mberRating eq 'C'}">selected</c:if>  >중지</option>
								</select>

								<button type="button" class="common_button2 margin_l2" onclick="fnProcSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							</td>
						</tr>
					</table>
						<div class="border margin_l6">
						    		<button type="button" class="btn_it02" onclick="fnOpenRegisterPage(0);">계정 등록</button>
						    		<button type="button" class="btn_it03" onclick="fnProcExcel();">엑셀 다운로드</button>
						</div>
					</form>
				</div>


			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
						<tr>
							<th scope="col" width="5%">No</th>
<!-- 							<th scope="col" width="8%">부서</th> -->
<!-- 							<th scope="col" width="8%">직위</th> -->
							<th scope="col" width="7%">처리일자</th>
							<th scope="col" width="7%">등록자</th>
							<th scope="col" width="7%">소유자</th>
							<th scope="col" width="7%">Email</th>
							<th scope="col" width="7%">전화번호</th>
							<th scope="col" width="7%">담당</th>
							<th scope="col" width="7%">권한</th>
							<th scope="col" width="7%">정/부</th>
							<th scope="col" width="7%">상태</th>
						</tr>
					</thead>
					<tbody style="cursor: pointer;">
						<c:forEach var="board" items="${Data.list}" varStatus="status" >
						<tr onclick ="fnOpenRegisterContentPage('${board.uniqId}')">
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
<%-- 							<td>${board.dpamentName}</td>			<!-- 부서 --> --%>
<%-- 							<td>${board.positionName}</td>			<!-- 직위 --> --%>
							<td>${board.frstRegisterPnttm}</td>			<!-- 처리일자 -->
							<td>${board.uniqIdDisplay}</td>				<!-- 등록자-->
							<td>${board.mberNameDisplay}</td>			<!-- 소유자 -->
							<td>${board.emailAddress}</td>				<!-- 계정-->
							<td>${board.moblphonNoDisplay}</td>			<!-- 전화번호-->
							<td>${board.name}</td>						<!-- 담당-->
							<td>${board.roleName}</td>					<!-- 권한-->
							<td>
																		<!-- 정/부 -->
								<c:choose>
									 <c:when test = "${board.mberRating eq 'M'}">정</c:when>
									 <c:otherwise>부</c:otherwise>
								</c:choose>
							</td>
							<td>										<!-- 상태 -->
								<c:choose>
									 <c:when test = "${board.mberSttus eq 'A'}">활성화</c:when>
									 <c:otherwise>해지</c:otherwise>
								</c:choose>
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
					<tag:Page formName="FormSearchGnrlmber" pageing="${Data.paginationInfo}"/>
				</div>
			</c:if>
		</div>
	</div>
 <jsp:include page="ListPagingDataS.jsp" flush="false" />
</tag:layout>