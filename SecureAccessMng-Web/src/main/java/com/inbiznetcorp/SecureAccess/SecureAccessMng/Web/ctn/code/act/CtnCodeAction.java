package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.code.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.code.biz.CtnCodeBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;

@Controller
@RequestMapping("/ctn/code")
public class CtnCodeAction
{
    final String pagePrefix = "ctn/code";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(CtnCodeAction.class.getName());
    
    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctncode.biz.CtnCodeBiz")
    CtnCodeBiz mBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz mEqListBiz;

    @RequestMapping(value =
    { "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

//		model.addAttribute("IdcInfoList", mIdcInfoBiz.ListData(new MyMap()));

        return pagePrefix + "/ListPagingData";
    }
    
    @RequestMapping(value = { "/PopupListData.do" })
    public String PopupListPagingData(Model model) {
            MyMap           paramMap                = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            MyMap           searchParentMap = new MyMap();

            MyCamelMap  resultParentMap     = null;

            searchParentMap.put("ptitle",    paramMap.getStr("ptitle"));
            searchParentMap.put("type",      "A");

            resultParentMap = mBiz.SelectOneData( searchParentMap );

            model.addAttribute("paramMap",  paramMap);
            model.addAttribute("parentMap", resultParentMap);

            return pagePrefix + "/popup/ListData";
    }

    @RequestMapping(value =
    { "/ListData.do" })
    public @ResponseBody ResultMessage ListData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean resultBean = null;

        resultBean = mBiz.ListData(paramMap);

        return new ResultMessage("200", resultBean);
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
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap resultMap = null;

        if (FrameworkUtils.isNotNull(paramMap.getStr("svId", "")))
        {
            resultMap = mEqListBiz.SelectOneData(paramMap);
            model.addAttribute("Info", resultMap);
        }

        model.addAttribute("paramMap", paramMap);
        model.addAttribute("IdcInfoList", mBiz.ListData(new MyMap()));

        return pagePrefix + "/Register";
    }

    @RequestMapping(value =
    { "/RegisterData.do" })
    public @ResponseBody ResultMessage RegisterData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        
        int result = mBiz.RegisterData(paramMap);

        return new ResultMessage("", null);
    }

    @RequestMapping(value =
    { "/ModifyData.do" })
    public @ResponseBody ResultMessage ModifyData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value =
    { "/DeleteData.do" })
    public @ResponseBody ResultMessage DeleteData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }
}
