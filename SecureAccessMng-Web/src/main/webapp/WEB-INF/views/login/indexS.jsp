<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">


function fnModifyPasswordProc(){
	
	var _uniqid 		= $("[name=FormModifyPassword]").find("[name=uniqid]").val();
	var _password 		= $("[name=FormModifyPassword]").find("[name=password]").val();
	var _newpassword 	= $("[name=FormModifyPassword]").find("[name=newpassword]").val();
	var _newpassword2 	= $("[name=FormModifyPassword]").find("[name=newpassword2]").val();

	if ( isNull(_uniqid) ){
		$.fun.alert({content:"[아이디] 입력해주세요."});
		return false;
	} else if( isNull(_password ) ) {
		$.fun.alert({content:"현재 비빌번호를 입력해주세요."});
		return false;
	} else if ( _newpassword != _newpassword2 ) {
		$.fun.alert({content:"새로운 비밀번호 두개가 일치하지 않습니다."});
		return false;
	} else {
		$.ajax({
	        url: '/login/ModifyPasswordProc.do',
	        type: 'post',
	        data:$("[name=FormModifyPassword]").serialize(),
	        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	        dataType: 'json',
	        success: function (result) {
	        	if ( "200" == result.code ){
	        		location.href="/eqacclog/ListPagingData.do";
	        	} else if ( "400" == result.code ){
	        		$.fun.alert({content:"비밀번호는가 정책에 맞지않습니다.(10~16자리, 특수문자 포함, 연속되지않는 문자)"});
	        		return false;
	        	} else {
	        		alert("Error");
	        	}
	        },
			error:function(request,status,error){
	        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	       }
	    });
	}
	return false;
}

function fnModifyPasswordPop(uniqid){
	 $.fun.ajax({
		type:'get',
		url:"/login/ModifyPassword.do?uniqid="+uniqid,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd"
				,content:data
				,title:"비밀번호 변경"
				,width:500
				,buttons:{
         			"확인": function() {
         				fnModifyPasswordProc();
//          				$(this).dialog('destroy').remove();
         			},"닫기":function(){
         				$(this).dialog('destroy').remove();
         			}
     			}
			});
		}
	});
 }

function fnLogin(){
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
	        	if ( "200" == result.code ){
	        		location.href="/eqacclog/ListPagingData.do";
	        	} else if ( "304" == result.code ){
	        		var uniqid = result.result.uniqid;
	        		$.fun.alert({
	        			content:"현재 사용중인 비밀번호는 내부 정책에 맞지않습니다. <br/> 변경이 필요합니다."
	        			,buttons:{
		         			"확인": function() {
		         				$(this).dialog('destroy').remove();
		         				fnModifyPasswordPop(uniqid);
		         			}
	         			}
	        		});
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