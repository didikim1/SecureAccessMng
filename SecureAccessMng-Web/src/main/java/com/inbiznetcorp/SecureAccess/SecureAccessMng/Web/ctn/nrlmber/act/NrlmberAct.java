package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.act;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

@Controller
@RequestMapping("/ctn/nrlmber")
public class NrlmberAct
{
	final String pagePrefix = "ctn/nrlmber";

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(NrlmberAct.class.getName());

	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz")
	NrlmberBiz mBiz;

	@RequestMapping(value = { "/ListPagingData.do" })
	public String ListPagingDatas(Model model)
	{
	    MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            BasicBean       resultBean  = null;

            resultBean = mBiz.ListPagingData( paramMap );



//            model.addAttribute("roleId",                FrameworkBeans.findSessionBean().roleId);
            model.addAttribute("paramMap",              paramMap);
            model.addAttribute("Data",                  resultBean);

	    return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
	    MyMap                 paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            MyCamelMap      resultMap       = new MyCamelMap();

            resultMap = mBiz.SelectOneData(paramMap);

            model.addAttribute("paramMap",      paramMap);
            model.addAttribute("Data",          resultMap);

            return null;
	}

	@RequestMapping(value = { "/ProcSelectOneData.do" })
        public String ProcSelectOneData(Model model)
        {
            MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
            MyCamelMap      resultMap       = new MyCamelMap();

            resultMap = mBiz.SelectOneData(paramMap);

            model.addAttribute("paramMap",      paramMap);
            model.addAttribute("Data",          resultMap);

            return null;
        }

	@RequestMapping(value = { "/RegisterData.do" })
	public String RegisterData(Model model)
	{
		MyMap paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap resultMap       = null;

		resultMap = mBiz.SelectOneData(paramMap);

		 model.addAttribute("paramMap",      paramMap);
		 model.addAttribute("Info",          resultMap);

		return pagePrefix + "/RegisterData";
	}

	@RequestMapping(value = { "/RegisterContent.do" })
	public String RegisterContent(Model model)
	{
		MyMap paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap resultMap       = null;

		resultMap = mBiz.SelectOneData(paramMap);

		model.addAttribute("paramMap",      paramMap);
		model.addAttribute("Info",          resultMap);

		return pagePrefix + "/RegisterContent";
	}

	@RequestMapping(value = { "/ProcRegisterData.do" })
	public @ResponseBody ResultMessage ProcRegisterData(Model model)
	{
	    MyMap              paramMap                        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	    MyCamelMap         resultMap                       = new MyCamelMap();

	    String             resultCode                      = ResultCode.RESULT_INTERNAL_SERVER_ERROR;

            int                resultRegisterDataCount         = 0;

            if(paramMap.getInt("seq", 0) > 0 )
            {
                resultCode      = ResultCode.RESULT_OK;
                resultRegisterDataCount = mBiz.ModifyData(paramMap);
            }
            else
            {
                resultRegisterDataCount = mBiz.RegisterData( paramMap );
            }

	    return new ResultMessage(resultCode, null);
	}

	@RequestMapping(value = { "/ModifyData.do" })
	public String ModifyData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		 model.addAttribute("paramMap",      paramMap);

		return pagePrefix + "/ModifyData";
	}

	@RequestMapping(value = { "/ProcModifyData.do" })
	public ResultMessage ProcModifyData(Model model)
	{
	    MyMap              paramMap                = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	    MyCamelMap         resultMap               = new MyCamelMap();
            int                resultRegisterDataCount = 0;

	    resultRegisterDataCount = mBiz.ModifyData( paramMap );

	    return new ResultMessage(ResultCode.RESULT_OK, null);
	}

	@RequestMapping(value = { "/DeleteData.do" })
	public String DeleteData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		 model.addAttribute("paramMap",      paramMap);

		return pagePrefix + "/ModifyData";
	}

	@RequestMapping(value = { "/ProcDeleteData.do" })
	public ResultMessage ProcDeleteData(Model model)
	{
	    MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	    MyCamelMap         resultMap                     = new MyCamelMap();
            int                resultDeleteDataCount         = 0;
            String              resultCode                   = ResultCode.RESULT_EMPTY;

            resultDeleteDataCount = mBiz.DeleteData( paramMap );

            if ( resultDeleteDataCount > 0 )
            {
                    resultCode = ResultCode.RESULT_OK;
            }

            return new ResultMessage(resultCode, resultMap);
	}
}
