package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.login;

import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz;

@Controller
@RequestMapping("/exe/api/Login")
public class ExeLoginAction
{
	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(ExeLoginAction.class.getName());
	
	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz")
    LoginBiz mBiz;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/auth.do")
	public ResponseEntity syncArsAuth( HttpServletRequest request, HttpServletResponse response )
	{
		// >> uniqid, password
		// >> seq, dpamentId, positionId, uniqId, mberName, mberSttus, moblphonNo, emailAddress, 
		JSONObject requestMessage  = FrameworkUtils.getBody( request );
		
		MyCamelMap responseMap	   = null;
		JSONObject responseMessage = new JSONObject();
		
		MyMap paramMap = new MyMap();
		paramMap.put("uniqid", 			requestMessage.getOrDefault("uniqid", ""));
		paramMap.put("password", 		requestMessage.getOrDefault("password", ""));
		
		responseMap = mBiz.SelectOneData(paramMap);
		
		for( Map.Entry elem : responseMap.entrySet() )
		{
            responseMessage.put(String.valueOf( elem.getKey() ), String.valueOf( elem.getValue() ));
        }
		
		HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK) ;
	}
}
