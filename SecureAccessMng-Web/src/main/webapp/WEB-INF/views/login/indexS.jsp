<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">


function fnModifyPasswordProc(){

	var _uniqId 		= $("[name=FormModifyPassword]").find("[name=uniqId]").val();
/* 	var _password 		= $("[name=FormModifyPassword]").find("[name=password]").val();
	var _newpassword 	= $("[name=FormModifyPassword]").find("[name=newpassword]").val();
	var _newpassword2 	= $("[name=FormModifyPassword]").find("[name=newpassword2]").val(); */

	if ( isNull(_uniqId) ){
		$.fun.alert({content:"[아이디]를 입력해주세요."});
		return false;
	/* } else if( isNull(_password ) ) {
		$.fun.alert({content:"현재 비빌번호를 입력해주세요."});
		return false;
	} else if ( _newpassword != _newpassword2 ) {
		$.fun.alert({content:"새로운 비밀번호 두개가 일치하지 않습니다."});
		return false; */
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
	var _uniqId 	= $("[name=uniqId]").val();
	/* var _password 	= $("[name=password]").val(); */

	if ( isNull(_uniqId) ){
		alert("[아이디] 를 입력해주세요.");
		return;
	/* } else if( isNull(_password ) ) {
		alert("[비밀번호]를 입력해주세요.");
		return; */
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
	        		fnCallAuthPage()
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
	        		$.fun.alert({content:"아이디/비밀번호를 확인해주세요."});
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

function fnCallAuthPage() {
	var _uniqId 	= $("[name=uniqId]").val();

	if ( isNull(_uniqId) ){
		alert("아이디 를 입력해주세요.");
		$("[name=uniqId]").focus();
		return true;
	} else {
		$.fun.ajax({
			type:'get',
			url:'/login/idCheck.do?uniqId='+_uniqId, // id 체크여부
			dataType:"JSON",
			success:function(data){
				if(data.code == "200"){
					$.fun.ajax({
						type:'get',
						url:"/login/CallAuthPage.do?uniqId="+_uniqId,
						success:function(data){
							$.fun.layout({
								id:"CallAuthPage",
								"content":data,
								"title":"ARS인증요청",
								"width":475,
								"buttons":{}
							});
						}
					});
				} else if (data.code == "404"){
					$.fun.alert({
						content : "등록된 ID가 없습니다.",
						action : function() {
							 $("[name=uniqId]").val("");
							 $("[name=uniqId]").focus();
							$(this).dialog('destroy').remove();
						}
					});
				} else {
					$.fun.alert({
						content : "죄송합니다. 잠시 후 다시 이용해주세요.",
						action : function() {
							location.reload();
						}
					});
				}
			}
		})
	};

		/*
		$.fun.ajax({
			type:'get',
			url:"/login/CallAuthPage.do?uniqId="+_uniqId,
			success:function(data){
				$.fun.layout({
					id:"CallAuthPage",
					"content":data,
					"title":"ARS인증요청",
					"width":475,
					"buttons":{}
				});
			}
		});
		*/

	return true;
}

$(document).ready(function(){

	$(document).keydown(function (event ) {
		if(event.keyCode == 13){
			event.preventDefault();
			fnCallAuthPage();
        }
		return true;
	});
	$("#btnLogin").click(function(){
		fnCallAuthPage();
	});


});
</script>