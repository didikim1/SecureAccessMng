package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.httpClient.RequestProc;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.httpClient.RequestProcJSON;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz")
public class CommonBiz
{
    @Value("${spring.profiles.active}")
    public String active;


    public JSONObject authCallSender(String phoneNumber, String authNumber)
    {
        JSONObject          strJSONObject = new JSONObject();

        // 인증
        strJSONObject.put("authReqNumber",        "TEST_"+FrameworkUtils.generateSessionID());
        strJSONObject.put("requestTime",          FrameworkUtils.getDateToStr());
        strJSONObject.put("companyCode",          "43418");
        strJSONObject.put("serviceCode",          "0001");
        strJSONObject.put("serviceType",          "01");
        strJSONObject.put("phoneNumber",          phoneNumber); // 전화번호
        strJSONObject.put("authNumber",           authNumber); // 전화번호

        JSONObject jsonObject = new RequestProcJSON().sendPacket("https://auth_test.ring2pay.com:7891/asp/arsauth/sync/rest/r2p_paauth.do", strJSONObject.toString());

        System.out.println("jsonObject : " + jsonObject);

        return jsonObject;
    }

    @SuppressWarnings("unchecked")
    public JSONObject authCallDynamicSender(String phoneNumber, String authNumber, String intro1, String intro2)
    {
        @SuppressWarnings("rawtypes")
        HashMap  paramMap= new HashMap();

        // 인증
        paramMap.put("authReqNumber",        "TEST_"+FrameworkUtils.generateSessionID());
        paramMap.put("requestTime",          FrameworkUtils.getDateToStr());
        paramMap.put("companyCode",          "70010");
        paramMap.put("serviceCode",          "0001");
        paramMap.put("serviceType",          "03");
        paramMap.put("phoneNumber",          phoneNumber); // 전화번호
        paramMap.put("authNumber",           authNumber); // 전화번호

        paramMap.put("INFO1",                  intro1); // 안내멘트
        paramMap.put("INFO2",                  intro2); // 안내멘트

        JSONObject jsonObject = new RequestProc().sendPacket("https://auth_test2.ring2pay.com:7910/authgwSync/asp/arsauth/sync/r2p_auth.do", paramMap);

        System.out.println("jsonObject : " + jsonObject);

        return jsonObject;
    }
}
