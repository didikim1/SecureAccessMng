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
				<input type="hidden" name=mberSttus 	value="${Info.mberSttus}" />
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
							<td><input type="text" class="userManageInput" id="mberName" name="mberName" autocomplete="off" value="${Info.mberName}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">비밀번호</th>
							<td><input type="text" class="userManageInput" id="password" name="password" autocomplete="off" value="" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">전화번호</th>
							<td><input type="text" class="userManageInput" id="moblphonNo" name="moblphonNo" autocomplete="off" value="${Info.moblphonNo}" /></td>
						</tr>
						<tr>
							<th scope="col" width="120px">담당</th>
							<td>

							<select class="common_select" name="chargeId">
								<option value="" <c:if test="${Info.chargeId eq ''}">selected</c:if> >선택</option>
									<c:forEach var="charge" items="${ChargeList}" varStatus="status">
											<option value="${charge.seq}" <c:if test="${Info.chargeId eq charge.seq}">selected</c:if> >${charge.name}</option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">권한 ${Info.roleId}</th>
							<td>
								<select class="common_select" name="roleId">
									<option value="" <c:if test="${Info.roleId eq ''}">selected</c:if> >선택</option>
									<c:forEach var="role" items="${RoleList}" varStatus="status">
											<option value="${role.roleId}" <c:if test="${Info.roleId eq role.roleId}">selected</c:if> >${role.roleName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="col" width="120px">정/부</th>
							<td>
							<select class="common_select" name="mberRating">
									<option value="" <c:if test="${Info.mberRating eq ''}">selected</c:if> >선택</option>
									<option value="M"  <c:if test="${Info.mberRating eq 'M'}">selected</c:if>  >정</option>
									<option value="D"  <c:if test="${Info.mberRating eq 'D'}">selected</c:if>  >부</option>
							</select>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>

		<div class="border margin_l7">
			<c:choose>
 				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnCallAuthPage()">수정</button>
					<button type="button" class="userManageButton" onclick="fnDeleteData()">해지</button>
<!-- 					<button type="button" class="userManageButton" onclick="fnCallAuthPage()" name ="fnCallAuthPage">ARS</button> -->
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
<script type="text/javascript">

function fnCallAuthPage() {

//var authNumber = Math.floor(Math.random() * 98) +10 ;
 //document.write(authNumber);

	$.fun.ajax({
		type:'get',
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

function fnProcRegisterData(){
	$.fun.ajax({
		type:'post',
		data:$( "[name=FormComtngnrlmber]" ).serialize(),
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
	
	$("#grid").jqGrid(jqGridUtils.fn_JQGridOption({
		datatype:'json',
		url:'/jqGrid/init',
		colNames: [
		           	 '정/부'
		           	,'회원 SEQ'
		           	,'이름'
		           	,'휴대폰번호'
		           	,'담당'
		           	,'권한'
		           	,'상태'
		           ],
		colModel: [
				 {name:'mberRatingName', 	index:'mberRatingName',			width:5,	align:'center', search:false,  sortable:true}
				,{name:'nrlmberId', 			index:'nrlmberId',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'mberName', 		 	index:'mberName',				width:8,	align:'center', search:false,  sortable:true}
				,{name:'moblphonNo', 		index:'moblphonNo',				width:13,	align:'center', search:false,  sortable:true}
				,{name:'chargeName', 		index:'chargeName',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'roleName', 			index:'roleName',				width:10,	align:'center', search:false,  sortable:true}
				,{name:'mberSttusName', 	index:'mberSttusName',			width:10,	align:'center', search:false,  sortable:true}
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
  			$("[name=mberRatingName]").val(rowval.mberRatingName);
   			$("[name=nrlmberId]").val(rowval.nrlmberId);
   			$("[name=mberName]").val(rowval.mberName);
   			$("[name=moblphonNo]").val(rowval.moblphonNo);
   			$("[name=chargeName]").val(rowval.chargeName);
   			$("[name=roleName]").val(rowval.roleName);
   			$("[name=mberSttusName]").val(rowval.mberSttusName);
   			
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
	/*
	저기 ajax data랑  paramMap["refEqList"] 		= '${infoMap.seq}'; 랑 같은거야
	javascript 로 {} 표시는 object 를 의미하고
	infoMap.seq 는 뭐야 잘했네 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
	다했네  (취소!!!!!!!!!!!)
	내가 저번에 말한것처럼  서버에 값을 보낼땐 키 가 있어야대
	{refEqList : infoMap.seq} 이거면  refEqList 키의 값을 infoMap.seq(계정고유값) 으로 한다는건데.... 
	// 인터셉터를 보면  refEqList 라는 키는 넘어갔는데 `infoMap.seq}` 가 빈값이네.. 그러면 이 페이지를 호추할때  `RegisterContent.jsp` 할때 `infoMap.seq` 값이 노내주는 확인해야겠네
	zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz아 왜 infoMap이였지..? 저거  원래 저거 그냥 그대로였나보넹
	
			// /ctn/nrlmber/history/ListPagingData.do
			
	*/
	
	setTimeout(function(){
		var paramMap = {};
		// paramMap["refEqList"] 		= '${Info.seq}';
		paramMap["nrlmberId"] 		= '${Info.seq}';  // => 이게 {nrlmberId: ;47'} 이지 
		console.log("paramMap : " + paramMap);
		jqGridUtils.searchProc({
		     gridId:"#grid"
			,postData:paramMap
		   ,url:"/ctn/nrlmber/history/ListPagingData.do"
		});
	}, 500);
});


</script>
