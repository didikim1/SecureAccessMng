<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${_url}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${_url}/js/common/common.js"></script>


<!-- 헤더 메뉴 -->
<link rel="stylesheet" type="text/css" href="${_url}/lib/smoothness/jquery-ui-1.10.3.custom.css?ver=${Ver}" />
<link rel="stylesheet" type="text/css" href="${_url}/lib/smoothness/jquery.custom.css?ver=${Ver}" />

<script type="text/javascript" src="${_url}/js/common/jquery/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${_url}/js/common/jquery/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${_url}/js/common/jquery/jquery.function.js"></script>
<script type="text/javascript" src="${_url}/js/common/jquery/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript" src="${_url}/js/common/common.js"></script>
<script type="text/javascript" src="${_url}/js/common/uicommon.js"></script>
<script type="text/javascript" src="${_url}/js/common/jquery/jquery.form.js"></script>

<script type="text/javascript">
var common={
		init:function(obj)
		{
			return obj
		}
	}
	$(document).ready(function(){
		common.init($("BODY"));
	});
</script>