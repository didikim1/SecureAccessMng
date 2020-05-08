package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.idcinfo.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.idcinfo.service.IdcInfoBiz;

@Controller
@RequestMapping("/idcinfo")
public class IdcInfoAction
{
	final String pagePrefix = "comtn/bbs";

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(IdcInfoAction.class.getName());
	
	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.idcinfo.service.IdcInfoBiz")
        IdcInfoBiz mBiz;

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
