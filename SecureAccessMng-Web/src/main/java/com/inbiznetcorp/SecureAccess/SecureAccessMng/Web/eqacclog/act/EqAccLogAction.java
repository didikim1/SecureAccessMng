package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.act;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.code.biz.CtnCodeBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.excel.ExcelWrite;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper;

import jxl.write.WriteException;

@Controller
@RequestMapping("/eqacclog")
public class EqAccLogAction
{
    final String pagePrefix = "eqacclog";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(EqAccLogAction.class.getName());

	private static final LinkedHashMap<Object, Object> requestMessage = null;

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz")
    EqAccLogBiz mBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz")
    EqIdcBiz mEqIdcBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz mEqListBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.excel.ExcelWrite")
    ExcelWrite mExcelWrite;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper")
	 CodeMapper mCodeMapper;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctncode.biz.CtnCodeBiz")
    CtnCodeBiz mCtnCodeBiz;

    @Value("${spring.profiles.active}")
    String active;

    @RequestMapping(value ={ "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {

    	MyMap           paramMap    		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        BasicBean       resultBean  		= null;
        BasicBean       idcInfoBean 		= mEqIdcBiz.ListData(new MyMap());              // IDC List
        BasicBean       refEqListInfoBean 	= mEqIdcBiz.ListData(new MyMap());              // ?????? List

        List<MyCamelMap> workTypeList	    = null;
        List<MyCamelMap> purposeInfoList	= null;


        if ("".equals(paramMap.getStr("sDate", ""))) {
            paramMap.put("sDate", FrameworkUtils.aGoDate(-30, "yyyy-MM-dd"));
            paramMap.put("eDate", FrameworkUtils.aGoDate(0,   "yyyy-MM-dd"));
        }

        resultBean = mBiz.ListPagingData( paramMap );


        MyMap miaMap = new MyMap();
        miaMap.put("title", 		"WORK_TYPE");
        miaMap.put("type", 			"B");

        workTypeList = mCodeMapper.ListData(miaMap);

        MyMap purposeMap = new MyMap();
        purposeMap.put("title", 		"PURPOSE_USE");
        purposeMap.put("type", 			"B");

        purposeInfoList = mCtnCodeBiz.ListData(purposeMap).getList();

        model.addAttribute("paramMap",        			   	paramMap);
        model.addAttribute("Data",              			resultBean);


        model.addAttribute("workInfoList",              	workTypeList);
        model.addAttribute("purposeInfoList",             	purposeInfoList);
        model.addAttribute("IdcInfoList",      				idcInfoBean.getList());
        model.addAttribute("refEqList",       				refEqListInfoBean.getList());



//        if ( paramMap.getInt("idcSeq", 0) > 0)
//        {
//            MyMap eqListParamMap = new MyMap();
//
//            eqListParamMap.put("rows",     10000);
//            eqListParamMap.put("refEqIdc", paramMap.getInt("idcSeq"));
//
//
//            model.addAttribute("EqListInfoList",       mEqListBiz.ListPagingData(eqListParamMap).getList());
//        }


        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value = {"/ListExcelData" })
	public void ListExcelData(HttpServletRequest request, HttpServletResponse response, Model model)throws WriteException, IOException
	{
		List<MyCamelMap> resultS03Excel    = null;
        MyMap            paramMap          = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        String      strFileName      = "???????????? ???????????? ("+paramMap.getStr("sDate")+"~"+paramMap.getStr("eDate")+").xlsx";
        String[]    arrTitle		 = new String[]{"IDC", 			  "??????", 	   "??????ID", 		"??????IP", 		"?????????",   		   "????????????ID", 	"???????????????",    	 "??????????????????"};
        String[]    arrExcelColum  	 = new String[]{"eqIdcName", 	  "eqListName", "eqIdpwdID", "eqAllowIpName",  "ctnNrlmberName", "processid", 	"frstRegisterPnttm", "lastUpdusrPnttm"};

        resultS03Excel = mBiz.ListData(paramMap);

        mExcelWrite.selectExcelList(response, arrTitle, arrExcelColum, resultS03Excel, strFileName);

	}

    // IDC??? ?????????
    @RequestMapping(value ={ "/EqList/ListData.do" })
    public @ResponseBody ResultMessage EqListListData(Model model)
    {
        MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        MyMap eqListParamMap = new MyMap();

        eqListParamMap.put("rows",     10000);
        eqListParamMap.put("refEqIdc", paramMap.getInt("idcSeq", 0));


        return new ResultMessage(ResultCode.RESULT_OK, mEqListBiz.ListPagingData(eqListParamMap) );
    }




    @RequestMapping(value ={ "/SelectOneData.do" })
    public String SelectOneData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return null;
    }

    @RequestMapping(value ={ "/pop/SelectOneData.do" })
    public String popSelectOneData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap rtnMap   = mBiz.SelectOneData(paramMap);
        List<MyCamelMap> purposeInfoList        = null;

        MyMap purposeMap = new MyMap();
        purposeMap.put("title",                 "PURPOSE_USE");
        purposeMap.put("type",                  "B");
        purposeInfoList = mCtnCodeBiz.ListData(purposeMap).getList();

        model.addAttribute("info",          rtnMap);
        model.addAttribute("purposeUseInfoList",    purposeInfoList);

        return pagePrefix + "/pop/SelectOneData";
    }

    @RequestMapping(value = { "/RegisterData.do" })
    public ResultMessage RegisterData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value = { "/ModifyData.do" })
    public ResultMessage ModifyData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value = { "/DeleteData.do" })
    public ResultMessage DeleteData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }
}
