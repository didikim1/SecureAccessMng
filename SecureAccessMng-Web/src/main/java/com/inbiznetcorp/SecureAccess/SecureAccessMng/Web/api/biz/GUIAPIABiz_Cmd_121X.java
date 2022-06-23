package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz;

/**
 * 회원
 * @author hyo
 *
 */
@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_121X")
public class GUIAPIABiz_Cmd_121X
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz")
    LoginBiz mBiz;

    @SuppressWarnings("unchecked")
    public JSONObject Cmd_1214(JSONObject requestMessage, HttpServletRequest request)
    {
        MyMap           paramMap                 = new MyMap();
        MyMap           returnMap                = null;
        JSONObject      responseMessage          = new JSONObject();

        paramMap.put("uniqid",                  requestMessage.getOrDefault("uniqid", ""));
        paramMap.put("uniqId",                  requestMessage.getOrDefault("uniqid", ""));
        paramMap.put("password",                requestMessage.getOrDefault("password", ""));


        returnMap = mBiz.SelectOneData(paramMap);

        System.out.println( " returnMap : " + returnMap);

        for( Map.Entry elem : returnMap.entrySet() )
        {
            responseMessage.put(String.valueOf( elem.getKey() ), String.valueOf( elem.getValue() ));
        }

        return responseMessage;
    }
}
