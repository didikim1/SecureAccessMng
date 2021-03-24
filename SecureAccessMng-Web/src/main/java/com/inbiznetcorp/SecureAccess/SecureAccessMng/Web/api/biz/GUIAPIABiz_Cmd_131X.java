package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.httpClient.HttpRequestProc;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAccAllowMng.EqAccAllowMngMapper;

import net.minidev.json.JSONArray;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_131X")
public class GUIAPIABiz_Cmd_131X
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqAccAllowMng.EqAccAllowMngMapper")
    EqAccAllowMngMapper mMapper;

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz")
    EqAccLogBiz mEqAccLogBiz;


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

    @SuppressWarnings("unchecked")
    public JSONObject Cmd_1315(JSONObject requestMessage, HttpServletRequest request)
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

        paramMap.put("sttus",      requestMessage.getOrDefault("sttus", "A")); // A 활성화

        returnMap = mMapper.SelectOneData(paramMap);

        paramName       = requestMessage.getOrDefault("name", "").toString();
        paramWorkType   = requestMessage.getOrDefault("workType", "").toString();
        paramIDC        = requestMessage.getOrDefault("idc", "").toString();
        paramServerIP   = requestMessage.getOrDefault("serverIP", "").toString();
        paramMdctId     = requestMessage.getOrDefault("mdctId", "").toString();

        INFO1           =  paramName + "님이 "+ paramServerIP+" 서버에 " + paramMdctId+" 계정으로 접속하였습니다." ;

        approvalParamMap = new HashMap();

        approvalParamMap.put("msgType",                 "SMS");
        approvalParamMap.put("callback",                "15880559");
        approvalParamMap.put("msg",                     INFO1);
        approvalParamMap.put("phone",                   returnMap.getStr("phonnumber", ""));

        approvalResponseMessage = new HttpRequestProc().sendPacket2("http://dev01.ring2pay.com:43400/SMSMng/Sender/Proc", approvalParamMap);

        return approvalResponseMessage;
    }

    @SuppressWarnings("unchecked")
    public JSONObject Cmd_1316(JSONObject requestMessage, HttpServletRequest request)
    {
        MyMap                   paramMap                 = new MyMap();
        List<MyCamelMap>        returnMap                = null;
        JSONObject              responseMessage          = new JSONObject();
        JSONArray               responseMessageList      = new JSONArray();

        paramMap.put("sttus",           requestMessage.getOrDefault("sttus", "A"));     // A 활성화
        paramMap.put("refNrlmber",      requestMessage.getOrDefault("refNrlmber", "0")); // 사용자 고유 SEQ

        returnMap = mEqAccLogBiz.ListData(paramMap);

        for (MyCamelMap info : returnMap)
        {
            JSONObject tempObject = new JSONObject( info );

            responseMessageList.add(tempObject);
        }
        responseMessage.put("list", responseMessageList);

        return responseMessage;
    }
}
