<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="" >
			ARS인증 콜을 발신하였습니다.
			인증번호 <h1 id="authNumber"></h1>
			눌러주세요.
		</div>
	</div>
</div>
<script type="text/javascript">
function fnAuthCallSender() {


 	 var authNumber = Math.floor(Math.random() * 98) +10 ;
 	 if( authNumber > 100 ){
 		 authNumber -= 10;
 	 }
 	 $("#authNumber").text(authNumber);

	 $.ajax({
		type:'get',
		url:"/login/CallAuth.do",
		 dataType : "json",
		data:{'authNumber':authNumber, "moblphonNo": $( "[name=moblphonNo]" ) },
		success:function(data){
			console.log(data);
			if(data.code == "200"){
				$.fun.alert({content:"인증 성공",  action:function(){
					location.href="/eqacclog/ListPagingData.do";
				}})
			} else {
				$.fun.alert({content:"인증 실패로 처리되지않았습니다.", action:function(){
					location.reload();
				}});
			}
	}})
};

$(document).ready(function(){
	setTimeout(function(){
		fnAuthCallSender();

	}, 500);

})
</script>