package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.httpClient.HttpRequestProc;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAccAllowMng.EqAccAllowMngMapper;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_131X")
public class GUIAPIABiz_Cmd_131X
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAccAllowMng.EqAccAllowMngMapper")
    EqAccAllowMngMapper mMapper;

    /**
     * 등록
     * @param requestMessage
     * @param request
     * @return
     */
    public JSONObject Cmd_1310(JSONObject requestMessage, HttpServletRequest request)
    {
        return null;
    }

    /**
     * 변경
     * @param requestMessage
     * @param request
     * @return
     */
    public JSONObject Cmd_1311(JSONObject requestMessage, HttpServletRequest request)
    {
        return null;
    }

    /**
     * 삭제
     * @param requestMessage
     * @param request
     * @return
     */
    public JSONObject Cmd_1312(JSONObject requestMessage, HttpServletRequest request)
    {
        return null;
    }

    /**
     * 조회
     * @param requestMessage
     * @param request
     * @return
     */
    public JSONObject Cmd_1313(JSONObject requestMessage, HttpServletRequest request)
    {
        MyMap           paramMap                 = new MyMap();
        MyMap           returnMap                = null;
        JSONObject      responseMessage          = new JSONObject();

        paramMap.put("sttus",      requestMessage.getOrDefault("sttus", "A")); // A 활성화

        returnMap = mMapper.SelectOneData(paramMap);

        if ( returnMap == null )
        {
            returnMap = new MyMap();
        }

        System.out.println( " returnMap : " + returnMap);

        for( Map.Entry elem : returnMap.entrySet() )
        {
            responseMessage.put(String.valueOf( elem.getKey() ), String.valueOf( elem.getValue() ));
        }

        return responseMessage;
    }

    /**
     * @param requestMessage
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public JSONObject Cmd_1314(JSONObject requestMessage, HttpServletRequest request)
    {
        MyMap           paramMap                 = new MyMap();
        MyMap           returnMap                = null;
        JSONObject      responseMessage          = new JSONObject();

        String          paramName                = null;
        String          paramWorkType            = null;
        String          paramIDC                 = null;
        String          paramServerIP            = null;
        String          paramMdctId              = null;

        HashMap         approvalParamMap         = null;
        JSONObject      approvalResponseMessage  = null;
        String          INFO1                    = null;
        String          INFO2                    = null;

        paramMap.put("sttus",      requestMessage.getOrDefault("sttus", "A")); // A 활성화

        returnMap = mMapper.SelectOneData(paramMap);

        paramName       = requestMessage.getOrDefault("name", "").toString();
        paramWorkType   = requestMessage.getOrDefault("workType", "").toString();
        paramIDC        = requestMessage.getOrDefault("idc", "").toString();
        paramServerIP   = requestMessage.getOrDefault("serverIP", "").toString();
        paramMdctId     = requestMessage.getOrDefault("mdctId", "").toString();
        INFO1           = "안녕하세요? 관리자님, "+paramName+" 님이, "+paramWorkType+" 작업을 위해,  "+paramIDC+", "+paramServerIP+",,, 아이디, "+FrameworkUtils.separateTextComma(paramMdctId)+", 으로 서버 접속을 요청하였습니다. 접속을 허용하시려면, 등록된 관리자님의 인증번호를 입력해주세요.";
        INFO2           = "관리자님, 관리자님, "+paramName+" 님이, "+paramWorkType+" 작업을 위해,  "+paramIDC+", "+paramServerIP+",,, 아이디, "+FrameworkUtils.separateTextComma(paramMdctId)+", 으로 서버 접속을 요청하였습니다. 접속을 허용하시려면, 등록된 관리자님의 인증번호를 입력해주세요.";

        approvalParamMap = new HashMap();

        approvalParamMap.put("authReqNumber",           FrameworkUtils.generateSessionID());
        approvalParamMap.put("requestTime",             FrameworkUtils.getDateToStr());
        approvalParamMap.put("companyCode",             "70010");
        approvalParamMap.put("serviceCode",             "0001");
        approvalParamMap.put("serviceType",             "03");
        approvalParamMap.put("phoneNumber",             returnMap.getStr("phonnumber", ""));
        approvalParamMap.put("authNumber",              returnMap.getStr("authnumber", ""));
        approvalParamMap.put("INFO1",                   INFO1);
        approvalParamMap.put("INFO2",                   INFO2);

        approvalResponseMessage = new HttpRequestProc().sendPacket2("https://auth_test.ring2pay.com:7910/authgwSync/asp/arsauth/sync/r2p_auth.do", approvalParamMap);

        return approvalResponseMessage;
    }

}
