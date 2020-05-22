<%@ tag language="java" pageEncoding="UTF-8"%><%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ attribute name="activePageIndex" required="false"%>
<%@ attribute name="bodyClass" required="false"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>
<c:set var="Ver"  value="20200522_003" scope="application"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<link rel="stylesheet" type="text/css" href="${_resource}/css/common/style.css?ver=${Ver}" />
<link rel="stylesheet" type="text/css" href="${_resource}/css/common/record.css?ver=${Ver}" />

<!-- 헤더 메뉴 -->
<link rel="stylesheet" type="text/css" href="${_resource}/js/common/menu/stuHover.css?ver=${Ver}" />
<link rel="stylesheet" type="text/css" href="${_resource}/lib/smoothness/jquery-ui-1.10.3.custom.css?ver=${Ver}" />
<link rel="stylesheet" type="text/css" href="${_resource}/lib/smoothness/jquery.custom.css?ver=${Ver}" />

<script type="text/javascript" src="${_resource}/js/common/jquery/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${_resource}/js/common/jquery/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${_resource}/js/common/jquery/jquery.function.js"></script>
<script type="text/javascript" src="${_resource}/js/common/jquery/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript" src="${_resource}/js/common/common.js"></script>
<script type="text/javascript" src="${_resource}/js/common/uicommon.js"></script>
<script type="text/javascript" src="${_resource}/js/common/jquery/jquery.form.js"></script>

<link rel="stylesheet" type="text/css"  href="${_resource}/lib/audio/skin/blue.monday/css/jplayer.blue.monday.css"/>
<script type="text/javascript" src="${_resource}/lib/audio/jplayer/jquery.jplayer.min.js"></script>

<!-- Font-awesome -->
<link rel="stylesheet" type="text/css" href="${_resource}/lib/font-awesome/css/font-awesome.min.css" />

<!-- Table sorter -->
<script type="text/javascript" src="${_resource}/lib/tablesorter/jquery.tablesorter.js"></script>
<script type="text/javascript" src="${_resource}/lib/tablesorter/jquery.tablesorter.widgets.js"></script>

<!-- jqGrid -->
<link type="text/css" rel="stylesheet" href="${_url}/lib/jqGrid/css/ui.jqgrid.css?20180303_001"/>
<script type="text/javascript" src="${_url}/lib/jqGrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="${_url}/lib/jqGrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${_url}/lib/jqGrid/inbiznetcorp.jqGrid.js"></script>

<script type="text/javascript">
	function btnLogout(){
		location.href="/login/DeleteData.do";
	}
	$(function(){

	});
</script>

</head>
<body>
	<div class="wrap">
		<div id="head">
		<div id="layout_top">
		    <div class="company">
<%-- 		         <img src="${_resource}/static/images/logo/hyundai_ci2.png" style="height: 40px; width: auto;" /> --%>
		   </div>

		    <div class="rbtn">
<!-- 		    	<font class="rbtn_font1"><i class="fa fa-user-circle-o fa-lg" aria-hidden="true"></i>&nbsp;관리자 그룹 </font> -->
<%-- 		  	    <font class="rbtn_font2">${session_emp_nm}</font> --%>
		  	    <button type="button" class="header_button" onclick="btnLogout();">로그아웃</button>
		    </div>

		</div>

		<div class="nav-container">
			<div id="layout_menu">
				<ul id="nav">
				   <li class="top border_right" id="m0"><a href="/eqacclog/ListPagingData.do" id="gnb_0" class="top_link">접속로그</a></li>
				    <li class="top" id="m0"><a href="/recordPlay/ListPagingData.do" id="gnb_0" class="top_link">권한등록이력</a></li>

				    <li class="top" id="m1"><a href="/playHistory/ListPagingData.do" id="gnb_0" class="top_link">서버접속이력</a></li>

				    <li class="top border_right" id="m2"><a href="/nrlmber/ListPagingData.do" id="gnb_0" class="top_link">직원 조회</a></li>

				    <li class="top border_right" id="m2"><a href="/eqlist/" id="gnb_0" class="top_link">자산관리</a></li>

				     <li class="top border_right" id="m2"><a href="/ctn/code/" id="gnb_0" class="top_link">코드등록</a></li>
				</ul>
			</div>
		</div>
	</div>

	<jsp:doBody/>

	</div>

	<div id="footer">
		<div class="footer_div1">
<%-- 			<img  src="${_url}/r2pRecordStreaming/static/images/logo/hyundai_ci2.png"/> --%>
		</div>
		<div class="footer_div2">
			<span class="footer_font1">서울시 마포구 마포대로 49, 1007호 (도화동,  성우빌딩)<br/></span>
<!-- 			<b><span class="footer_font2">COPYRIGHT © HYUNDAI COMMERCIAL INC. ALL RIGHTS RESERVED.</span></b> -->
		</div>
	</div>

</body>
</html>


