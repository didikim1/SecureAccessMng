package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_111X")
public class GUIAPIABiz_Cmd_111X
{

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper")
    EqAllowIPMapper mEqAllowIPMapper;

    /**
     * 허용IP 조회 (1113)
     *
     * @param paramObj
     */
    @SuppressWarnings("unchecked")
    public JSONObject Cmd_1113(JSONObject requestMessage, HttpServletRequest request)
    {
        MyMap           paramMap                 = new MyMap();
        MyMap           returnMap                = null;
        String          addr                     = getIp(request);

        JSONObject  responseMessage              = null;


        System.out.println("addr : " + addr);

        paramMap.put("addr", (String) requestMessage.getOrDefault("addr", getIp(request)));

        returnMap = mEqAllowIPMapper.SelectOneData( paramMap );

        responseMessage = new JSONObject(returnMap);

        return responseMessage;
    }

    private String getIp( HttpServletRequest request )
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
