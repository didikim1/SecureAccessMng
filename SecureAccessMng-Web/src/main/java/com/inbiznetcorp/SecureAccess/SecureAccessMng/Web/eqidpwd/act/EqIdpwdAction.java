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
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.ManagerPWAES256;
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

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz mEqListBiz;

    @RequestMapping(value = { "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        BasicBean resultBean = mBiz.ListPagingData(paramMap);

        model.addAttribute("Data", resultBean);

        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value = { "/ListData.do" })
    public @ResponseBody ResultMessage ListData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean resultBean = null;

        resultBean = mBiz.ListPagingData(paramMap);

        return new ResultMessage("200", resultBean);
    }

    @RequestMapping(value ={ "/SelectOneData.do" })
    public @ResponseBody ResultMessage SelectOneData(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap resultMap = mBiz.SelectOneData(paramMap);

        return new ResultMessage("200", resultMap);
    }

    @RequestMapping(value = { "/Register.do" }, method = RequestMethod.GET)
    public String Register(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap infoMap   = null;
        String receivingPnttm = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        paramMap.put("receivingPnttm", receivingPnttm);


        infoMap = mEqListBiz.SelectOneData(paramMap);

        model.addAttribute("paramMap",          paramMap);
        model.addAttribute("infoMap",           infoMap);

        return "eqidpwd/Register";
    }

    @SuppressWarnings("static-access")
	@RequestMapping(value = { "/RegisterData.do" })
    public @ResponseBody ResultMessage RegisterData(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        int   iRtnValue = 0;
        
        String strPWD	 = null;
        String strEncPWD = null;
        
        strPWD    = paramMap.getStr("pwd");
        try 
        {
			strEncPWD = ManagerPWAES256.getInstance().AES_Encode( strPWD );
			paramMap.put("pwd", strEncPWD);
			// 계정 등록
			iRtnValue = mBiz.RegisterData(paramMap);
			
			if ( iRtnValue > 0 )
			{
				return new ResultMessage(ResultCode.RESULT_OK, null);
			}
			else
			{
				return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
			}
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
        return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
        
    }

    @RequestMapping(value = { "/ModifyData.do" })
    public @ResponseBody ResultMessage ModifyData(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        int   iRtnValue = 0;
        // 계정 수정
        iRtnValue = mBiz.ModifyData(paramMap);

        if ( iRtnValue > 0 )
        {
            return new ResultMessage(ResultCode.RESULT_OK, null);
        }
        else
        {
            return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
        }
    }

    @RequestMapping(value = { "/DeleteData.do" })
    public @ResponseBody ResultMessage DeleteData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        int   iRtnValue = 0;

        // 계정 삭제
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
