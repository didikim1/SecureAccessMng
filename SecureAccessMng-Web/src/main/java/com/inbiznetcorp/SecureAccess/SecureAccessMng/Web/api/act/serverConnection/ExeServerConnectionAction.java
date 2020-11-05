package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.serverConnection;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper;

import net.minidev.json.JSONArray;

@Controller
@RequestMapping("/exe/api/ServerConnection")
public class ExeServerConnectionAction 
{
	 @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper")
	 CodeMapper mCodeMapper;
	 
	 @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz")
     EqIdcBiz 	mEqIdcBiz;
	 
	 @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
	 EqListBiz  mEqListBiz;
	 
	 @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz")
	 EqIdpwdBiz mEqIdpwdBiz;
	 
	 // 작업구분
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 @RequestMapping("/codeList.do")
	 public ResponseEntity codeList( HttpServletRequest request, HttpServletResponse response )
	 {
			// >> title = 'WORK_TYPE', type = 'B'
			// >> seq, dpamentId, positionId, uniqId, mberName, mberSttus, moblphonNo, emailAddress, 
			JSONObject requestMessage  = FrameworkUtils.getBody( request );
			
			List<MyCamelMap> responseList	    = null;
			JSONArray      responseArrayMessage = null;
			
			MyMap paramMap = new MyMap();
			paramMap.put("title", 			requestMessage.getOrDefault("title", "WORK_TYPE"));
			paramMap.put("type", 			requestMessage.getOrDefault("type", "B"));
			
			responseList = mCodeMapper.ListPagingData(paramMap);
			
			responseArrayMessage = new JSONArray();
			
			for (MyCamelMap info : responseList) 
			{
				responseArrayMessage.add(new JSONObject(info));
			}
			
			HttpHeaders resHeaders = new HttpHeaders();
		    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
			
			return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
	 }
	 
	 // IDC
	 
	 // 서버 정보
	 
	 // 계정 정보
}
