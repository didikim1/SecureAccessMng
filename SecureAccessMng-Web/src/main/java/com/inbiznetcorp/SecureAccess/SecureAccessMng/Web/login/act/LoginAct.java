package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.act;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.act.NrlmberAct;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz;

@Controller
@RequestMapping("/login")
public class LoginAct
{
    final String pagePrefix = "login";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(NrlmberAct.class.getName());

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz")
    LoginBiz mBiz;


    @RequestMapping(value = { "/", "/index.do" })
    public String index(Model model)
    {
            return pagePrefix + "/index";
    }

    @RequestMapping(value = { "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean       resultBean  = null;

        resultBean = mBiz.ListPagingData( paramMap );

        model.addAttribute("paramMap",          paramMap);
        model.addAttribute("Data",              resultBean);

        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value = { "/SelectOneData.do" })
    public String SelectOneData(Model model)
    {
        MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap      resultMap       = new MyCamelMap();

        resultMap = mBiz.SelectOneData(paramMap);

        model.addAttribute("paramMap",      paramMap);
        model.addAttribute("Data",          resultMap);

        return null;
    }

    @RequestMapping(value = { "/ProcSelectOneData.do" })
    public @ResponseBody ResultMessage ProcSelectOneData(Model model)
    {
        MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap      resultMap       = new MyCamelMap();
        String          resultCode      = ResultCode.RESULT_OK;

        resultMap                         = mBiz.SelectOneData(paramMap);

        if ( resultMap == null )
        {
                resultCode = ResultCode.RESULT_EMPTY;
        }
        else
        {
                FrameworkBeans.findSessionBean().mberSeq        = resultMap.getStr("seq");
                FrameworkBeans.findSessionBean().dpamentId      = resultMap.getStr("dpamentId");
                FrameworkBeans.findSessionBean().positionId     = resultMap.getStr("positionId");
                FrameworkBeans.findSessionBean().uniqId         = resultMap.getStr("uniqId");
                FrameworkBeans.findSessionBean().mberName       = resultMap.getStr("mberName");
                FrameworkBeans.findSessionBean().mberSttus      = resultMap.getStr("mberSttus");
                FrameworkBeans.findSessionBean().moblphonNo     = resultMap.getStr("moblphonNo");
                FrameworkBeans.findSessionBean().emailAddress   = resultMap.getStr("emailAddress");
        }

        return new ResultMessage(resultCode, "success");
    }
//
//    @RequestMapping(value = { "/RegisterData.do" })
//    public String RegisterData(Model model)
//    {
//            MyMap paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//            MyMap resultMap       = null;
//
//             model.addAttribute("paramMap",      paramMap);
//
//            return pagePrefix + "/RegisterData";
//    }
//
//    @RequestMapping(value = { "/ProcRegisterData.do" })
//    public ResultMessage ProcRegisterData(Model model)
//    {
//        MyMap              paramMap                        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//        MyCamelMap         resultMap                       = new MyCamelMap();
//        int                resultRegisterDataCount         = 0;
//
//        resultRegisterDataCount = mBiz.RegisterData( paramMap );
//
//        return new ResultMessage("", null);
//    }
//
//    @RequestMapping(value = { "/ModifyData.do" })
//    public String ModifyData(Model model)
//    {
//            MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//
//             model.addAttribute("paramMap",      paramMap);
//
//            return pagePrefix + "/ModifyData";
//    }
//
//    @RequestMapping(value = { "/ProcModifyData.do" })
//    public ResultMessage ProcModifyData(Model model)
//    {
//        MyMap              paramMap                = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//        MyCamelMap         resultMap               = new MyCamelMap();
//        int                resultRegisterDataCount = 0;
//
//        resultRegisterDataCount = mBiz.ModifyData( paramMap );
//
//        return new ResultMessage(ResultCode.RESULT_OK, null);
//    }

    // 로그아웃
    @RequestMapping(value = { "/DeleteData.do" })
    public String DeleteData(Model model, HttpServletRequest request)
    {
            request.getSession().invalidate();

            return "redirect:/login/";
    }

//    @RequestMapping(value = { "/ProcDeleteData.do" })
//    public ResultMessage ProcDeleteData(Model model)
//    {
//        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//        MyCamelMap         resultMap                     = new MyCamelMap();
//        int                resultDeleteDataCount         = 0;
//        String              resultCode                   = ResultCode.RESULT_EMPTY;
//
//        resultDeleteDataCount = mBiz.DeleteData( paramMap );
//
//        if ( resultDeleteDataCount > 0 )
//        {
//                resultCode = ResultCode.RESULT_OK;
//        }
//
//        return new ResultMessage(resultCode, resultMap);
//    }
}
