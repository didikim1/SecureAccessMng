package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comm/api")
public class CommAPIAction
{
    final String pagePrefix = "comtn/bbs";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(CommAPIAction.class.getName());

    @RequestMapping(value ={ "/getPublicIP.do" })
    public @ResponseBody String ListPagingData(Model model, HttpServletRequest request)
    {
        System.out.println("@@@");
        String ip = request.getHeader("X-FORWARDED-FOR");

        if (ip == null || ip.length() == 0)
        {
          ip= request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0)
        {
          ip= request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0)
        {
          ip= request.getRemoteAddr() ;
        }


        return ip;
    }


}
