package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.act;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.charge.biz.ChargeBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.role.biz.RoleBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.excel.ExcelWrite;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

import jxl.write.WriteException;

@Controller
@RequestMapping("/ctn/nrlmber")
public class NrlmberAct
{
	final String pagePrefix = "ctn/nrlmber";

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(NrlmberAct.class.getName());

	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz")
	NrlmberBiz mBiz;
	
	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.role.biz.RoleBiz")
	RoleBiz mRoleBiz;
	
	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.charge.biz.ChargeBiz")
	ChargeBiz mChargeBiz;

	@Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.excel.ExcelWrite")
    ExcelWrite mExcelWrite;
	
	 @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz")
	 NrlmberBiz mNrlmberBiz;

	@RequestMapping(value = { "/ListPagingData.do" })
	public String ListPagingDatas(Model model)
	{
			MyMap           paramMap    		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            BasicBean       resultBean  		= null;
    		BasicBean       roleList	 		= null;
    		BasicBean       chargeList  		= null;

            resultBean   = mBiz.ListPagingData( paramMap );
    		roleList  	 = mRoleBiz.ListPagingData(new MyMap());
    		chargeList	 = mChargeBiz.ListPagingData(new MyMap());

            model.addAttribute("paramMap",      paramMap);
            model.addAttribute("Data",          resultBean);
   		 	model.addAttribute("RoleList",      roleList.getList());
   		 	model.addAttribute("ChargeList",    chargeList.getList());

	    return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
			MyMap                 paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            MyCamelMap     		  resultMap       = new MyCamelMap();

            resultMap = mBiz.SelectOneData(paramMap);

            model.addAttribute("paramMap",      paramMap);
            model.addAttribute("Data",          resultMap);

            return null;
	}

	@RequestMapping(value = { "/ProcSelectOneData.do" })
        public String ProcSelectOneData(Model model)
        {
            MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            MyCamelMap      resultMap       = new MyCamelMap();

            resultMap = mBiz.SelectOneData(paramMap);

            model.addAttribute("paramMap",      paramMap);
            model.addAttribute("Data",          resultMap);

            return null;
        }

	@RequestMapping(value = { "/RegisterData.do" })
	public String RegisterData(Model model)
	{
		MyMap paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap resultMap       = null;
		
		BasicBean roleList	  = null;
		BasicBean chargeList  = null;
		
		resultMap	 = mBiz.SelectOneData(paramMap);
		roleList  	 = mRoleBiz.ListPagingData(new MyMap());
		chargeList	 = mChargeBiz.ListPagingData(new MyMap());
		
		 model.addAttribute("paramMap",      paramMap);
		 model.addAttribute("Info",          resultMap);
		 model.addAttribute("RoleList",      roleList.getList());
		 model.addAttribute("ChargeList",    chargeList.getList());
		
		return pagePrefix + "/RegisterData";
	}

	@RequestMapping(value = { "/RegisterContent.do" })
	public String RegisterContent(Model model)
	{
		MyMap paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap resultMap       = null;
		
		BasicBean roleList	  = null;
		BasicBean chargeList  = null;
		
		resultMap  = mBiz.SelectOneData(paramMap);
		roleList   = mRoleBiz.ListPagingData(new MyMap());
		chargeList = mChargeBiz.ListPagingData(new MyMap());
		
		model.addAttribute("paramMap",      paramMap);
		model.addAttribute("Info",          resultMap);
		model.addAttribute("RoleList",      roleList.getList());
		model.addAttribute("ChargeList",    chargeList.getList());

		return pagePrefix + "/RegisterContent";
	}

	@RequestMapping(value = { "/ProcRegisterData.do" })
	public @ResponseBody ResultMessage ProcRegisterData(Model model)
	{
	    MyMap              paramMap                        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	    MyCamelMap         resultMap                       = new MyCamelMap();
	    
	    String             resultCode                      = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
	    int                resultRegisterDataCount         = 0;

	    	
        if(paramMap.getInt("seq", 0) > 0 )
        {
                resultCode      = ResultCode.RESULT_OK;
                resultRegisterDataCount = mBiz.ModifyData(paramMap);            
        }
        else
        {
        	resultRegisterDataCount = mBiz.RegisterData( paramMap );
        	if( resultRegisterDataCount > 0 )
        	{
        		resultCode = ResultCode.RESULT_OK;
        	}
        }

	    return new ResultMessage(resultCode, null);
	}
	

	@RequestMapping(value = { "/ModifyData.do" })
	public String ModifyData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		 model.addAttribute("paramMap",      paramMap);

