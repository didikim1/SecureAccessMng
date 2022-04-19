package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.config.interceptor;

import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;


public class AuthenticationInterceptor implements HandlerInterceptor
{
   String[] UN_CKECKURLS = new String[]{"/index", "/loginProc", "/error/error500"};

   final String  kLogNewLine             = "\r\n";

    final String kProfiles_Active_Local  = "local";
    final String kProfiles_Active_Test   = "test";
    final String kProfiles_Active_PRD    = "prd";


    @Override
    public boolean preHandle(HttpServletRequest _httpServletRequest, HttpServletResponse _httpServletResponse, Object _handler) throws Exception
    {

        String url = _httpServletRequest.getAttribute("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping").toString();
        _httpServletRequest.setAttribute("MAPPING_URL", url);

        StringBuffer sbReqMessage = new StringBuffer();


        FrameworkBeans.setHttpServletBean(_httpServletRequest, _httpServletResponse);

        Logger.info( "URL:"+url);

        sbReqMessage.append(kLogNewLine+"==========================LAB603=================================="+kLogNewLine);
        sbReqMessage.append(" Request URL=" + url + kLogNewLine);
        sbReqMessage.append(" Connetion IPAddr=" + _httpServletRequest.getRemoteAddr() + kLogNewLine);
        sbReqMessage.append(" Connetion X-Forwarded-For=" + _httpServletRequest.getHeader("x-forwarded-for") + kLogNewLine);
        for (Entry<Object, Object> elem : FrameworkBeans.findHttpServletBean().findClientRequestParameter().entrySet())
        {
            String mapKey = (String) elem.getKey();
//            System.out.println("=="+mapKey);
            String mapVal = (String) elem.getValue();

            sbReqMessage.append("\t " + mapKey + " = " + mapVal + kLogNewLine);
        }

        sbReqMessage.append("==========================//LAB603==================================" + kLogNewLine);

        Logger.info( sbReqMessage.toString() );


        CommonBiz commonBiz = (CommonBiz) FrameworkBeans.findBean("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz");
//        System.out.println("active = " + commonBiz.active);

        switch (commonBiz.active)
        {
        case kProfiles_Active_Local:

            if(FrameworkUtils.isNull(FrameworkBeans.findSessionBean().roleId))
            {
				//FrameworkBeans.findSessionBean().roleId = "8"; 
            }

            if(FrameworkUtils.isNull(FrameworkBeans.findSessionBean().dpamentId))
            {

            }

            if(FrameworkUtils.isNull(FrameworkBeans.findSessionBean().mberName))
            {

            }

            return true;
        default:
                if( 	    url.indexOf("/login/") >= 0
                		|| 	url.indexOf("/login/index.do") >= 0
                		|| 	url.indexOf("/login/SelectOneData.do") >= 0
                		|| 	url.indexOf("/login/DeleteData.do") >= 0
                		|| 	url.indexOf("/comm/api/getPublicIP.do") >= 0
                		|| 	url.indexOf("api") >= 0
                	)
                {
                	return true;
                }
                else
                {
                	if( FrameworkUtils.isNull( FrameworkBeans.findSessionBean().mberSeq ))
                	{
                		FrameworkBeans.findHttpServletBean().getHttpServletResponse().sendRedirect("/login/index.do");
                		return true;
                	}
                	else
                	{
                		return true;
                	}
                }
        }


    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    	request.setAttribute("dpamentId",  FrameworkBeans.findSessionBean().dpamentId);
    	request.setAttribute("roleId",     FrameworkBeans.findSessionBean().roleId);
    	request.setAttribute("mberName",   FrameworkBeans.findSessionBean().mberName);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }

    public static final Logger Logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
}