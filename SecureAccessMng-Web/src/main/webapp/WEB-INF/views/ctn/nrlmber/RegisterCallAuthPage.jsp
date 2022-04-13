<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="" >	
			관리자에게 ARS를 요청중입니다.
		</div>
	</div>
</div>
<script type="text/javascript">
function fnAuthCallSender() {
	
	// JSONObject rtrn =  mCommonBiz.authCallSender(paramMap.getStr("moblphonNo"), paramMap.getStr("authNumber"));
	
// 	 var authNumber = Math.floor(Math.random() * 98) +10 ;
// 	 if( authNumber > 100 ){
// 		 authNumber -= 10;
// 	 }
// 	 $("#authNumber").text(authNumber);
	 
// 	 var moblphonNo = "01036253133";
	 
	 $.ajax({
		type:'get',
		url:"/ctn/nrlmber/CallAuth.do",
		//data:{'moblphonNo':moblphonNo, 'authNumber':authNumber},
		success:function(data){
			console.log(data);
		}
	});
	 
	 
}
$(document).ready(function(){
	setTimeout(function(){
		fnAuthCallSender() ;
		
	}, 500);
	
})
</script>