package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.act;

import javax.annotation.Resource;

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

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSMasterBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;

@Controller
@RequestMapping("/ctn/bbs")
public class CtnBBSAction 
{
	 final String pagePrefix  = "comtn/bbs";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(CtnBBSAction.class.getName());

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSBiz")
    BBSBiz mService;

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz")
    FileUploadBiz mFileUploadBiz;
    
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSMasterBiz")
    BBSMasterBiz mBBSMasterBiz;


    @RequestMapping(value ={ "/User/{bbsCode}/ListPagingData.do" })
    public String UserBBSCode(Model model, @PathVariable String bbsCode)
    {
    	MyMap 	  paramMap 		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean bean 	   		= null;
        MyCamelMap bbsMasterMap = null;

        // bbsNm
        paramMap.put("bbsCode", bbsCode);
        bbsMasterMap = mBBSMasterBiz.SelectOneData(paramMap);

        if ( bbsMasterMap == null ) return "";

        paramMap.put("bbsId", mBBSMasterBiz.findOneDataBBSID(bbsCode));

        bean = mService.ListPagingData(paramMap);

        FrameworkUtils.getDateToStr("yyyy-MM");

        paramMap.put("sStartDate",  paramMap.getStr("sStartDate", FrameworkUtils.getDateToStr("yyyy-MM")+"-01"));
        paramMap.put("sEndDate", 	paramMap.getStr("sEndDate",   FrameworkUtils.getDateToStr("yyyy-MM-dd")));

        model.addAttribute("bbsCode",   bbsCode);
        model.addAttribute("data", 		bean);
        model.addAttribute("paramMap",  paramMap);

        return pagePrefix+"/ListPagingData";
    }

    @RequestMapping(value ={ "/User/{bbsCode}/SelectOneData.do" })
    public String SelectOneData(Model model, @PathVariable String bbsCode)
    {
        MyMap 		paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap  objResultMap = null;

        paramMap.put("bbsCode", bbsCode);
        objResultMap = mService.SelectOneData(paramMap);
        
        
        
        objResultMap.put("data", FrameworkUtils.escapeHtml(objResultMap.getStr("nttCn")));
        
        System.out.println("objResultMap::" + objResultMap);
        
        model.addAttribute("data", objResultMap);

        return pagePrefix+"/SelectOneData";
    }

//    @RequestMapping(value ={ "/User/{bbsCode}/RegisterData.do"}, method = RequestMethod.GET)
//    public String RegisterData( @PathVariable String bbsCode, Model model)
//    {
//    	MyMap paramMap			 = new MyMap();
//    	String strBoardSessionID = FrameworkUtils.generateSessionID();
//
//
//    	paramMap.put("ntceBgnde",  FrameworkUtils.getDateToStr("yyyy-MM-dd"));
//        paramMap.put("ntceEndde", 	FrameworkUtils.getDateToStr("yyyy-MM-dd"));
//
//        model.addAttribute("paramMap",   		paramMap);
//    	model.addAttribute("bbsCode",   		bbsCode);
//    	model.addAttribute("SESSION_BOARD_ID",  strBoardSessionID);
//
//    	return pagePrefix+"/RegisterData";
//    }

//    @RequestMapping(value ={ "/User/{bbsCode}/RegisterData.do"}, method = RequestMethod.POST)
//    public ModelAndView  RegisterData(Model model, @PathVariable String bbsCode)
//    {
//        MyMap 		paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//        int			iResult		 = 0;
//
//        paramMap.put("bbsId", mBBSMasterBiz.findOneDataBBSID(bbsCode));
//
//        iResult = mService.RegisterData(paramMap);
//
//        mFileUploadBiz.RegisterProcFileupload();
//
//        return new ModelAndView("redirect:/comtn/bbs/User/"+bbsCode+"/ListPagingData.do");
//
//    }
//    
//    @RequestMapping(value ={ "/User/{bbsCode}/RegisterData.do"}, method = RequestMethod.POST)
//    public ResponseEntity RegisterData(  @PathVariable String bbsCode )
//    {
//    	 MyMap 		 paramMap 	 		   = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//         int		 iResult		 	   = 0;
//         JSONObject  responseArrayMessage  = new JSONObject();
//         HttpHeaders resHeaders 		   = new HttpHeaders();
//
//         paramMap.put("bbsId", mBBSMasterBiz.findOneDataBBSID(bbsCode));
//
//         iResult = mService.RegisterData(paramMap);
//
//         mFileUploadBiz.RegisterProcFileupload();
//         
//         responseArrayMessage.put("result", ResultCode.RESULT_UNAUTHORIZED);
//         if ( iResult > 0 )
//         {
//        	 responseArrayMessage.put("result", ResultCode.RESULT_OK);
//         }
//         
//		 resHeaders.add("Content-Type", "application/json;charset=UTF-8");
//    	
//    	return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
//    }
    

    @RequestMapping(value ={ "/User/{bbsCode}/ModifyData.do" }, method = RequestMethod.GET)
    public String ModifyData(@PathVariable String bbsCode, Model model)
    {
    	MyMap 		paramMap 	 = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap  objResultMap = null;

        objResultMap = mService.SelectOneData(paramMap);

        model.addAttribute("data", objResultMap);

    	return pagePrefix+"/ModifyData";
    }

    @RequestMapping(value ={ "/User/{bbsCode}/ModifyData.do" }, method = RequestMethod.POST)
    public ModelAndView ModifyData(Model model, @PathVariable String bbsCode)
    {
        MyMap paramMap 		= FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        paramMap.put("lastUpdusrId", paramMap.getStr("SESSION_USER_ID"));

        mService.ModifyData(paramMap);

//        mFileUploadBiz.RegisterProcFileupload();

        return new ModelAndView("redirect:/comtn/bbs/User/"+bbsCode+"/SelectOneData.do?nttId="+paramMap.getInt("nttId"));
    }

    @RequestMapping(value ={ "/User/{bbsCode}/DeleteData.do" })
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
