package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.act;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.dto.AllowIPDTO;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.dto.LoginDTO;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper;

@Controller
@RequestMapping("/api/v2/login")
public class ApiV2LoginAct
{
    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(ApiV2LoginAct.class.getName());

    public static String kKey_uniqid    = "uniqid";
    public static String kKey_password  = "password";

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz")
    LoginBiz mBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper")
    EqAllowIPMapper mEqAllowIPMapper;

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz")
    CommonBiz mCommonBiz;


    @RequestMapping(value="/ars_auth.do", method=RequestMethod.GET)
    public @ResponseBody ResultMessage chkAllowMacAddress(@RequestParam("authNumber") String authNumber, @RequestParam("moblphonNo") String moblphonNo)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        MyMap           returnMap      = null;
        MyMap           paramMap       = new MyMap();
        JSONObject      rtrn           = null;
        String          callResult     = null;


        String intro1 = "안녕하세요. 로그인을 위해 화면에 보이는 인증번호를 눌러주세요.";
        String intro2 = "로그인을 위해 화면에 보이는 인증번호를 눌러주세요.";

        System.out.println("authNumber : " + authNumber);
        System.out.println("moblphonNo : " + moblphonNo);

        result_code = ResultCode.RESULT_OK;
        /*
        rtrn = mCommonBiz.authCallDynamicSender(moblphonNo, authNumber, intro1, intro2);

        callResult = (String) rtrn.get("result");

        if (callResult.equals("00")) {
            resultCode = ResultCode.RESULT_OK;

        } else {
                resultCode = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
        }
        */
        return new ResultMessage(result_code, returnMap);
    }

    @RequestMapping(value="/chkAllowIP.do", method=RequestMethod.GET)
    public @ResponseBody ResultMessage chkAllowIP(@RequestParam("ip") String ip)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        MyMap           returnMap      = null;
        MyMap           paramMap       = new MyMap();
        paramMap.put("addr",  ip);
        returnMap = mEqAllowIPMapper.SelectOneData( paramMap );

        if(returnMap != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, returnMap);
    }

    @RequestMapping(value="/login.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage login(@RequestBody LoginDTO dto)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        MyCamelMap      responseMap     = null;
        MyMap           paramMap        = new MyMap();

        paramMap.put(kKey_uniqid,       dto.getUniqid());
        paramMap.put(kKey_password,     dto.getPassword());

        responseMap = mBiz.SelectOneData(paramMap);

        if(responseMap != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, responseMap);
    }

    @RequestMapping(value="/login_user_info.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage login_user_info(HttpServletRequest request)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        MyCamelMap      responseMap     = null;
        MyMap           paramMap        = new MyMap();

        JSONObject paramObject = FrameworkUtils.getBody(request);

        System.out.println("paramObject : " + paramObject);

        paramMap.put("uniqId",        paramObject.get("uniqId"));

        responseMap = mBiz.SelectOneData(paramMap);

        if(responseMap != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, responseMap);
    }

    @RequestMapping(value="/login_ars.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage login_ars(HttpServletRequest request)
    {
        String          result_code     = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
        JSONObject      rtrn            = null;

        MyCamelMap      responseMap     = null;
        MyMap           paramMap        = new MyMap();

        JSONObject paramObject = FrameworkUtils.getBody(request);

        String moblphonNo       = (String)paramObject.get("moblphonNo");
        String authNumber       =  paramObject.get("authNumber").toString();

        System.out.println("moblphonNo" + moblphonNo);
        System.out.println("authNumber : " + authNumber);

        rtrn =  mCommonBiz.authCallSender(moblphonNo, authNumber);

        if(rtrn != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, rtrn);
    }

    @RequestMapping(value="/getIp.do", method=RequestMethod.GET)
    private @ResponseBody String getIp( HttpServletRequest request )
    {
          String ip = request.getHeader("X-FORWARDED-FOR");

           if (ip == null || ip.length() == 0)
           {
             ip= request.getHeader("Proxy-Client-IP");
           }

           if (ip == null || ip.length() == 0)
           {
             ip= request.getHeader("WL-Proxy-Client-IP");
           }

           if (ip == null || ip.length() == 0)
           {
             ip= request.getRemoteAddr() ;
           }

           return ip;
    }

}
