package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.act;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.dto.LoginDTO;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
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

    @RequestMapping(value="/chkAllowIP.do", method=RequestMethod.GET, consumes="application/json")
    public @ResponseBody ResultMessage list(@RequestParam(value="ip", defaultValue="0.0.0.0") String ipAddress)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        MyMap           returnMap      = null;
        MyMap           paramMap       = new MyMap();

        paramMap.put("addr", ipAddress);
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
}
