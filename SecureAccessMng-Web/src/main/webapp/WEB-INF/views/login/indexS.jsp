<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

function fnLogin(){



// 	location.href="/eqlist/";

	var _uniqid 	= $("[name=uniqid]").val();
	var _password 	= $("[name=password]").val();

	if ( isNull(_uniqid) ){
		alert("[아이디] 입력해주세요.");
		return;
	} else if( isNull(_password ) ) {
		alert("[비밀번호를] 입력해주세요.");
		return;
	}
	else{
		$.ajax({
	        url: '/login/ProcSelectOneData.do',
	        type: 'post',
	        data:$("[name=loginForm]").serialize(),
	        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	        dataType: 'json',
	        success: function (result) {
	        	console.log(result);
	        	if ( "200" == result.code ){
// 	        		location.href="/recordPlay/";
// 	        		location.href="/eqlist/";
	        		location.href="/eqacclog/ListPagingData.do";
	        	} else if ( "100" == result.code ){
	        		alert("[아이디/비밀번호] 확인해주세요.");
	        	} else {
	        		alert("Error");
	        	}
	        },
			error:function(request,status,error){
	        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	       }

	    });
	}
}

$(document).ready(function(){

	$("input[name=mberId]").keydown(function (key) {
        if(key.keyCode == 13){
        	fnLogin();
        }
    });
	$("input[name=password]").keydown(function (key) {
        if(key.keyCode == 13){
        	fnLogin();
        }
    });
	$("#btnLogin").click(function(){
		fnLogin();
	});
});
</script>