<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">
			<form name="FormEqList">
<%-- 				<input type="hidden" name="mberId" value="${Info.mberId}" /> --%>
				<div align="center" >
					<table class="htable">

						<!-- 자산명 -->
						<tr>
							<th scope="col" width="120px">자산명</th>
							<td><input type="text" class="userManageInput" id="propertyName" name="propertyName" autocomplete="off" value="${Info.propertyName}" /></td>
						</tr>
						<!-- //자산명 -->
						
						<!-- 자산번호 -->
						<tr>
							<th scope="col" width="120px">자산번호</th>
							<td><input type="text" class="userManageInput" id="propertyNum" name="propertyNum" autocomplete="off" value="${Info.propertyNum}" /></td>
						</tr>
						<!-- //자산번호 -->
						
						<!-- 사용용도 -->
						<tr>
							<th scope="col" width="120px">사용용도</th>
							<td><input type="text" class="userManageInput" id="svUsage" name="svUsage" autocomplete="off" value="${Info.svUsage}" /></td>
						</tr>
						<!-- //사용용도 -->

						<!-- 서버명 -->
						<tr>
							<th scope="col" width="120px">서버명</th>
							<td><input type="text" class="userManageInput" id="svName" name="svName" autocomplete="off" value="${Info.svName}" /></td>
						</tr>
						<!-- //서버명 -->

						<!-- 서버기종 -->
						<tr>
							<th scope="col" width="120px">서버기종</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('MODEL', 'svModel')" id="svModelName" name="svModelName" autocomplete="off" value="${Info.svModelName}" readonly="readonly" />
								<input type="hidden" id="svModel" name="svModel" autocomplete="off" value="${Info.svModelName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //서버기종 -->

						<!-- 제조사 -->
						<tr>
							<th scope="col" width="120px">제조사</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('MANUFACTURE', 'svManufacture')" id="svManufactureName" name="svManufactureName" autocomplete="off" value="${Info.svManufactureName}" readonly="readonly" />
								<input type="hidden" id="svManufacture" name="svManufacture" autocomplete="off" value="${Info.svManufactureName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //제조사 -->

						<!-- 입고일 -->
						<tr>
							<th scope="col" width="120px">입고일</th>
							<td><input type="text" class="userManageInput2" id="receivingPnttm" name="receivingPnttm" autocomplete="off" value="${paramMap.receivingPnttm}" /></td>
						</tr>
						<!-- //입고일 -->

						<!-- 스펙 CPU-->
						<tr>
							<th scope="col" width="120px">스펙 CPU</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('SEPEC_CPU', 'svSepecCpu')" id="svSepecCpuName" name="svSepecCpuName" autocomplete="off" value="${Info.svSepecCpuName}" readonly="readonly" />
								<input type="hidden" id="svSepecCpu" name="svSepecCpu" autocomplete="off" value="${Info.svSepecCpuName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //스펙 CPU -->

						<!-- 스펙 MM -->
						<tr>
							<th scope="col" width="120px">스펙 MM</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('SEPEC_MM', 'svSepecMm')" id="svSepecMmName" name="svSepecMmName" autocomplete="off" value="${Info.svSepecMmName}" readonly="readonly" />
								<input type="hidden" id="svSepecMm" name="svSepecMm" autocomplete="off" value="${Info.svSepecMmName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //스펙 MM -->

						<!-- 스펙 DISK -->
						<tr>
							<th scope="col" width="120px">스펙 DISK</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('SEPEC_DISK', 'svSepecDisk')" id="svSepecDiskName" name="svSepecDiskName" autocomplete="off" value="${Info.svSepecDiskName}" readonly="readonly" />
								<input type="hidden" id="svSepecDisk" name="svSepecDisk" autocomplete="off" value="${Info.svSepecDiskName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //스펙 DISK -->

						<!-- 스펙 NIC -->
						<tr>
							<th scope="col" width="120px">스펙 NIC</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('SEPEC_NIC', 'svSepecNic')" id="svSepecNicName" name="svSepecNicName" autocomplete="off" value="${Info.svSepecNicName}" readonly="readonly" />
								<input type="hidden" id="svSepecNic" name="svSepecNic" autocomplete="off" value="${Info.svSepecNicName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //스펙 NIC -->


						<!-- IP #1 -->
						<tr>
							<th scope="col" width="120px">IP #1</th>
							<td><input type="text" class="userManageInput" id="svIp1" name="svIp1" autocomplete="off" value="${Info.svIp1}" /></td>
						</tr>
						<!-- //IP #1 -->

						<!-- IP #2 -->
						<tr>
							<th scope="col" width="120px">IP #2</th>
							<td><input type="text" class="userManageInput" id="svIp2" name="svIp2" autocomplete="off" value="${Info.svIp2}" /></td>
						</tr>
						<!-- //IP #2 -->

						<!-- IP #3 -->
						<tr>
							<th scope="col" width="120px">IP #3</th>
							<td><input type="text" class="userManageInput" id="svIp3" name="svIp3" autocomplete="off" value="${Info.svIp3}" /></td>
						</tr>
						<!-- //IP #3 -->

						<!-- IP #4 -->
						<tr>
							<th scope="col" width="120px">IP #4</th>
							<td><input type="text" class="userManageInput" id="svIp4" name="svIp4" autocomplete="off" value="${Info.svIp4}" /></td>
						</tr>
						<!-- //IP #4 -->

						<!-- port -->
						<tr>
							<th scope="col" width="120px">PORT</th>
							<td><input type="text" class="userManageInput" id="svPort" name="svPort" autocomplete="off" value="${Info.svPort}" /></td>
						</tr>
						<!-- //port -->

						<!-- OSType -->
						<tr>
							<th scope="col" width="120px">OSType</th>
							<td>
								<input type="text" style="cursor: pointer;" class="userManageInput" onclick="fnOpenRegisterEquipmentPage('OS_TYPE', 'svOsType')" id="svOsTypeName" name="svOsTypeName" autocomplete="off" value="${Info.svOsTypeName}" readonly="readonly" />
								<input type="hidden" id="svOsType" name="svOsType" autocomplete="off" value="${Info.svOsTypeName}" readonly="readonly" />
							</td>
						</tr>
						<!-- //OSType -->
						
						<!-- 서버상태 -->
						<tr>
							<th scope="col" width="120px">서버상태</th>
							<td><input type="text" class="userManageInput" id="svSttus" name="svSttus" autocomplete="off" value="${Info.svSttus}" /></td>
						</tr>
						<!-- //서버상태 -->

						<!-- IDC -->
						<tr>
							<th scope="col" width="120px">IDC</th>
							<td>
								<select class="userManageInput" name="idcSeq">
									<option value="">선택</option>
									<c:forEach items="${IdcInfoList}" var="idcInfo">
										<option value="${idcInfo.idcSeq}">${idcInfo.idcName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<!-- //IDC -->

						<!-- 상면 -->
						<tr>
							<th scope="col" width="120px">상면</th>
							<td><input type="text" class="userManageInput" id="svPosition" name="svPosition" autocomplete="off" value="${Info.svPosition}" /></td>
						</tr>
						<!-- //상면 -->

						<!-- 관리자 -->
						<tr>
							<th scope="col" width="120px">관리자</th>
							<td>
								<select class="userManageInput" name="entrprsmberSeq">
									<option value="">선택</option>
									<c:forEach items="${EntrprsmberList}" var="entrprsmber">
										<option value="${entrprsmber.seq}">${entrprsmber.mberName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<!-- //관리자 -->

					</table>
				</div>
			</form>
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

			<button type="button" class="userManageButton" onclick="fnProcRegisterData()">등록하기</button>
			<button type="button" class="userManageButton" onclick="fnClose()">닫기</button>
		</div>

	</div>
</div>
<script type="text/javascript">
function fnProcRegisterData(){
	
	// 관리자, IDC 선택하지 않은 경우 체크.
	if(!$("[name=idcSeq]").val())
	{
		$.fun.alert({content:"IDC를 선택하세요."});
		$("[name=idcSeq]").focus();
		return;
	}
	if(!$("[name=entrprsmberSeq]").val())
	{
		$.fun.alert({content:"관리자를 선택하세요."});
		$("[name=entrprsmberSeq]").focus();
		return;
	}
	
	// 자산 등록
	$.fun.ajax({
		type:'get',
		url:"/eqlist/RegisterData.do",
		data:$("form[name=FormEqList]").serialize(),
		success:function(data){
			$.fun.alert({content:"정상 처리되었습니다.", action:function(){
				location.reload();
			}});
// 			$.fun.layout({
// 				id:"induacaAdd",
// 				"content":data,
// 				"title":"자산 등록",
// 				"width":475,
// 				"buttons":{}
// 			});
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
