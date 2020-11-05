package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.login;

import java.util.Enumeration;

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
		JSONObject responseMessage = null;
		
		MyMap paramMap = new MyMap();
		paramMap.put("uniqid", 			requestMessage.getOrDefault("uniqid", ""));
		paramMap.put("password", 		requestMessage.getOrDefault("password", ""));
		
		responseMap = mBiz.SelectOneData(paramMap);
		responseMessage = new JSONObject(responseMap);
		
		HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK) ;
	}
}
