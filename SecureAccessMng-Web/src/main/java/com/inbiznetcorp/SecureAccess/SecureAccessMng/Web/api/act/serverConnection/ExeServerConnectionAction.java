package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.serverConnection;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper;


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
	 
	 @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz")
	 EqAccLogBiz mEqAccLogBiz;
	 
	 // 작업구분
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 @RequestMapping("/workList.do")
	 public ResponseEntity workList( HttpServletRequest request, HttpServletResponse response )
	 {
			// >> title = 'WORK_TYPE', type = 'B'
			// >> seq, dpamentId, positionId, uniqId, mberName, mberSttus, moblphonNo, emailAddress, 
			JSONObject requestMessage  = FrameworkUtils.getBody( request );
			
			List<MyCamelMap> responseList	    = null;
			JSONArray      responseArrayMessage = null;
			
			MyMap paramMap = new MyMap();
			paramMap.put("title", 			requestMessage.getOrDefault("title", "WORK_TYPE"));
			paramMap.put("type", 			requestMessage.getOrDefault("type", "B"));
			
			responseList = mCodeMapper.ListData(paramMap);
			
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
	 @SuppressWarnings({ "rawtypes"})
	 @RequestMapping("/idcList.do")
	 public ResponseEntity idcList( HttpServletRequest request, HttpServletResponse response )
	 {
			JSONObject requestMessage  = FrameworkUtils.getBody( request );
			
			List<MyCamelMap> responseList	    = null;
			JSONArray      responseArrayMessage = null;
			
			MyMap paramMap = new MyMap();
			
			responseList = mEqListBiz.ListData(paramMap).getList();
			
			responseArrayMessage = new JSONArray();
			
			for (MyCamelMap info : responseList) 
			{
				responseArrayMessage.add(new JSONObject(info));
			}
			
			HttpHeaders resHeaders = new HttpHeaders();
		    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
			
			return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
	 }
	 
	 
	 // 서버 정보
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 @RequestMapping("/serverList.do")
	 public ResponseEntity serverList( HttpServletRequest request, HttpServletResponse response )
	 {
			JSONObject requestMessage  = FrameworkUtils.getBody( request );
			
			List<MyCamelMap> responseList	    = null;
			JSONArray      responseArrayMessage = null;
			
			System.out.println("requestMessage:" + requestMessage);
			
			
			String  strRefEqIdc = requestMessage.getOrDefault("refEqIdc", "0").toString();
			int 	refEqIdc 	= Integer.valueOf( strRefEqIdc );
			
			MyMap paramMap = new MyMap();
			paramMap.put("refEqIdc", refEqIdc);
			
			responseList = mEqListBiz.ListData(paramMap).getList();
			
			responseArrayMessage = new JSONArray();
			
			for (MyCamelMap info : responseList) 
			{
				responseArrayMessage.add(new JSONObject(info));
			}
			
			HttpHeaders resHeaders = new HttpHeaders();
		    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
			
			return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
	 }
	 
	 // 계정 정보
	 @SuppressWarnings("unchecked")
	 @RequestMapping("/idpwdList.do")
	 public ResponseEntity idpwdList( HttpServletRequest request, HttpServletResponse response )
	 {
			JSONObject requestMessage  = FrameworkUtils.getBody( request );
			
			List<MyCamelMap> responseList	    = null;
			JSONArray      responseArrayMessage = null;
			
			
			System.out.println("requestMessage: " + requestMessage);
			String  strRefEqList = requestMessage.getOrDefault("refEqList", "0").toString();
			int 	refEqList 	= Integer.valueOf( strRefEqList );
			
			MyMap paramMap = new MyMap();
			paramMap.put("refEqList", refEqList);
			
			responseList = mEqIdpwdBiz.ListData(paramMap).getList();
			
			responseArrayMessage = new JSONArray();
			
			for (MyCamelMap info : responseList) 
			{
				responseArrayMessage.add(new JSONObject(info));
			}
			
			HttpHeaders resHeaders = new HttpHeaders();
		    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
			
			return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
	 }
	 
	 /*
	  *  		#{worktypecodeId} 		<!-- 업무코드 ID -->
				,#{refEqIdc} 			<!-- IDC고유 SEQ -->
				,#{refEqList} 			<!-- 서버고유SEQ -->
				,#{refEqIdpwd} 			<!-- 모듈고유SEQ -->
				,#{refNrlmber}			<!-- 회원고유번호 SEQ -->
				,#{refAllowIp}			<!-- 허용IP SEQ -->
				,#{processID}			<!-- 프로세스 ID -->
				,'A'					<!-- 처리상태(A) -->
				,#{reason}				<!-- 사유 -->
				,now()					<!-- 최초등록ID -->
	  */
	 @SuppressWarnings("unchecked")
	@RequestMapping("/mEqAccLogBiz.do")
	 public ResponseEntity mEqAccLogBiz( HttpServletRequest request, HttpServletResponse response )
	 {
		 JSONObject requestMessage  = FrameworkUtils.getBody( request );
		 
		 List<MyCamelMap> responseList	    = null;
		 JSONObject       responseMessage 	= null;
		 int			  intRtnValue		= 0;
		 
		 System.out.println("requestMessage: " + requestMessage);
		 
		 MyMap paramMap = new MyMap();
		 paramMap.put("worktypecodeId",  Integer.valueOf( requestMessage.getOrDefault("worktypecodeId", "0").toString() ));
		 paramMap.put("refEqIdc", 		 Integer.valueOf( requestMessage.getOrDefault("refEqIdc", "0").toString() ));
		 paramMap.put("refEqList",       Integer.valueOf( requestMessage.getOrDefault("refEqList", "0").toString() ));
		 paramMap.put("refEqIdpwd", 	 Integer.valueOf( requestMessage.getOrDefault("refEqIdpwd", "0").toString() ));
		 paramMap.put("refNrlmber", 	 Integer.valueOf( requestMessage.getOrDefault("refNrlmber", "0").toString() ));
		 paramMap.put("refAllowIp", 	 Integer.valueOf( requestMessage.getOrDefault("refAllowIp", "0").toString() ));
		 paramMap.put("processID", 		 requestMessage.getOrDefault("processID", "").toString());
		 paramMap.put("reason", 		 requestMessage.getOrDefault("reason", "").toString());
		 
		 
		 
		 intRtnValue = mEqAccLogBiz.RegisterData(paramMap);
		 
		 responseMessage = new JSONObject();
		 
		 responseMessage.put("resultCode", "99");
		 if ( intRtnValue > 0)
		 {
			 responseMessage.put("resultCode", "00");
		 }
		 
		 HttpHeaders resHeaders = new HttpHeaders();
		 resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		 
		 return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK);
	 }
}
