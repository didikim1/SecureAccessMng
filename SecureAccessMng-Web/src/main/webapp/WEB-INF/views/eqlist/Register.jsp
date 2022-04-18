<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">

			<!-- A:자산등록, B:계정등록, C:서버관리 -->
			<c:choose>
				<c:when test="${paramMap.registType eq 'A'}">
					<jsp:include page="register/Equip.jsp" flush="false" />
				</c:when>
				<c:when test="${paramMap.registType eq 'B'}">
					<jsp:include page="register/Account.jsp" flush="false" />
				</c:when>
				<c:when test="${paramMap.registType eq 'C'}">
					<jsp:include page="register/Server.jsp" flush="false" />
				</c:when>
			</c:choose>

		</div>

		<div class="border margin_l7">
<%-- 			<c:choose> --%>
<%-- 				<c:when test="${Info.mberId != '' || Info.mberId ne null}"> --%>
<!-- 					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">수정하기</button> -->
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<!-- 					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록하기</button> -->
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>

			<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록</button>
<!--  		<button type="button" class="userManageButton" onclick="fnClose()">닫기</button>-->
		</div>

	</div>
</div>
<script type="text/javascript">
function fnProcRegisterData()
{
	var registType = "${paramMap.registType}";

	// 장비등록
	if(registType == "A")
	{
		fnProcRegisterEquipCodeData();
	}
	// 계정등록
	else if(registType == "B")
	{
		fnProcRegisterAccountData();
	}
	// 계정등록
	else if(registType == "C")
	{
		fnProcRegisterServerData();
	}

}

function fnProcRegisterEquipCodeData()
{
	// 관리자, IDC 선택하지 않은 경우 체크.
	if(!$("[name=idcSeq]").val())
	{
		$.fun.alert({content:"준비중입니다."});
		$("[name=idcSeq]").focus();
		return;
	}
// 	if(!$("[name=entrprsmberSeq]").val())
// 	{
// 		$.fun.alert({content:"관리자를 선택하세요."});
// 		$("[name=entrprsmberSeq]").focus();
// 		return;
// 	}

	// 자산 등록
	$.fun.ajax({
		type:'get',
		url:"/eqlist/RegisterData.do",
		data:$("form[name=FormEqList]").serialize(),
		success:function(data){
			$.fun.alert({content:"정상 처리되었습니다.", action:function(){
				location.reload();
			}});
		}
	});
}

function fnProcRegisterAccountData()
{
	var inputText = $("form[name=FormEqList] input[type=text]");
	for(var i=0; i<inputText.length; i++)
	{
		if(!inputText.eq(i).val())
		{
			$.fun.alert({content:"비어있는 항목이 있습니다."});
			return false;
		}
	}
	

	// 계정 등록
	$.fun.ajax({
		type:'get',
		url:"/eqidpwd/RegisterData.do",
		data:$("form[name=FormEqList]").serialize(),
		success:function(data){
			$.fun.alert({content:"정상 처리되었습니다.", action:function(){
				location.reload();
			}});
		}
	});
}

// 장비등록
function fnOpenRegisterEquipCodePage(title,inputName){
	$.fun.ajax({
		type:'get',
		url:"/ctn/code/PopupListData.do?title="+title+"&registType=A&type=B&inputName="+inputName,
		success:function(data){
			console.log(data)
			$.fun.layout({
				id:"RegisterSvModelPageEquipment",
				"content":data,
				"title":"장비등록",
				"width":600,
				"buttons":{}
			});
		}
	});
}

// 계정등록
function fnOpenRegisterAccountPage(title,inputName){
	$.fun.ajax({
		type:'get',
		url:"/ctn/code/PopupListData.do?title="+title+"&registType=B&type=B&inputName="+inputName,
		success:function(data){
			console.log(data)
			$.fun.layout({
				id:"RegisterSvModelPageEquipment",
				"content":data,
				"title":"계정등록",
				"width":600,
				"buttons":{}
			});
		}
	});
}

function fnClose(){
	$("#induacaAdd").dialog('destroy').remove();
}

$(document).ready(function(){
	$("[name=receivingPnttm]").datepicker({
		 dayNamesMin : ['일', '월', '화', '수', '목', '금', '토']
		,monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
		,dateFormat : "yy-mm-dd"
		,showOn: "both"
		,buttonImage : "/images/ico_date.png"
	});
});
</script>
