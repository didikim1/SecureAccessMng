package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.act;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz;
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
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;

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

	 @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz")
	 CommonBiz mCommonBiz;

	@RequestMapping(value = { "/ListPagingData.do" })
	public String ListPagingDatas(Model model)
	{
			MyMap           paramMap    		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            BasicBean       resultBean  		= null;
    		BasicBean       roleList	 		= null;
    		BasicBean       chargeList  		= null;

            if ("".equals(paramMap.getStr("sDate", ""))) {
                paramMap.put("sDate", FrameworkUtils.aGoMonth(-36, "yyyy-MM-dd"));
                paramMap.put("eDate", FrameworkUtils.aGoDate(0,   "yyyy-MM-dd"));
            }

            resultBean   = mBiz.ListPagingData( paramMap );
    		roleList  	 = mRoleBiz.ListPagingData(new MyMap());
    		chargeList	 = mChargeBiz.ListPagingData(new MyMap());


    		for (MyCamelMap info : resultBean.getList())
                {
                       System.out.println(info);
                }

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

		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);

		model.addAttribute("paramMap",      paramMap);
		model.addAttribute("Info",          resultMap);
		model.addAttribute("RoleList",      roleList.getList());
		model.addAttribute("ChargeList",    chargeList.getList());

		return pagePrefix + "/RegisterContent";
	}


	/**
	 * id??????
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/uniqIdChk.do" })
	public @ResponseBody ResultMessage uniqIdChk(Model model)
	{
	            MyMap              paramMap                        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	            MyCamelMap         resultMap                       = new MyCamelMap();

	            String             resultCode                      = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
	            int                resultRegisterDataCount         = 0;

	            // paramMap ??????
	            //  uniqId ???????????? ???????????? ????????? id??? ????????????.
	            resultMap = mBiz.SelectOneData(paramMap);

	            if( resultMap == null ){
	                resultCode = ResultCode.RESULT_NOT_FOUND;
	            } else {
	                resultCode = ResultCode.RESULT_BAD_REQUEST;
	            }

	            return new ResultMessage(resultCode, null);
	}

	/**
	 * ???????????? ??????
	 * @param model
	 * @return
	 */
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
	public @ResponseBody ResultMessage DeleteData(Model model)
	{
		MyMap 	paramMap 				  	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		String 	resultCode			  		= ResultCode.RESULT_INTERNAL_SERVER_ERROR;
		int   	resultRegisterDataCount 	= 0;

		resultRegisterDataCount = mBiz.DeleteData( paramMap );

		if( resultRegisterDataCount > 0)
		{
			resultCode			  		= ResultCode.RESULT_OK;
		}

		 model.addAttribute("paramMap",      paramMap);

		 return new ResultMessage(ResultCode.RESULT_OK, null);
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

	// ?????? ????????????
	@RequestMapping(value = { "/ListExcelData.do" })
	public void ListExcelData(HttpServletRequest request, HttpServletResponse response, Model model)throws WriteException, IOException
	{
		List<MyCamelMap> resultS03Excel    		= null;
        MyMap            paramMap          		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        BasicBean       resultBean 	 			= null;
        String      	strFileName      		= "?????????????????? ("+paramMap.getStr("sDate")+"~"+paramMap.getStr("eDate")+").xlsx";
		String [] 		arrTitle           		= new String[]
																{
																"ID"
																,"?????????"
																,"?????????"
																,"????????????"
																,"??????"
																,"??????"
																,"????????????"
																,"??????"};

		String [] 		arrExcelColum         	= new String[]
																{"uniqId"
																,"mberNameDisplay"
																,"emailAddress"
																,"moblphonNoDisplay"
																,"name"
																,"roleName"
																,"mberRatingName"
																,"mberSttusName"};

		paramMap.put("rows",1000000);
		resultBean = mBiz.ListPagingData( paramMap );
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

    @RequestMapping(value = { "/CallAuthPage.do" })
    public String CallAuthPage(Model model)
    {
    	MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

//            String phoneNumber = paramMap.getStr("phoneNumber");  //  ?????? ????????????
    	int authNumber  = 0;

    	/*???????????? ????????? ??????*/
    	//authNumber = ((int)(Math.random() * 98) + 10);

    	model.addAttribute("paramMap",         paramMap);
//        model.addAttribute("phoneNumber",      phoneNumber);
    	model.addAttribute("authNumber",       authNumber);

    	return pagePrefix + "/RegisterCallAuthPage";
    }



    // ???????????? ????????????
    @RequestMapping(value = { "/CallAuth.do" })
    public @ResponseBody ResultMessage  CallAuth(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        JSONObject rtrn = null;
        // ????????? A()
        String moblphonNo = null;
        String authNumber 	= paramMap.getStr("authNumber");

        String resultCode = ResultCode.RESULT_OK;

        MyMap charge_seachMap = new MyMap();
        charge_seachMap.put("name", "?????????");

        MyCamelMap charge =  mChargeBiz.SelectOneData(charge_seachMap);
        System.out.println("charge : " + charge);


        charge_seachMap.put("chargeId","1");

        MyCamelMap nrlmber =  mBiz.SelectOneData(charge_seachMap);
        System.out.println("nrlmber : " + nrlmber);

        System.out.println("moblphonNo:"+nrlmber.getStr("moblphonNo"));
        System.out.println("uniqId:"+nrlmber.getStr("uniqId"));
        System.out.println("mberSttus:"+nrlmber.getStr("mberSttus"));


//         rtrn =  mCommonBiz.authCallSender(nrlmber.getStr("moblphonNo"), authNumber);

        String intro1 = "???????????????. ?????? ????????? ?????? ????????? ????????? ??????????????? ???????????????.";
        String intro2 = "?????? ????????? ?????? ????????? ????????? ??????????????? ???????????????.";

        rtrn = mCommonBiz.authCallDynamicSender(nrlmber.getStr("moblphonNo"), authNumber, intro1, intro2);

         String callResult = (String) rtrn.get("result");

         if( callResult.equals("00")  )
         {
             resultCode = ResultCode.RESULT_OK;
         } else {
             resultCode = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
         }

        return new ResultMessage(resultCode, rtrn );
    }

}
