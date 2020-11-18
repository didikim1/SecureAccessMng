package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.exe.eqDepoly;

import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSMasterBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileDownLoadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqDepoly.biz.EqDepolyBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper;

@Controller
@RequestMapping("/exe/eq/Depoly")
public class ExEEqDepolyAction 
{	
//	  @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSBiz")
//	  BBSBiz mService;
	  
	  @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqDepoly.biz.EqDepolyBiz")
	  EqDepolyBiz mDepolyBiz;
	
	  @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz")
	  FileUploadBiz mFileUploadBiz;
	    
	  @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSMasterBiz")
	  BBSMasterBiz mBBSMasterBiz;
	    
	  @Resource(name="com.lab603.albert.blog.common.biz.FileDownLoadBiz")
	  FileDownLoadBiz mFileDownLoadBiz;
	    
	  @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper")
	  EqAllowIPMapper mEqAllowIPMapper;
	    
	  @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz")
	  NrlmberBiz mNrlmberBiz;
	  
	 @RequestMapping(value ={ "/User/DEPOLY/ListLatestData.do" })
     public ResponseEntity ListLatestData()
     {
    	MyMap 	  			paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap 			bbsMasterMap 			= null;
        List<MyCamelMap> 	resultList 				= null;
        JSONArray     	 	responseArrayMessage 	= null;
        
        System.out.println("@@@@@@@@@@@@");
        
        // bbsNm
        paramMap.put("bbsCode", "DEPOLY");
        bbsMasterMap = mBBSMasterBiz.SelectOneData(paramMap);

        if ( bbsMasterMap == null ) return null;

        paramMap.put("bbsId", mBBSMasterBiz.findOneDataBBSID("DEPOLY"));

        resultList = mDepolyBiz.ListPagingData(paramMap).getList();

        FrameworkUtils.getDateToStr("yyyy-MM");
        
        paramMap.put("sStartDate",  paramMap.getStr("sStartDate", FrameworkUtils.getDateToStr("yyyy-MM")+"-01"));
        paramMap.put("sEndDate", 	paramMap.getStr("sEndDate",   FrameworkUtils.getDateToStr("yyyy-MM-dd")));
        
        responseArrayMessage = new JSONArray();
		
        System.out.println("@@@@@@@@@@@@");
        
		for (MyCamelMap info : resultList) 
		{
			responseArrayMessage.add(new JSONObject(info));
		}
		
		HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
    }
	  
	  @SuppressWarnings("unchecked")
	  @RequestMapping(value ={ "/User/DEPOLY/RegisterData.do"}, method = RequestMethod.POST)
	  public ResponseEntity RegisterData(   )
	  {
	    	 MyMap 		 paramMap 	 		   = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
	         int		 iResult		 	   = 0;
	         JSONObject  responseArrayMessage  = new JSONObject();
	         HttpHeaders resHeaders 		   = new HttpHeaders();
	         
	         String SESSION_BOARD_ID		   = FrameworkUtils.generateSessionID();
	         
	         paramMap.put("bbsId", 				mBBSMasterBiz.findOneDataBBSID("DEPOLY"));
	         paramMap.put("SESSION_BOARD_ID", 	SESSION_BOARD_ID);
	         
	         iResult = mDepolyBiz.RegisterData(paramMap);
	         
	         mDepolyBiz.RegisterDataDepoly(paramMap);
	         
	         mFileUploadBiz.RegisterProcFileupload( SESSION_BOARD_ID );
	         
	         
	         responseArrayMessage.put("result", ResultCode.RESULT_UNAUTHORIZED);
	         if ( iResult > 0 )
	         {
	        	 responseArrayMessage.put("result", ResultCode.RESULT_OK);
	         }
	         
			 resHeaders.add("Content-Type", "application/json;charset=UTF-8");
	    	
	    	return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
	 }
}
