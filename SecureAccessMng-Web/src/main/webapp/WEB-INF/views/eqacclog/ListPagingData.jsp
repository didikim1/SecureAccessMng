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
									<c:forEach var="data" items="${refEqList}" varStatus="status">
										<option value="${data.seq}" <c:if test="${paramMap.eQListSeq eq data.seq}"></c:if> >${data.eqListSvIp1}</option>
									</c:forEach>
								</select>
								
								<div class="common_div left margin_l2">업무</div>
								<!--  이런식으로 어떻게 paramMap으로 넘어오는지 함볼수있오 ㅎ -->
								<!-- ????????? 이미 한거아냐? 틀만 만들어뒀어욬ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ아니 무슨 수선가 너무 바뀐거아냐?ㅋㅋㅋㅋ -->
								<!-- 
								이렇게  
codeSeq : 고유키값
name   : 명칭

worktypecodeId이건 어디에서 가져왔을려나....음음.. 아 worktypecodeId 음...
								일단 실행해보자
								
								<select class="common_select" id="worktypecodeId"   > 아까 여기에 이렇게있었는데.
								
								실제 server랑 통신하기위해선 컨트롤러에 값이 보내기위해선  name값이 선언되어야함.
								
								id 는 javascript 에게 접근 or css에서 접근 그런용도로? 서버에 값을 보내긴 위해선 꼭 ㅜ믇
								name 이 선언되어야함..
name항모이 여러개면 배열러 서버에 전달이되고
								
							여기서 파람맵은서버에 전단될 값을 그대로 jsp에 return함. 
							왜냐하면 화면에서  검색바에 검색 항목을 유지하기 위해서 
							일단 검색 할때  worktypecodeId 로 워크타입을 넘기니깐 저게 sql에서도 적용되야겠지? 
							
							" <c:if test="${paramMap.worktypecodeId eq data.codeSeq}">selected</c:if> 
								
								 -->
								<select class="common_select" id="worktypecodeId"   name="worktypecodeId">
									<option value="" <c:if test="${paramMap.worktypecodeId  != '' || paramMap.worktypecodeId  ne null}">selected</c:if> >선택</option>
									<c:forEach var="data" items="${workInfoList}" varStatus="status">
										<option value="${data.codeSeq}" <c:if test="${paramMap.worktypecodeId eq data.codeSeq}">selected</c:if> >${data.name}</option>
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
						<th scope="col" width="2%"></th>
						<th scope="col" width="8%">IDC</th>
						<th scope="col" width="12%">서버</th>
						<th scope="col" width="5%">업무</th>
						<th scope="col" width="7%">접속ID</th>
						<th scope="col" width="7%">접속IP</th>
						<th scope="col" width="7%">접속자</th>
						<th scope="col" width="3%">정/부</th>
						<th scope="col" width="7%">프로세스ID</th>
						<th scope="col" width="5%">현재상태</th>
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
							<td><b>${board.worktypecodeName}</b></td>							<!-- 업무 -->
							<td>${board.eqIdpwdID}(${board.eqListSvIp1})</td>					<!-- 접속ID -->
							<td>${board.eqAllowIpName}(${board.eqAllowIpAdd})</td>				<!-- 접속IP -->
							<td>${board.ctnNrlmberName}(${board.ctnNrlmberUniqID})</td>			<!-- 접속자 -->
							<td>${board.mberRatingName}</td>											<!-- 담당책임 (정/부) -->
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