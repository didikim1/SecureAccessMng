var jqGridUtils={
  msgBox:{
    "noSeleteRow":"선택된 게시물이 없습니다."
  },
  "searchProc":function(pObject){
    var gridId=pObject.gridId;
    $(gridId).setGridParam({
//       page: $(gridId + "_pageIndex").val()
       page: "0"
      ,postData: pObject.postData
    });
    $(gridId).setGridParam({url:pObject.url}).trigger("reloadGrid");
  },
  "searchProcNopage":function(pObject){
	  var gridId=pObject.gridId;
	  $(gridId).setGridParam({
		  page: $(gridId + "_pageIndex").val(),
		  postData: pObject.postData
	  });
	  $(gridId).setGridParam({url:pObject.url}).trigger("reloadGrid");
  },
  "searchProcAdmin":function(pObject){
	    var gridId=pObject.gridId;
	    $(gridId).setGridParam({
	      page: $(gridId + "_pageIndex").val(),
	      postData: pObject.postData
	    });
	    $(gridId).setGridParam({url:pObject.url});
	    $(gridId).empty();
	  },
  //선택된 그리드의 컬럼값 가져오기.
  "getRowCelValue":function(gridId, columnName){
    var myGrid = $(gridId),
    selectedRowId = myGrid.jqGrid ('getGridParam', 'selrow'),
    cellValue = myGrid.jqGrid ('getCell', selectedRowId, columnName);
    return cellValue;
  },
  "getRowData":function(gridId){
    var myGrid = $(gridId),
    selectedRowId = myGrid.jqGrid ('getGridParam', 'selrow'),
    rowObject = myGrid.getRowData(selectedRowId);
    return rowObject;
  },
  "getMultiSelectRowData":function(gridId){
    var myGrid = $(gridId);
    var _selectRows = myGrid.jqGrid('getGridParam', 'selarrrow');
    var arrObj = new Array();

    for (var idx = 0; idx < _selectRows.length; idx++) {
      var girdRowData = myGrid.getRowData(_selectRows[idx]);
      arrObj.push(girdRowData);
     }
     return arrObj;
  },
  "getGridSortInfo":function(gridId){
    var myGrid = $(gridId);
    var gridSortInfo={};
    gridSortInfo["sidx"]=myGrid.jqGrid ('getGridParam', 'sortname');
    gridSortInfo["sord"]=myGrid.jqGrid ('getGridParam', 'sortorder');
    return gridSortInfo;
  },
  //jqGrid json 형식을 재 정의
  "JsonReader":{
    repeatitems:false,
    id:"",
    root:function(obj){return obj.list;},
    page:function(obj){return obj.paginationInfo.currentPageNo;},
    total:function(obj){return obj.paginationInfo.totalPageCount;},
    records:function(obj){return obj.paginationInfo.totalRecordCount;}
  },
  "NoPageJsonReader":{
    repeatitems:false,
    id:"",
    root:function(obj){return obj.list;},
    page:function(obj){
      //obj.paginationInfo.currentPageNo
      return 0;
    },
    total:function(obj){
      //obj.paginationInfo.totalPageCount
      return 1;
    },
    records:function(obj){
      //obj.paginationInfo.totalRecordCount
      return 0;
    }
  },
  //jQgrid 한페이지에 보여줄 행 갯수 선택 항목 재 정의
  "RowList":[100,200,300]
  ,"getCheckedValues":function(gridId, object, not, columnName){

    var arr=new Array();
    var myGrid = $(gridId);
    var __$object = $(object);

    var targetObj=angryFrUtils.isNotNull(not)?__$object.not(":eq("+not+")"):__$object;

    targetObj.filter(":checked").each(function(){
      var rowId = $(this).parents("tr").attr("id");
      var cellValue = myGrid.jqGrid ('getCell', rowId, columnName);
      arr.push(cellValue);
    })
    return arr.toString();
  },"getCheckedSize":function(gridId, object, not){
    var __$object = $(object);
    var targetObj=angryFrUtils.isNotNull(not)?__$object.not(":eq("+not+")"):__$object;
    return targetObj.filter(":checked").length;
  },
  "fn_JQGridOption":function(options){
		var jqGridDefault={
				datatype:'json',
				url:options.url ? options.url : "",
			    colNames:options.colNames,
			    colModel:options.colModel,
				autowidth:options.autowidth ? options.autowidth : true,
			  	scrollerbar:true,
			  	search:true,
//			  	multiselect: false,
			  	height: options.height ? options.height : "500px",
			  	mtype:  options.mtype  ? options.mtype  : "post",
			  	rowNum: options.rowNum ? options.rowNum  :  20,
			  	pager:  options.pager  ? options.pager  :  "#page1",
			  	sortname: options.sortname ? options.sortname :'date_Add',
			  	sortorder: options.sortorder  ? options.sortorder  :  "desc",
//			  	recordpos: options.recordpos  ? options.recordpos  : 'left',
//			  	viewrecords:true,
			  	jsonReader:{
			  		  	repeatitems:false,
			  			id:"",
			  			root:function(obj){
			  				console.log(obj);
			  				if ( obj.result == null )return;
			  				return obj.result.list;
			  			},
			  			page:function(obj){
			  				if ( obj.result == null )return;
			  				return obj.result.paginationInfo.currentPageNo;
			  				},
			  			total:function(obj){
			  				if ( obj.result == null )return;
			  			  	return obj.result.paginationInfo.totalPageCount;
			  			},
			  			records:function(obj){
			  			  if ( obj.result == null )return;
			  			  $("#totalRecordCount").text(fn_DisplayComma(obj.result.paginationInfo.totalRecordCount));
			  			  return obj.result.paginationInfo.totalRecordCount;
			  			}
			  	},
			  	onSelectRow: options.onSelectRow  ? options.onSelectRow  : function(id){},
				gridComplete: options.gridComplete  ? options.gridComplete  : function(){},
				loadError: options.loadError  ? options.loadError  : function(xhr,st,err){},
				afterRedraw: options.afterRedraw  ? options.afterRedraw  : function () {},
				ondblClickRow: options.ondblClickRow  ? options.ondblClickRow  : function () {},
			    beforeSelectRow : function(rowid, event) { return true; }
			}
		return jqGridDefault;
	},
    "fn_JQGridOption2":function(options){
		var jqGridDefault={
				datatype:'json',
				url:options.url ? options.url : "",
			    colNames:options.colNames,
			    colModel:options.colModel,
				autowidth:options.autowidth ? options.autowidth : true,
			  	scrollerbar:true,
			  	search:true,
//			  	multiselect: false,
			  	height: options.height ? options.height : "500px",
			  	mtype:  options.mtype  ? options.mtype  : "post",
			  	rowNum: options.rowNum ? options.rowNum  :  20,
			  	pager:  options.pager  ? options.pager  :  "#page1",
			  	sortname: options.sortname ? options.sortname :'date_Add',
			  	sortorder: options.sortorder  ? options.sortorder  :  "desc",
//			  	recordpos: options.recordpos  ? options.recordpos  : 'left',
//			  	viewrecords:true,
			  	jsonReader:{
			  		  	repeatitems:false,
			  			id:"",
			  			root:function(obj){
			  				if ( obj.result.list == null )return;
			  				return obj.list;
			  			},
			  			page:function(obj){
			  				if ( obj.result.list == null )return;
			  				return obj.paginationInfo.currentPageNo;
			  				},
			  			total:function(obj){
			  				if ( obj.result.list == null )return;
			  			  	return obj.paginationInfo.totalPageCount;
			  			},
			  			records:function(obj){
			  				if ( obj.result.list == null )return;
			  			  return obj.paginationInfo.totalRecordCount;
			  			}
			  	},
			  	onSelectRow: options.onSelectRow  ? options.onSelectRow  : function(id){},
				gridComplete: options.gridComplete  ? options.gridComplete  : function(){},
				loadError: options.loadError  ? options.loadError  : function(xhr,st,err){},
				afterRedraw: options.afterRedraw  ? options.afterRedraw  : function () {},
				ondblClickRow: options.ondblClickRow  ? options.ondblClickRow  : function () {},
			    beforeSelectRow : function(rowid, event) { return true; }
			}
		return jqGridDefault;
	},
    "fn_JQGridOptionNoPageing":function(options){
		var jqGridDefault={
				datatype:'json',
				url:options.url ? options.url : "",
			    colNames:options.colNames,
			    colModel:options.colModel,
				autowidth:options.autowidth ? options.autowidth : true,
			  	scrollerbar:true,
			  	search:true,
			  	height: options.height ? options.height : "500px",
			  	mtype:  options.mtype  ? options.mtype  : "post",
			  	rowNum: options.rowNum ? options.rowNum  :  20,
			  	sortname: options.sortname ? options.sortname :'date_Add',
			  	sortorder: options.sortorder  ? options.sortorder  :  "desc",
			  	viewrecords:true,
			  			jsonReader:{
				  		  	repeatitems:false,
				  			id:"",
				  			root:function(obj){
				  				console.log(obj);
				  				return obj.result.list;
				  			},
				  			page:function(obj){
				  				},
				  			total:function(obj){
				  			},
				  			records:function(obj){
				  			}
				},
			  	onSelectRow: options.onSelectRow  ? options.onSelectRow  : function(id){},
				gridComplete: options.gridComplete  ? options.gridComplete  : function(){},
				loadError: options.loadError  ? options.loadError  : function(xhr,st,err){},
				afterRedraw: options.afterRedraw  ? options.afterRedraw  : function () {},
				ondblClickRow: options.ondblClickRow  ? options.ondblClickRow  : function () {},
			    beforeSelectRow : function(rowid, event) { return true; }
			}
		return jqGridDefault;
	}
};
