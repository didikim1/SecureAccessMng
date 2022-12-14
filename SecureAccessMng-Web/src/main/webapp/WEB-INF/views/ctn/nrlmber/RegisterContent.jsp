<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">

		<table id="grid"></table>
		<div id="pager"></div>


			<form name="FormComtngnrlmber">
				<input type="hidden" name="seq" 		value="${Info.seq}" />
				<input type="hidden" name="nrlmberId" 	value="${Info.seq}" />
				<input type="hidden" name="mberSttus" 	value="${Info.mberSttus}" />
				<input type="hidden" name="uniqId" 		value="${Info.uniqId}" />
				<div align="center"  >
					<table class="htable" >
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
							<th scope="col" width="120px">소유자</th>
								<th class = "nowInfo">
								<input type="text" class="userManageInputNow"  value="${Info.mberNameDisplay}"disabled >
								<input type="text" class="userManageInputRev" id="mberName" name="mberName" placeholder="변경할 소유자명을 입력하세요." autocomplete="off" value="" />
								</th>
						</tr>
						<tr>
							<th scope="col" width="120px">휴대폰번호</th>
								<th class = "nowInfo">
								<input type="text" class="userManageInputNow" value="${Info.moblphonNoDisplay}" disabled>
								<input type="text" class="userManageInputRev" id="moblphonNo" name="moblphonNo"
								oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" placeholder="변경할 휴대폰번호를 입력하세요." autocomplete="off" value="" /></th>
						</tr>
						<tr>
							<th scope="col" width="120px">담당</th>
							<th class ="nowInfo">
							<input type="text" class="userManageInputNow" value="${Info.chargeName}" disabled>
							<select class="common_selectRev" name="chargeId">
								<option value="" <c:if test="${Info.chargeId eq ''}" >selected</c:if> >선택</option>
									<c:forEach var="charge" items="${ChargeList}" varStatus="status">
										<c:if test="${charge.seq ne '1' }">
											<option value="${charge.seq}" <c:if test="${Info.chargeId eq ''}">selected</c:if> >${charge.name}</option>
										</c:if>
									</c:forEach>
							</select>
						</tr>
						<tr>
							<th scope="col" width="120px">권한</th>
							<th class ="nowInfo">
							<input type="text" class="userManageInputNow" value="${Info.roleName}" disabled>
								<select class="common_selectRev" name="roleId">
									<option value="" <c:if test="${Info.roleId eq ''}">selected</c:if> >선택</option>
									<c:forEach var="role" items="${RoleList}" varStatus="status">
											<option value="${role.roleId}" <c:if test="${Info.roleId eq ''}">selected</c:if> >${role.roleName}</option>
									</c:forEach>
								</select>
						</tr>
						<tr>
							<th scope="col" width="120px">정/부</th>
							<th class ="nowInfo">
							<input type="text" class="userManageInputNow"  value="${Info.mberRatingName}" disabled>
							<select class="common_selectRev" name="mberRating">
									<option value="" <c:if test="${Info.mberRating eq '' }">selected</c:if> >선택</option>
									<option value="M"  <c:if test="${Info.mberRating eq ''}">selected</c:if>  >정</option>
									<option value="D"  <c:if test="${Info.mberRating eq ''}">selected</c:if>  >부</option>
							</select>
						</tr>
					</table>
				</div>
			</form>
		</div>

		<div class="border margin_l7">
			<c:choose>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButtonRev" onclick="fnCallAuthPage('A')">수정</button>
					<button type="button" class="userManageButtonRev" onclick="fnCallAuthPage('C')">해지</button>
<!-- 					<button type="button" class="userManageButton" onclick="fnCallAuthPage()" name ="fnCallAuthPage">ARS</button> -->
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">

// Info
var dataOjb = {};

var origin_uniqId		= '${Info.uniqId}';
var origin_seq			= '${Info.seq}';
var origin_mberName 	= '${Info.mberName}';
var origin_moblphonNo	= '${Info.moblphonNo}';
var origin_chargeId 	= '${Info.chargeId}';
var origin_roleId 		= '${Info.roleId}';
var origin_mberRating 	= '${Info.mberRating}';

