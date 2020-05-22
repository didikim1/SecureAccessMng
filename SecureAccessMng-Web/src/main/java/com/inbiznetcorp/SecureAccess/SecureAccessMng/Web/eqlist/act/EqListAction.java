package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.act;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

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
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

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

    @RequestMapping(value =
    { "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        
        BasicBean resultBean = mBiz.ListPagingData(paramMap);
        
        model.addAttribute("Data", resultBean);

//		model.addAttribute("IdcInfoList", mIdcInfoBiz.ListData(new MyMap()));

        return pagePrefix + "/ListPagingData";
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
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
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

        model.addAttribute("paramMap",          paramMap);
        model.addAttribute("EntrprsmberList",   entrprsmberBean.getList());
        model.addAttribute("IdcInfoList",       idcInfoBean.getList());

        return pagePrefix + "/Register";
    }

    @RequestMapping(value =
    { "/RegisterData.do" })
    public @ResponseBody ResultMessage RegisterData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        
        // 자산 등록
        mBiz.RegisterData(paramMap);

        return new ResultMessage(ResultCode.RESULT_OK, null);
    }

    @RequestMapping(value =
    { "/ModifyData.do" })
    public ResultMessage ModifyData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value =
    { "/DeleteData.do" })
    public ResultMessage DeleteData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }
}
