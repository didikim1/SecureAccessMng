package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.code.biz.CtnCodeBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;

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

    @RequestMapping(value =
    { "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

//		model.addAttribute("IdcInfoList", mIdcInfoBiz.ListData(new MyMap()));

        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value =
    { "/SelectOneData.do" })
    public String SelectOneData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return null;
    }

    @RequestMapping(value =
    { "/Register.do" }, method = RequestMethod.GET)
    public String Register(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap resultMap = null;

        if (FrameworkUtils.isNotNull(paramMap.getStr("svId", "")))
        {
            resultMap = mBiz.SelectOneData(paramMap);
            model.addAttribute("Info", resultMap);
        }

        model.addAttribute("paramMap", paramMap);
        model.addAttribute("IdcInfoList", mCtnCodeBiz.ListData(new MyMap()));

        return pagePrefix + "/Register";
    }

    @RequestMapping(value =
    { "/RegisterData.do" })
    public ResultMessage RegisterData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
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