console.log(origin_mberName)
console.log(origin_moblphonNo)
console.log(origin_chargeId)
console.log(origin_roleId)
console.log(origin_mberRating)


function fnRegExpChk(str, regExp) {
    if(regExp.test(str)) {
        return true;
    }else{
        return false;
    }
}

function fnCallAuthPage(mberSttus) {
	// mberSttus = A

	var idRegExp_Kor       	= /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;	    																					// 	한글체크
	var pwsRegExp		  	= /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/; 										//	비밀번호 체크 (숫자, 소문자, 대문자, 특수문자)
	var phoneRegExp			= /^\d{2,3}\d{3,4}\d{4}$/;																					//	전화번호 체크

	var form = $("[name=FormComtngnrlmber]");
	// jqeury  // isNull
	var seq					= seq;
	var mberName 			= form.find("[name=mberName]").val();
	var moblphonNo 			= form.find("[name=moblphonNo]").val();
	var chargeId 			= form.find("[name=chargeId]").val();
	var roleId 				= form.find("[name=roleId]").val();
	var mberRating 			= form.find("[name=mberRating]").val();

	form.find("[name=mberSttus]").val(mberSttus);

	console.log("==============")
	console.log("============== >>> :: " + mberSttus )
	console.log("==============")

	if( "C" == mberSttus ){
		$.fun.ajax({
			type:'get',
			data:dataOjb,
			url:"/ctn/nrlmber/CallAuthPage.do",
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
	}else if( isNull( mberName) && isNull( moblphonNo) &&  isNull( chargeId) &&  isNull( roleId) &&  isNull( mberRating) ){
		$.fun.alert({
			content : "수정 할 사항이 없습니다. ",
		});
		 }else if( isNotNull(mberName) && ( !fnRegExpChk(mberName, idRegExp_Kor) ) ){
			$.fun.alert({
				content : "이름은 한글만 가능합니다.",
				action : function() {
					$("[name=mberName]").focus();
				}
			});
		}else if( isNotNull(moblphonNo) && ( !fnRegExpChk(moblphonNo, phoneRegExp))) {
			$.fun.alert({
				content : "입력된 휴대폰 번호를 확인 해 주세요.",
				action : function() {
					$("[name=moblphonNo]").focus();
				}
			});
	} else {
		$.fun.ajax({
			type:'get',
			data:dataOjb,
			url:"/ctn/nrlmber/CallAuthPage.do",
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
	}

}

	function fnProcRegisterData(){

		var form = $("[name=FormComtngnrlmber]");


		dataOjb.seq				= origin_seq;
		dataOjb.nrlmberId		= origin_seq;
		dataOjb.mberSttus   	= form.find("[name=mberSttus]").val();
		dataOjb.mberName   		= form.find("[name=mberName]").val();
		dataOjb.moblphonNo 		= form.find("[name=moblphonNo]").val();
		dataOjb.chargeId 		= form.find("[name=chargeId]").val();
		dataOjb.roleId 			= form.find("[name=roleId]").val();
		dataOjb.mberRating 		= form.find("[name=mberRating]").val();


		 if( isNull( dataOjb.mberName) )     		{ dataOjb.mberName 		= origin_mberName; }
		 if( isNull( dataOjb.moblphonNo) )   		{ dataOjb.moblphonNo 	= origin_moblphonNo; }
		 if( isNull( dataOjb.chargeId) )  			{ dataOjb.chargeId 		= origin_chargeId; }
		 if( isNull( dataOjb.roleId) )   			{ dataOjb.roleId 		= origin_roleId; }
		 if( isNull( dataOjb.mberRating) )   		{ dataOjb.mberRating 	= origin_mberRating; }

		 console.log(dataOjb); // {mberName : '전효성', moblphonNo: '010...'}

	$.fun.ajax({
		type:'post',
		//data:$( "[name=FormComtngnrlmber]" ).serialize(),
		data:dataOjb,
		url:"/ctn/nrlmber/ProcRegisterData.do",
		dataType:"JSON",
		success:function(data){
			console.log(data);
			if( "200" == data.code ) {
				$.fun.alert({content:"정상 처리되었습니다.", action:function(){
					location.reload();
				}});
			}else {
				$.fun.alert({content:"Error!!!!!", action:function(){
					location.reload();
				}});
			}
		}
	});
}

function fnDeleteData(){
	$( "[name=mberSttus]" ).val("C");
	$.fun.ajax({
		type:'post',
		data:$( "[name=FormComtngnrlmber]" ).serialize(),
		url:"/ctn/nrlmber/DeleteData.do",
		dataType:"JSON",
		success:function(data){
			console.log(data);
			if( "200" == data.code ) {
				$.fun.alert({content:"정상 처리되었습니다.", action:function(){
					location.reload();
				}});
			}else {
				$.fun.alert({content:"Error!!!!!", action:function(){
					location.reload();
				}});
			}
		}
	});

}

function fnClose(){
	$("#induacaAdd").dialog('destroy').remove();
}



$(document).ready(function(){
	var uniqId 	= $("[name=uniqId]").val();

	$("#grid").jqGrid(jqGridUtils.fn_JQGridOption({
		datatype:'json',
		url:'/jqGrid/init',
		colNames: [
		           	 '정/부'
		           	,'소유자'
		           	,'휴대폰번호'
		           	,'담당'
		           	,'권한'
		           	,'상태'
		           	,'처리자'
		           	,'처리일'
		           ],
		colModel: [
				 {name:'mberRatingName', 	index:'mberRatingName',			width:10,	align:'center', search:false,  sortable:true}
				,{name:'mberNameDisplay', 	index:'mberNameDisplay',		width:10,	align:'center', search:false,  sortable:true}
				,{name:'moblphonNoDisplay', index:'moblphonNoDisplay',		width:13,	align:'center', search:false,  sortable:true}
				,{name:'chargeName', 		index:'chargeName',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'roleName', 			index:'roleName',				width:15,	align:'center', search:false,  sortable:true}
				,{name:'mberSttusName', 	index:'mberSttusName',			width:10,	align:'center', search:false,  sortable:true}
				,{name:'frstRegisterId', 	index:'frstRegisterId',			width:10,	align:'center', search:false,  sortable:true}
				,{name:'frstRegisterPnttm', index:'frstRegisterPnttm',		width:15,	align:'center', search:false,  sortable:true}
		],

		pager:"#pager",
		rowNum:10,
		width:"300px",
		height:"150px",
		sortname:"LAST_UPDUSR_PNTTM",
   		sortorder:"desc",
   		onSelectRow:function(rowid, status,e){
   			var rowval = $('#grid').jqGrid('getRowData', rowId);
   			console.log(rowval)
  			$("[name=mberRatingName]")		.val(rowval.mberRatingName);
   			$("[name=mberNameDisplay]")		.val(rowval.mberNameDisplay);
   			$("[name=moblphonNoDisplay]")	.val(rowval.moblphonNoDisplay);
   			$("[name=chargeName]")			.val(rowval.chargeName);
   			$("[name=roleName]")			.val(rowval.roleName);
   			$("[name=mberSttusName]")		.val(rowval.mberSttusName);

   		}
	}))
	/*
		$.fun.ajax({
		type:'get',
		url:"/ctn/nrlmber/RegisterData.do?uniqId="+uniqId,
        data : {}
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"계정등록",
				"width":475,
				"buttons":{}
			});
		}
	});
	*/

	setTimeout(function(){
		var paramMap = {};
		console.log('${Info.seq}');
		paramMap["nrlmberId"] 		= '${Info.seq}';  // 
		console.log("paramMap : " + paramMap);
		jqGridUtils.searchProc({
		     gridId:"#grid"
			,postData:paramMap
		   ,url:"/ctn/nrlmber/history/ListPagingData.do"
		});
	}, 500);
});

</script>
