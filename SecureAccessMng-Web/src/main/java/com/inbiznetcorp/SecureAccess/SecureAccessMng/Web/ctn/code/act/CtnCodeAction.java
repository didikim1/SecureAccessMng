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
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
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

    @RequestMapping(value ={ "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean       resultBean  = null;

        paramMap.put("type", "A");      // 부모(A) 자식(C)

        resultBean = mBiz.ListPagingData( paramMap );

        model.addAttribute("paramMap",          paramMap);
        model.addAttribute("Data",              resultBean);

        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value = { "/PopupListData.do" })
    public String PopupListPagingData(Model model) {
            MyMap           paramMap                = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            MyMap           searchParentMap = new MyMap();

            MyCamelMap  resultParentMap     = null;

            searchParentMap.put("title",    paramMap.getStr("title"));
            searchParentMap.put("type",     "A");

            resultParentMap = mBiz.SelectOneData( searchParentMap );

            model.addAttribute("paramMap",  paramMap);
            model.addAttribute("parentMap", resultParentMap);

            return pagePrefix + "/popup/ListData";
    }

    @RequestMapping(value = { "/ListData.do" })
    public @ResponseBody ResultMessage ListData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean resultBean = null;

        String registType = paramMap.getStr("registType");      // A:자산등록, B:계정등록
        
        // CTN_CODE 테이블에서 장비정보 SELECT
        if(registType.equals("A"))
        {
            resultBean = mBiz.ListPagingData(paramMap);
        }
        // EQ_LIST 테이블에서 서버정보 SELECT
        else if(registType.equals("B"))
        {
            resultBean = mEqListBiz.ListPagingData(paramMap);
        }

        return new ResultMessage("200", resultBean);
    }

    @RequestMapping(value ={ "/SelectOneData.do" })
    public @ResponseBody ResultMessage SelectOneData(Model model)
    {
        MyMap paramMap 	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap resultMap = mBiz.SelectOneData(paramMap);

        return new ResultMessage("200", resultMap);
    }

    @RequestMapping(value = { "/Register.do" }, method = RequestMethod.GET)
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

        return new ResultMessage(ResultCode.RESULT_OK, "Success!!", null);
    }

    @RequestMapping(value = { "/ModifyData.do" })
    public @ResponseBody ResultMessage ModifyData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value = { "/DeleteData.do" })
    public @ResponseBody ResultMessage DeleteData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }
}
