package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.httpClient.HttpRequestProc;

@Controller
@RequestMapping("/comm/api")
public class CommAPIAction
{
    final String pagePrefix = "comtn/bbs";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(CommAPIAction.class.getName());

    @RequestMapping(value ={ "/getPublicIP.do" })
    public @ResponseBody String ListPagingData(Model model, HttpServletRequest request)
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
    
	@RequestMapping(value ={ "/arsAuth.do" })
    public void arsAuth(HttpServletRequest request, HttpServletResponse response)
    {
		PrintWriter     printWriter                     = null;
	    
    	MyMap 		paramMap 		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
    	JSONObject jsonObjectResult = null; 
    	
    	HashMap<String, String> arsAuthMap = new HashMap<String, String>();
    	
    	
    	arsAuthMap.put("authReqNumber",        	paramMap.getStr("authReqNumber"));
    	arsAuthMap.put("requestTime",          	paramMap.getStr("requestTime"));
    	arsAuthMap.put("companyCode",          	paramMap.getStr("companyCode"));
    	arsAuthMap.put("serviceCode",          	paramMap.getStr("serviceCode"));
    	arsAuthMap.put("serviceType",          	paramMap.getStr("serviceType"));
    	arsAuthMap.put("phoneNumber",          	paramMap.getStr("phoneNumber"));
    	arsAuthMap.put("authNumber",   			paramMap.getStr("authNumber"));
    	
    	arsAuthMap.put("INFO1",   				FrameworkUtils.unescapeHtml(paramMap.getStr("INFO1")));   
    	arsAuthMap.put("INFO2",   				FrameworkUtils.unescapeHtml(paramMap.getStr("INFO2")));   
    	
    	String strJSONResult = new HttpRequestProc().sendPacket("https://auth_test.ring2pay.com:7910/authgwSync/asp/arsauth/sync/r2p_auth.do", arsAuthMap);
    	System.out.println("strJSONResult:::"+strJSONResult);
    	
    	try 
    	{
			jsonObjectResult = (JSONObject) new JSONParser().parse(strJSONResult);
		} 
    	catch (ParseException e) 
    	{
			e.printStackTrace();
		}
    	
        try {
        	response.setContentType("text/html; charset=utf-8");
			printWriter = response.getWriter();
			printWriter.print(jsonObjectResult.toString());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

}
