<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>
<c:set var="Ver"  value="20200601_001" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>[INBIZNET] 접속관리</title>
<tag:header_comm_stylesheet/>
<tag:header_comm_script/>
</head>
<body>
			<div class="wrap">
				<div id="head"></div>

				<div id="content">
				    	<div class="login_box block">
				    		<div class="login_text_box block">
				    			<p class="main_text1"><b>인비즈넷 개인정보처리 서버</b></p>
								<p class="main_text2"><b>접속관리 페이지</b></p>
				    		</div>

				    		<div class="login_input_box block">
				    			<form action="" name="loginForm">
					    			<div class="login_div_sub">
					    				<div class="login_div">아이디</div>
										<input type="text" name="uniqId" id="uniqId" class="loginInput" placeholder="아이디" autocomplete="off"/>
										<div class="login_div">비밀번호</div>
										<input type="password" name="password" id="password" class="loginInput" placeholder="비밀번호" autocomplete="off"/>
									</div>
								</form>

								<button class="loginButton" type="button" id="btnLogin">
									<b>로그인</b>
								</button>

								<p class="main_text3">
									<span class="glyphicon glyphicon-exclamation-sign"></span>
									본 사이트는 승인 받은 관리자의 PC에서만 접속 가능합니다.<br/> Chrome (1920x1080)에 최적화되어 있습니다.
								</p>
				    		</div>
				    	</div>
				</div>

				<div id="footer">
					<div class="footer_div1">
					</div>
					<div class="footer_div2">
						<span class="footer_font1">서울시 마포구 마포대로 49, 1007호 (도화동,  성우빌딩)<br/></span>
					</div>
				</div>
			</div>
</body>
<jsp:include page="indexS.jsp" flush="false" />
</html>
