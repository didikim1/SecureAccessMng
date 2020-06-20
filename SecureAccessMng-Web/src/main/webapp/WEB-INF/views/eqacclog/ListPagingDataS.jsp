<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">



$(function(){


	$("table thead th:eq(0)").data("sorter", false);
	$("table thead th:eq(1)").data("sorter", false);
	$("table thead th:eq(2)").data("sorter", false);
	$("table thead th:eq(3)").data("sorter", false);
	$("table thead th:eq(4)").data("sorter", false);
	$("table thead th:eq(5)").data("sorter", false);
	$("table thead th:eq(6)").data("sorter", false);
	$("table thead th:eq(7)").data("sorter", false);
	$("table thead th:eq(8)").data("sorter", false);
	$("table thead th:eq(9)").data("sorter", false);
	$("table thead th:eq(10)").data("sorter", false);

	$("#myTable").tablesorter({
		usNumberFormat : false,
		sortReset      : true,
		sortRestart    : true
	});


	$("[name=idcSeq]").change(function(){
		var idcSeq = $("select[name=idcSeq]").val();



		$.fun.ajax({
			type:'get',
			dataType:"JSON",
			url:"/eqacclog/EqList/ListData.do?idcSeq="+idcSeq,
			success:function(data){

				$("select[name=eQListSeq]").empty();
				$('#eQListSeq').empty();
				$('#eQListSeq').children('option').remove();
				$("select[name='eQListSeq'] option").remove();
				$("#eQListSeq").find("option").remove();


				var option = $("<option value=''>선택</option>");
	 	        $('select[name=eQListSeq]').append(option);

	 	       for(var i=0;i < data.result.list.length; i++){
	 	    	  var option = $("<option value="+data.result.list[i].seq+">"+data.result.list[i].name+"</option>");
	 	            $('select[name=eQListSeq]').append(option);

	 	        }
			}
		});

	});

	 $.datepicker.setDefaults({
	        dateFormat: 'yy-mm-dd',
	        firstDay: 0,
	        isRTL: false,
	        changeMonth : true,
	        changeYear : true,
	        showOtherMonths: true,
	        selectOtherMonths: true,
	        showAnim: "fadeIn"
	    });

		$("#sDate").datepicker({
			onClose: function( selectedDate ) {
				$("#eDate").datepicker( "option", "minDate", selectedDate );
		    	var date = $(this).datepicker('getDate');
		   		date.setDate(date.getDate() + 30);
		    	$('#eDate').datepicker("option", "maxDate", date);
			}
		});

		$('#eDate').datepicker({
	        onClose: function( selectedDate ) {
	            $("#sDate").datepicker( "option", "maxDate", selectedDate );
	        }
		});

		$('#Date').datepicker();

});


function fnProcSearch(){
	$("[name=FormSearchEqAcclog]").submit();
}

</script>