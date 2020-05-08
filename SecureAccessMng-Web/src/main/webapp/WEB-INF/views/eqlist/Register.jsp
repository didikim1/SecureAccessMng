<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">
			<form name="FormComtngnrlmber">
				<input type="hidden" name="mberId" value="${Info.mberId}" />
				<div align="center" >
					<table class="htable">

						<!-- 자산명 -->
						<tr>
							<th scope="col" width="120px">자산명</th>
							<td><input type="text" class="userManageInput" id="propertyName" name="propertyName" autocomplete="off" value="${Info.propertyName}" /></td>
						</tr>
						<!-- //자산명 -->

						<!-- 서버기종 -->
						<tr>
							<th scope="col" width="120px">서버기종</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('SEPEC_CPU', 'svModel')" id="svModelName" name="svModelName" autocomplete="off" value="${Info.svModelName}" readonly="readonly" />
								<input type="hidden" id="svModel" name="svModel" autocomplete="off" value="${Info.svModelName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //서버기종 -->

						<!-- 제조사 -->
						<tr>
							<th scope="col" width="120px">제조사</th>
							<td>
								<select class="userManageSelect2" name="dpamentId">
									<option>선택</option>
								</select>
								<button type="button" style="height: 30px" class="common_button2" ><i class="fa" aria-hidden="true"></i>&nbsp;추가</button>
							</td>
						</tr>
						<!-- //제조사 -->

						<!-- 입고일 -->
						<tr>
							<th scope="col" width="120px">입고일</th>
							<td><input type="text" class="userManageInput" id="propertyName" name="propertyName" autocomplete="off" value="${Info.propertyName}" /></td>
						</tr>
						<!-- //입고일 -->

						<!-- 스펙 CPU-->
						<tr>
							<th scope="col" width="120px">스펙 CPU</th>
							<td>
								<select class="userManageSelect2" name="dpamentId">
									<option>선택</option>
								</select>
								<button type="button" style="height: 30px" class="common_button2" ><i class="fa" aria-hidden="true"></i>&nbsp;추가</button>
							</td>
						</tr>
						<!-- //스펙 CPU -->

						<!-- 스펙 MM -->
						<tr>
							<th scope="col" width="120px">스펙 MM</th>
							<td>
								<select class="userManageSelect2" name="dpamentId">
									<option>선택</option>
								</select>
								<button type="button" style="height: 30px" class="common_button2" ><i class="fa" aria-hidden="true"></i>&nbsp;추가</button>
							</td>
						</tr>
						<!-- //스펙 MM -->

						<!-- 스펙 DISK -->
						<tr>
							<th scope="col" width="120px">스펙 DISK</th>
							<td>
								<select class="userManageSelect2" name="dpamentId">
									<option>선택</option>
								</select>
								<button type="button" style="height: 30px" class="common_button2" ><i class="fa" aria-hidden="true"></i>&nbsp;추가</button>
							</td>
						</tr>
						<!-- //스펙 DISK -->

						<!-- 스펙 NIC -->
						<tr>
							<th scope="col" width="120px">스펙 NIC</th>
							<td>
								<select class="userManageSelect2" name="dpamentId">
									<option>선택</option>
								</select>
								<button type="button" style="height: 30px" class="common_button2" ><i class="fa" aria-hidden="true"></i>&nbsp;추가</button>
							</td>
						</tr>
						<!-- //스펙 NIC -->


						<!-- IP #1 -->
						<tr>
							<th scope="col" width="120px">IP #1</th>
							<td><input type="text" class="userManageInput" id="propertyName" name="propertyName" autocomplete="off" value="${Info.propertyName}" /></td>
						</tr>
						<!-- //IP #1 -->

						<!-- IP #2 -->
						<tr>
							<th scope="col" width="120px">IP #2</th>
							<td><input type="text" class="userManageInput" id="propertyName" name="propertyName" autocomplete="off" value="${Info.propertyName}" /></td>
						</tr>
						<!-- //IP #2 -->

						<!-- IP #3 -->
						<tr>
							<th scope="col" width="120px">IP #3</th>
							<td><input type="text" class="userManageInput" id="propertyName" name="propertyName" autocomplete="off" value="${Info.propertyName}" /></td>
						</tr>
						<!-- //IP #3 -->

						<!-- IP #4 -->
						<tr>
							<th scope="col" width="120px">IP #4</th>
							<td><input type="text" class="userManageInput" id="propertyName" name="propertyName" autocomplete="off" value="${Info.propertyName}" /></td>
						</tr>
						<!-- //IP #4 -->

						<!-- OSType -->
						<tr>
							<th scope="col" width="120px">OSType</th>
							<td>
								<select class="userManageSelect2" name="dpamentId">
									<option>선택</option>
								</select>
								<button type="button" style="height: 30px" class="common_button2" ><i class="fa" aria-hidden="true"></i>&nbsp;추가</button>
							</td>
						</tr>
						<!-- //OSType -->

						<!-- 관리자 -->
						<tr>
							<th scope="col" width="120px">관리자</th>
							<td>
								<select class="userManageInput" name="dpamentId">
									<option>선택</option>
								</select>
							</td>
						</tr>
						<!-- //관리자 -->

					</table>
				</div>
			</form>
		</div>

		<div class="border margin_l7">
			<c:choose>
				<c:when test="${Info.mberId != '' || Info.mberId ne null}">
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">수정하기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록하기</button>
				</c:otherwise>
			</c:choose>
			<button type="button" class="userManageButton" onclick="fnClose()">닫기</button>
		</div>

	</div>
</div>
<script type="text/javascript">
function fnProcRegisterData(){

	$.fun.ajax({
		type:'get',
		url:"/eqlist/Register.do?svId="+svId,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"계정생성",
				"width":475,
				"buttons":{}
			});
		}
	});

}

function fnOpenRegisterEquipmentPage(title,inputName){
	$.fun.ajax({
		type:'get',
		url:"/ctn/code/PopupListData.do?title="+title+"&type=B&inputName="+inputName,
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

function fnClose(){
	$("#induacaAdd").dialog('destroy').remove();
}


</script>
