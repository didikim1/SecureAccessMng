package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.act;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkPagingUtils;


@Controller
public class CommonAction
{
	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(CommonAction.class.getName());



    @RequestMapping(value = "/jqGrid/init")
    public @ResponseBody ResultMessage init(Model model) throws Exception
    {
    	MyMap 		paramMap 	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
    	BasicBean 	resultBean 	= null;

    	FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 10));

    	resultBean = FrameworkPagingUtils.pagingData(paramMap, paramMap.getInt("rows", 10),
						0, new ArrayList<MyCamelMap>());

        return new ResultMessage("200", resultBean);
    }


}
