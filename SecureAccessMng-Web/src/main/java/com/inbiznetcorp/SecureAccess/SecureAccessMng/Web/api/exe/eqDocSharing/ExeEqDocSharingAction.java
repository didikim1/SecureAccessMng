package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.exe.eqDocSharing;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.bbs.act.ExeCtnBBSAction;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSMasterBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileDownLoadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqDocSharing.biz.EqDocSharingBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAllowIP.EqAllowIPMapper;

@Controller
@RequestMapping("/exe/eq/DocSharing")
public class ExeEqDocSharingAction 
{
    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(ExeCtnBBSAction.class.getName());

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqDocSharing.biz.EqDocSharingBiz")
    EqDocSharingBiz mService;

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
    
    
//    @RequestMapping("/eqAllowIP.do")
//    public ResponseEntity eqAllowIP(HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
//    	MyMap 				paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//    	List<MyCamelMap> responseList	 = null;
//    	JSONArray        responseMessage = new JSONArray();		 
//    	
//    	responseList = mEqAllowIPMapper.ListPagingData(paramMap);
//    	
//    	for (MyCamelMap info : responseList) 
//    	{
//    		responseMessage.add(new JSONObject(info));
//		}
//    	
//    	HttpHeaders resHeaders = new HttpHeaders();
//	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
//		
//		return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK);
//    }
//    
//    @RequestMapping("/ctnNrlmber.do")
//    public ResponseEntity ctnNrlmber(HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
//    	MyMap 				paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//    	List<MyCamelMap> responseList	 = null;
//    	JSONArray        responseMessage = new JSONArray();		 
//    	
//    	responseList = mNrlmberBiz.ListPagingData(paramMap).getList();
//    	
//    	for (MyCamelMap info : responseList) 
//    	{
//    		responseMessage.add(new JSONObject(info));
//		}
//    	
//    	HttpHeaders resHeaders = new HttpHeaders();
//	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
//		
//	    new ResultMap(ResultCode.RESULT_OK, result)
//	    
//		return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK);
//    }
    
    @RequestMapping("/DownLoadFile.do")
	public void DonwLoadFile(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MyMap 				paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		mFileDownLoadBiz.downloadFile(response, paramMap);
	}
    
    @RequestMapping(value ={ "/User/DOCSHARING/ListPagingData.do" })
    public ResponseEntity UserBBSCode(Model model)
    {
    	MyMap 	  paramMap 		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean bean 	   		= null;
        MyCamelMap bbsMasterMap = null;

        // bbsNm
        paramMap.put("bbsCode", "DOCSHARING");
        bbsMasterMap = mBBSMasterBiz.SelectOneData(paramMap);


        paramMap.put("bbsId", mBBSMasterBiz.findOneDataBBSID("DOCSHARING"));

        bean = mService.ListPagingData(paramMap);
        
        HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<Object>(new ResultMessage(ResultCode.RESULT_OK, bean), resHeaders, HttpStatus.OK);
    }

    @RequestMapping(value ={ "/User/DOCSHARING/SelectOneData.do" })
    public ResponseEntity SelectOneData()
    {
        MyMap 		paramMap 	 	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap  objResultMap 	= null;
        JSONObject  responseMessage = null;

        paramMap.put("bbsCode", "DOCSHARING");
        objResultMap = mService.SelectOneData(paramMap);
        
        responseMessage = new JSONObject(objResultMap);
        

        HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(responseMessage.toString(), resHeaders, HttpStatus.OK);
    }

    
    @SuppressWarnings("unchecked")
	@RequestMapping(value ={ "/User/DOCSHARING/RegisterData.do"}, method = RequestMethod.POST)
    public ResponseEntity RegisterData(  )
    {
    	 MyMap 		 paramMap 	 		   = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
         int		 iResult		 	   = 0;
         JSONObject  responseArrayMessage  = new JSONObject();
         HttpHeaders resHeaders 		   = new HttpHeaders();
         
         String SESSION_BOARD_ID		   = FrameworkUtils.generateSessionID();
         
         paramMap.put("bbsId", 				mBBSMasterBiz.findOneDataBBSID("DOCSHARING"));
         paramMap.put("SESSION_BOARD_ID", 	SESSION_BOARD_ID);
         
         iResult = mService.RegisterData(paramMap);
         
         mFileUploadBiz.RegisterProcFileupload( SESSION_BOARD_ID );
         
         responseArrayMessage.put("result", ResultCode.RESULT_UNAUTHORIZED);
         if ( iResult > 0 )
         {
        	 responseArrayMessage.put("result", ResultCode.RESULT_OK);
         }
         
		 resHeaders.add("Content-Type", "application/json;charset=UTF-8");
    	
    	return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
    }

    @RequestMapping(value ={ "/User/DOCSHARING/DeleteData.do" })
    public ModelAndView DeleteData(@PathVariable String bbsCode, Model model)
    {
        MyMap 	 paramMap   = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        String[] arrNTTId 	= paramMap.getStr("nttId").split("\\,");

        for (String nttId : arrNTTId)
        {
        	MyMap deleteMap  = new MyMap();

        	deleteMap.put("nttId", nttId);
        	mService.DeleteData(deleteMap);
		}
        return new ModelAndView("redirect:/comtn/bbs/User/"+bbsCode+"/ListPagingData.do");
    }
}
