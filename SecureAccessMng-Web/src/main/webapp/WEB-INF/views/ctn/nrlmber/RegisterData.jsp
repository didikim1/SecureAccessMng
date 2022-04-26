<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">
			<form name="FormComtngnrlmber">
				<input type="hidden" name="seq" value="${Info.seq}" />
				<div align="center" >
					<table class="htable">
<!-- 						<tr> -->
<!-- 							<th scope="col" width="120px">부서</th> -->
<!-- 							<td> -->
<!-- 								<select class="userManageInput" name="dpamentId"> -->
<%-- 									<c:forEach var="data" items="${ComtnDpamentList.list}" varStatus="status"> --%>
<%-- 										<option value="${data.dpamentId}" <c:if test="${Info.dpamentId eq data.dpamentId}">selected</c:if> >${data.dpamentName}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<th scope="col" width="120px">직위</th> -->
<!-- 							<td> -->
<!-- 								<select class="userManageInput" name=positionId> -->
<%-- 									<c:forEach var="data" items="${ComtnPositionList.list}" varStatus="status"> --%>
<%-- 										<option value="${data.positionId}" <c:if test="${Info.positionId eq data.positionId}">selected</c:if> >${data.positionName}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
						<tr>
							<th scope="col" width="120px" class = "MainInfo" >ID</th>
							<td><input type="text" class="userManageInputMain" id="uniqId" name="uniqId" autocomplete="off"  value="${Info.uniqId}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px"class = "MainInfo">이름</th>
							<td><input type="text" class="userManageInput" id="mberName" name="mberName" autocomplete="off" value="${Info.mberName}" /></td>
						</tr>

						<tr>
							<th scope="col" width="120px"class = "MainInfo">휴대폰번호</th>
							<td><input type="text" class="userManageInput" id="moblphonNo" name="moblphonNo" autocomplete="off"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" placeholder="숫자만입력" value="${Info.moblphonNo}" maxlength="12"></td>
						</tr>
						<tr>
							<th scope="col" width="120px"class = "MainInfo">Email</th>
							<td><input type="text" class="userManageInput" id="emailAddress" name="emailAddress" autocomplete="off" value="${Info.emailAddress}"></td>
						</tr>
						<tr>
							<th scope="col" width="120px"class = "MainInfo">담당</th>
							<td>
							<select class="common_selectMain" name="chargeId">
								<option value="" <c:if test="${Info.chargeId eq ''}">selected</c:if> >선택</option>
									<c:forEach var="charge" items="${ChargeList}" varStatus="status">
										<c:if test="${charge.seq ne '1' }">
											<option value="${charge.seq}" <c:if test="${Info.chargeId eq charge.seq}">selected</c:if> >${charge.name}</option>
										</c:if>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px"class="AccountMain">권한</th>
							<td>
								<select class="common_selectMain" name="roleId">
									<option value="" <c:if test="${Info.roleId eq ''}">selected</c:if> >선택</option>
									<c:forEach var="role" items="${RoleList}" varStatus="status">
											<option value="${role.roleId}" <c:if test="${Info.roleId eq role.roleId}">selected</c:if> >${role.roleName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px"class="AccountMain">정/부</th>
							<td>
							<select class="common_selectMain" name="mberRating">
										<option value="" <c:if test="${Info.mberRating eq ''}">selected</c:if> >선택</option>
										<option value="M"  <c:if test="${Info.mberRating eq 'M'}">selected</c:if>  >정</option>
										<option value="D"  <c:if test="${Info.mberRating eq 'D'}">selected</c:if>  >부</option>
							</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px"class="AccountMain">상태</th>
							<td>
								<select class="common_selectMain" name="mberSttus">
										<option value="" <c:if test="${Info.mberSttus eq ''}">selected</c:if> >선택</option>
										<option value="A"  <c:if test="${Info.mberRating eq 'A'}">selected</c:if>  >활성화</option>
										<option value="C"  <c:if test="${Info.mberRating eq 'C'}">selected</c:if>  >중지</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>

	<%-- 	<div class="border margin_l7">
			<c:choose>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">수정하기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="userManageButton" onclick="fnClose()">닫기</button>
		</div>
	--%>
		<div class="border margin_l7">
			<c:choose>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButtonAddAcount" onclick="fnProcUniqIdChk()">등록</button>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">


function fnRegExpChk(str, regExp) {
    if(regExp.test(str)) {
        return true;
    }else{
        return false;
    }
}


function fnProcUniqIdChk(){

//	var abcd = "가나다라fsfsf1234";

// 	var ___arr = ["fsfsf1234", "가나다라", "hsjeon1224", "kdh1126"];

// 	var arr = abcd.split(",");
// 	var zzz = abcd.substr(0,3)

// 	console.log(abcd);
// 	console.log(arr);
// 	console.log(zzz);

	var idRegExp_EngNumber 	= /^[a-zA-z0-9]{4,12}$/;																					// 	영문 + 숫자체크
	var idRegExp_Kor       	= /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;	    																					// 	한글체크
	var pwsRegExp		  	= /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/; 										//	비밀번호 체크 (숫자, 소문자, 대문자, 특수문자)
	var mailRegExp 			= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;  					// 	이메일 체크
	var phoneRegExp			= /^\d{2,3}\d{3,4}\d{4}$/;																				//	전화번호 체크

	// search()
// 	console.log(idRegExp.search(abcd));


	var idRegExp = /^[a-zA-z0-9]{4,12}$/;

	var form = $("[name=FormComtngnrlmber]");
	// jqeury  // isNull
	var uniqId 				= form.find("[name=uniqId]").val();
	var mberName 			= form.find("[name=mberName]").val();
	var password 			= form.find("[name=password]").val();
	var moblphonNo 			= form.find("[name=moblphonNo]").val();
	var emailAddress 		= form.find("[name=emailAddress]").val();
	var chargeId 			= form.find("[name=chargeId]").val();
	var roleId 				= form.find("[name=roleId]").val();
	var mberRating 			= form.find("[name=mberRating]").val();
	var mberSttus 			= form.find("[name=mberSttus]").val();


	console.log("mberName : " + mberName)

	if( isNull(uniqId)  ){
		$.fun.alert({
			content : "계정을 입력해 주세요.",
			action : function() {
				$("[name=uniqId]").focus();
			}
		});
	}else if( !fnRegExpChk(uniqId, idRegExp) ){
		$.fun.alert({
			content : "아이디는 영문 숫자 조합으로 되어야 합니다.",
			action : function() {
				$("[name=uniqId]").focus();
			}
		});
	}else if( isNull( mberName) ){
		$.fun.alert({
			content : "이름을 입력해주세요.",
			action : function() {
				$("[name=mberName]").focus();
			}
		});
	}else if( !fnRegExpChk(mberName, idRegExp_Kor) ){
		$.fun.alert({
			content : "이름은 한글만 가능합니다.",
			action : function() {
				$("[name=mberName]").focus();
			}
		});
	}else if( isNull( moblphonNo) ){
		$.fun.alert({
			content : "휴대폰번호를 입력해주세요.",
			action : function() {
				$("[name=moblphonNo]").focus();
			}
		});
	}else if(  !fnRegExpChk(moblphonNo, phoneRegExp)){
		$.fun.alert({
			content : "입력된 휴대폰 번호를 확인 해 주세요.",
			action : function() {
				$("[name=moblphonNo]").focus();
			}
		});
	}else if( isNull( emailAddress) ){
		$.fun.alert({
			content : "Email을 입력해주세요.",
			action : function() {
				$("[name=emailAddress]").focus();
			}
		});
	}else if( !fnRegExpChk(emailAddress, mailRegExp) ){
		$.fun.alert({
			content : "Email을 형식에 맞게 입력해주세요.",
			action : function() {
				$("[name=emailAddress]").focus();
			}
		});
	}else if( isNull( chargeId) ){
		$.fun.alert({
			content : "담당을 선택해주세요.",
			action : function() {
				$("[name=chargeId]").focus();
			}
		});
	}else if( isNull( roleId) ){
		$.fun.alert({
			content : "권한을 선택해주세요.",
			action : function() {
				$("[name=roleId]").focus();
			}
		});
	}else if( isNull( mberRating) ){
		$.fun.alert({
			content : "정/부 를 선택해주세요.",
			action : function() {
				$("[name=mberRating]").focus();
			}
		});
	}else if( isNull( mberSttus) ){
		$.fun.alert({
			content : "상태를 선택해주세요.",
			action : function() {
				$("[name=mberSttus]").focus();
			}
		});
	} else {
		$.fun.ajax({
			type : 'post',
			data : {
				"uniqId" : uniqId
			},
			url : "/ctn/nrlmber/uniqIdChk.do",
			dataType : "JSON",
			success : function(data) {
				if ("404" == data.code) {
					fnProcRegisterData();
				} else {
					$.fun.alert({
						content : "이미 등록된 아이디입니다. 다시 입력 해 주세요.",
						action : function() {
							$("#uniqId").val("");
							$("#uniqId").focus();
						}
					});
				}
			}
		});
	}

}


function fnProcRegisterData() {
	$.fun.ajax({
		type : 'post',
		data : $("[name=FormComtngnrlmber]").serialize(),
		url : "/ctn/nrlmber/ProcRegisterData.do",
		dataType : "JSON",
		success : function(data) {
			console.log(data);
			if ("200" == data.code) {
				$.fun.alert({
					content : "정상 처리되었습니다.",
					action : function() {
						location.reload();
					}
				});
			} else {
				$.fun.alert({
					content : "Error!!!!!",
					action : function() {
						location.reload();
					}
				});
			}
		}
	});
}


function fnClose() {
	$("#induacaAdd").dialog('destroy').remove();
}
</script>
