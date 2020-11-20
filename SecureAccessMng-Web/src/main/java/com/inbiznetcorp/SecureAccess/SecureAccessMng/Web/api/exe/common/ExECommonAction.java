package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.exe.common;

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

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSMasterBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileDownLoadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper;

@Controller
@RequestMapping("/exe/Common")
public class ExECommonAction 
{
	
	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSBiz")
    BBSBiz mService;

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
	
	@RequestMapping("/eqAllowIP.do")
    public ResponseEntity eqAllowIP(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	MyMap 				paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
    	List<MyCamelMap> responseList	 = null;
    	JSONArray        responseMessage = new JSONArray();		 
    	
    	responseList = mEqAllowIPMapper.ListPagingData(paramMap);
    	
    	for (MyCamelMap info : responseList) 
    	{
    		responseMessage.add(new JSONObject(info));
		}
    	
    	HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK);
    }
    
    @RequestMapping("/ctnNrlmber.do")
    public ResponseEntity ctnNrlmber(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	MyMap 				paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
    	List<MyCamelMap> responseList	 = null;
    	JSONArray        responseMessage = new JSONArray();		 
    	
    	responseList = mNrlmberBiz.ListPagingData(paramMap).getList();
    	
    	for (MyCamelMap info : responseList) 
    	{
    		responseMessage.add(new JSONObject(info));
		}
    	
    	HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK);
    }
    
    @RequestMapping("/DownLoadFile.do")
	public void DonwLoadFile(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	System.out.println("@@@@");
		MyMap 				paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		mFileDownLoadBiz.downloadFile(response, paramMap);
	}
}
