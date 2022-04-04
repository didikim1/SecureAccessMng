package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.config.interceptor;

import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;

public class AuthenticationInterceptor implements HandlerInterceptor
{
	String[] UN_CKECKURLS = new String[]{"/index", "/loginProc", "/error/error500"};
	
	// spring.profiles.active=${:local}
	
//	@Value("${spring.profiles.active}")
//    private String active;
	
	
    @Override
    public boolean preHandle(HttpServletRequest _httpServletRequest, HttpServletResponse _httpServletResponse, Object _handler) throws Exception
    {
    	String 	strNewLine = "\r\n";

        String url = _httpServletRequest.getAttribute("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping").toString();
        _httpServletRequest.setAttribute("MAPPING_URL", url);

        StringBuffer sbReqMessage = new StringBuffer();


        FrameworkBeans.setHttpServletBean(_httpServletRequest, _httpServletResponse);

        Logger.info( "URL:"+url);

        sbReqMessage.append(strNewLine+"==========================LAB603=================================="+strNewLine);
        sbReqMessage.append(" Request URL=" + url + strNewLine);
        sbReqMessage.append(" Connetion IPAddr=" + _httpServletRequest.getRemoteAddr() + strNewLine);
        sbReqMessage.append(" Connetion X-Forwarded-For=" + _httpServletRequest.getHeader("x-forwarded-for") + strNewLine);
        for (Entry<Object, Object> elem : FrameworkBeans.findHttpServletBean().findClientRequestParameter().entrySet())
        {
            String mapKey = (String) elem.getKey();
            String mapVal = (String) elem.getValue();

            sbReqMessage.append("\t " + mapKey + " = " + mapVal + strNewLine);
        }

        sbReqMessage.append("==========================//LAB603==================================" + strNewLine);

        Logger.info( sbReqMessage.toString() );
        
//        System.out.println("active = " + active);
//        System.out.println("active = " + active);
//        System.out.println("active = " + active);
//        System.out.println("active = " + active);
//        System.out.println("active = " + active);
//        System.out.println("active = " + active);
        
        if(FrameworkUtils.isNull(FrameworkBeans.findSessionBean().roleId))
        {
        	FrameworkBeans.findSessionBean().roleId = "8";
        }
        
        return true;

        
       // 임시주석
       /*
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
        */
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    	request.setAttribute("SessionBean_dpamentId", FrameworkBeans.findSessionBean().dpamentId);
    	request.setAttribute("SessionBean_roleId", FrameworkBeans.findSessionBean().roleId);
    	request.setAttribute("SessionBean_mberName", FrameworkBeans.findSessionBean().mberName);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }

    public static final Logger Logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
}