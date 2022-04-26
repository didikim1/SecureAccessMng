package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

@Controller
@RequestMapping("/idcinfo")
public class EqIdcAction
{
	final String pagePrefix = "comtn/bbs";

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(EqIdcAction.class.getName());

	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz")
        EqIdcBiz mBiz;

	@RequestMapping(value = { "/ListPagingData.do" })
	public String ListPagingData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		return null;
	}

	@RequestMapping(value = { "/RegisterData.do" }, method = RequestMethod.POST)
	public @ResponseBody ResultMessage RegisterData(Model model)
	{
	    MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	    int   iRtnValue = 0;

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
