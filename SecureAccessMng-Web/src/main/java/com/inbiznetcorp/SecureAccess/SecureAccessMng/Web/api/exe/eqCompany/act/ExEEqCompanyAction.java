package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.exe.eqCompany.act;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqCompany.service.EqCompanyBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;

@Controller
@RequestMapping("/exe/eq/Company")
public class ExEEqCompanyAction 
{
	 @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqCompany.service.EqCompanyBiz")
	 EqCompanyBiz mBiz;
	 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value ={ "/ListData.do" })
    public ResponseEntity ListLatestData(HttpServletRequest request, HttpServletResponse response)
    {
    	MyMap 	  			paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        List<MyCamelMap> 	resultList 				= null;
        JSONArray     	 	responseArrayMessage 	= null;

        resultList = mBiz.ListData(paramMap).getList();
        
        responseArrayMessage = new JSONArray();
		
		for (MyCamelMap info : resultList) 
		{
			responseArrayMessage.add(new JSONObject(info));
		}
		
		HttpHeaders resHeaders = new HttpHeaders();
	    resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(responseArrayMessage.toString(), resHeaders, HttpStatus.OK);
    }
}