		return pagePrefix + "/ModifyData";
	}
	
	@RequestMapping(value = { "/ProcModifyData.do" })
	public ResultMessage ProcModifyData(Model model)
	{
	    MyMap              paramMap                = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	    MyCamelMap         resultMap               = new MyCamelMap();
            int            resultRegisterDataCount = 0;

	    resultRegisterDataCount = mBiz.ModifyData( paramMap );

	    return new ResultMessage(ResultCode.RESULT_OK, null);
	}

	@RequestMapping(value = { "/DeleteData.do" })
	public String DeleteData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		 model.addAttribute("paramMap",      paramMap);

		return pagePrefix + "/ModifyData";
	}

	@RequestMapping(value = { "/ProcDeleteData.do" })
	public ResultMessage ProcDeleteData(Model model)
	{
	    MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	    MyCamelMap        		resultMap                     = new MyCamelMap();
            int                 resultDeleteDataCount         = 0;
            String              resultCode                    = ResultCode.RESULT_EMPTY;

            resultDeleteDataCount = mBiz.DeleteData( paramMap );

            if ( resultDeleteDataCount > 0 )
            {
                    resultCode = ResultCode.RESULT_OK;
            }

            return new ResultMessage(resultCode, resultMap);
	}
		
	// 엑셀 다운로드
	@RequestMapping(value = { "/ListExcelData.do" })
	public void ListExcelData(HttpServletRequest request, HttpServletResponse response, Model model)throws WriteException, IOException
	{
		List<MyCamelMap> resultS03Excel    = null;
        MyMap            paramMap          = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		
        BasicBean       resultBean  		= null;
        String      strFileName      		= "계정상세자료 ("+paramMap.getStr("sDate")+"~"+paramMap.getStr("eDate")+").xlsx";
		String [] arrTitle           			= new String[]{"처리자", 			"소유자",					"계정",				"전화번호",				"담당",				"권한",			"담당책임",			"상태"};
		String [] arrExcelColum         = new String[] {"uniqId",		"mberName",	"emailAddress",	"moblphonNo",		"chargeId",	"roleId",		"mberRating",	"mberSttus"};
		
		paramMap.put("rows",1000000);
		resultBean = mBiz.ListPagingData( paramMap );
		
		// 엑셀 데이터 나오는거 때문에 그러는거지?네네
		// public void selectExcelList(HttpServletResponse response, String[] title, String[] cols, List<MyCamelMap> list, String fileName) {
		// mExcelWrite 이 클래스에서 selectExcelList 이것만 사용하면될것같아
		// tcp 안에 http가 있는거고 tcp 소켓은 인아웃에 스트림을 있찌
		// HttpServletRequest 요청스트림
		// HttpServletResponse 응답스트림
		// 응답스트림에 엑셀을 내려주는건데 
		// selectExcelList 오면 `list` 리스트만 실어주면될듯해
		//
		// BasicBean       resultBean  		= null;
		// resultBean   = mBiz.ListPagingData( paramMap );
		// 일단 목록 페이지에서 사용하고있는  list항목을 이용해보자
		// 일단 `void` 인 이유는 다른곳은 다른곳은 페이지를 return 하거나 @Responsbody를 써서 바로 string을 리턴하지만(요청자한테바로)
		// 여긴 mExcelWrite 함수한테 `response` 응답 스트림을 넘겨줌
		// 근데 엑셀은 10개만 할게 아니지?ㅎㅎ
		// 잘바바
		// 11 rows가 엑셀에 나왔고 
		// 엑셀다운로들 누를때 limit 0 ~ 1000000 까지 
		// 그냥 페이지은 걍 10 씩만 나오는거고..ㅎ
		// ㅇㅋㅇㅋㅋㅇㅋ???네!!!
//		String [] arrvalue         			= new String[] {"${info.uniqId}",		"mberName",	"emailAddress",	"moblphonNo",		"chargeId",	"roleId",		"mberRating",	"mberSttus"};
		
//		resultS03Excel = mExcelWrite.ListData( paramMap );
		
		 mExcelWrite.selectExcelList(response, arrTitle, arrExcelColum, resultBean.getList(),  strFileName);
		
	}
	
    @RequestMapping(value ={ "/ListData.do" })
    public @ResponseBody ResultMessage ListData(Model model)
    {
        MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        MyMap nrlmListParamMap = new MyMap();

        nrlmListParamMap.put("rows",     10000);
        nrlmListParamMap.put("refEqIdc", paramMap.getInt("idcSeq", 0));


        return new ResultMessage(ResultCode.RESULT_OK, mNrlmberBiz.ListPagingData(nrlmListParamMap) );
    }
}
