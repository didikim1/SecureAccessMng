package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.act;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

@Controller
@RequestMapping("/eqidpwd")
public class EqIdpwdAction
{
    final String pagePrefix = "eqlist";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(EqIdpwdAction.class.getName());

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz")
    EqIdpwdBiz mBiz;

    @RequestMapping(value =
    { "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        
        BasicBean resultBean = mBiz.ListPagingData(paramMap);
        
        model.addAttribute("Data", resultBean);

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
        
        String receivingPnttm = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        paramMap.put("receivingPnttm", receivingPnttm);
        
        model.addAttribute("paramMap",          paramMap);

        return pagePrefix + "/Register";
    }

    @RequestMapping(value =
    { "/RegisterData.do" })
    public @ResponseBody ResultMessage RegisterData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        
        // 계정 등록
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
