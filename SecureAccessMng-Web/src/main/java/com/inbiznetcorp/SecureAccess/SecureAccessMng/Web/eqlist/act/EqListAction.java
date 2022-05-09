package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.act;

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

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.code.biz.CtnCodeBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.entrprsmber.biz.EntrprsmberBiz;
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
@RequestMapping("/eqlist")
public class EqListAction
{
    final String pagePrefix = "eqlist";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(EqListAction.class.getName());

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz mBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctncode.biz.CtnCodeBiz")
    CtnCodeBiz mCtnCodeBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz")
    EqIdcBiz mEqIdcBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.entrprsmber.biz.EntrprsmberBiz")
    EntrprsmberBiz mEntrprsmberBiz;

//    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper")
//    CodeMapper mCodeMapper;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.excel.ExcelWrite")
    ExcelWrite mExcelWrite;
    
    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz mEqListBiz;


    @RequestMapping(value =
    { "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        BasicBean resultBean    		= mBiz.ListPagingData(paramMap);
        BasicBean eqIdcBean     		= mEqIdcBiz.ListData(new MyMap());

        List<MyCamelMap> purposeInfoList	= null;

        if ("".equals(paramMap.getStr("sDate", ""))) {
            paramMap.put("sDate", FrameworkUtils.aGoMonth(-12, "yyyy-MM-dd"));
            paramMap.put("eDate", FrameworkUtils.aGoDate(0,   "yyyy-MM-dd"));
        }

        MyMap purposeMap = new MyMap();
        purposeMap.put("title", 		"PURPOSE_USE");
        purposeMap.put("type", 			"B");
        purposeInfoList = mCtnCodeBiz.ListData(purposeMap).getList();

        model.addAttribute("Data",              resultBean);
        model.addAttribute("IdcInfoList",       eqIdcBean);
        model.addAttribute("purposeUseList",    purposeInfoList);
        model.addAttribute("paramMap",          paramMap);

//		model.addAttribute("IdcInfoList", mIdcInfoBiz.ListData(new MyMap()));

        return pagePrefix + "/ListPagingData";
    }


    @RequestMapping(value = { "/ListExcelData.do" })
    public void ListExcelData(HttpServletRequest request, HttpServletResponse response, Model model)throws WriteException, IOException
    {
		    List<MyCamelMap> resultS03Excel                 = null;
		    MyMap            paramMap                       = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		    BasicBean       resultBean                      = null;
		    String          strFileName                     = "자산관리 ("+paramMap.getStr("sDate")+"~"+paramMap.getStr("eDate")+")";
		    String []       arrTitle                		= new String[] { "IDC명",  "서버명", "Port(SSH)", "IP", "등록일"};
		    String []       arrExcelColum           		= new String[] {"idcName", "name", "svPort",  "svIp1",  "frstRegisterPnttm"};

            paramMap.put("rows",1000000);
            resultBean = mBiz.ListPagingData( paramMap );

             mExcelWrite.selectExcelList(response, arrTitle, arrExcelColum, resultBean.getList(),  strFileName);

    }

    @RequestMapping(value ={ "/SelectOneData.do" })
    public @ResponseBody ResultMessage SelectOneData(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap resultMap = mBiz.SelectOneData(paramMap);

        return new ResultMessage("200", resultMap);
    }

    @RequestMapping(value =
    { "/Register.do" }, method = RequestMethod.GET)
    public String Register(Model model)
    {
        MyMap            paramMap               = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        List<MyCamelMap> purposeInfoList        = null;
//        MyMap resultMap = null;
//
//        if (FrameworkUtils.isNotNull(paramMap.getStr("svId", "")))
//        {
//            resultMap = mBiz.SelectOneData(paramMap);
//            model.addAttribute("Info", resultMap);
//        }

        String receivingPnttm = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        paramMap.put("receivingPnttm", receivingPnttm);

        BasicBean entrprsmberBean       = mEntrprsmberBiz.ListData(new MyMap());        // 관리자 List
        BasicBean idcInfoBean           = mEqIdcBiz.ListData(new MyMap());              // IDC List

        MyMap purposeMap = new MyMap();
        purposeMap.put("title",                 "PURPOSE_USE");
        purposeMap.put("type",                  "B");
        purposeInfoList = mCtnCodeBiz.ListData(purposeMap).getList();

        model.addAttribute("paramMap",         	 	paramMap);
        model.addAttribute("EntrprsmberList",   	entrprsmberBean.getList());
        model.addAttribute("IdcInfoList",       	idcInfoBean.getList());
        model.addAttribute("purposeUseInfoList",    purposeInfoList);

        return pagePrefix + "/Register";
    }

    @RequestMapping(value = { "/RegisterData.do" })
    public @ResponseBody ResultMessage RegisterData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap resultMap       = null;

        // 자산 등록
        mBiz.RegisterData(paramMap);

        return new ResultMessage(ResultCode.RESULT_OK, null);
    }

    @RequestMapping(value = { "/ModifyData.do" })
    public ResultMessage ModifyData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value = { "/DeleteData.do" })
    public @ResponseBody ResultMessage DeleteData(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        int   iRtnValue = 0;

        iRtnValue = mBiz.DeleteData(paramMap);

        if ( iRtnValue > 0 )
        {
            return new ResultMessage(ResultCode.RESULT_OK, null);
        }
        else
        {
            return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
        }
    }
}
