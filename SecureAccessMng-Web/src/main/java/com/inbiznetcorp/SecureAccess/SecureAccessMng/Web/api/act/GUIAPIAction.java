package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.intrf.GUIAPIConfig;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_111X;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_121X;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_131X;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;

@Controller
@RequestMapping("/gui/api")
public class GUIAPIAction
{

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_111X")
    GUIAPIABiz_Cmd_111X mGUIAPIABiz_Cmd_111X;

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_121X")
    GUIAPIABiz_Cmd_121X mGUIAPIABiz_Cmd_121X;

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.biz.GUIAPIABiz_Cmd_131X")
    GUIAPIABiz_Cmd_131X mGUIAPIABiz_Cmd_131X;

    @SuppressWarnings({ "unchecked"})
    @RequestMapping(value = { "/{cmd}" } , method=RequestMethod.POST)
    public ResponseEntity<String> cmd( @PathVariable("cmd") String cmd,  HttpServletRequest request )
    {
        JSONObject jsonReqMessage       = null;
        JSONObject jsonResMessage       = null;
        JSONObject jsonResultCode       = null;

        String     requestCmd           = GUIAPIConfig.Cmd.Cmd_Invalid;
//        String     traceId              = FrameworkUtils.generateSessionID();

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        requestCmd              = cmd;
        jsonReqMessage          = FrameworkUtils.getBody(request);

        switch (requestCmd)
        {
                case GUIAPIConfig.Cmd.Cmd_1110:                                                                           break;       // 허용 IP  등록
                case GUIAPIConfig.Cmd.Cmd_1111:                                                                           break;       // 허용 IP  변경
                case GUIAPIConfig.Cmd.Cmd_1112:                                                                           break;       // 허용 IP  삭제
                case GUIAPIConfig.Cmd.Cmd_1113:  jsonResMessage = mGUIAPIABiz_Cmd_111X.Cmd_1113(jsonReqMessage, request); break;       // 허용 IP  조회

                case GUIAPIConfig.Cmd.Cmd_1210:                                                                           break;       // 회원     등록
                case GUIAPIConfig.Cmd.Cmd_1211:                                                                           break;       // 회원     변경
                case GUIAPIConfig.Cmd.Cmd_1212:                                                                           break;       // 회원     삭제
                case GUIAPIConfig.Cmd.Cmd_1213:                                                                           break;       // 회원     조회
                case GUIAPIConfig.Cmd.Cmd_1214: jsonResMessage = mGUIAPIABiz_Cmd_121X.Cmd_1214(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // 회원     로그인

                case GUIAPIConfig.Cmd.Cmd_1310:                                                                           break;       // 접속승인     등록
                case GUIAPIConfig.Cmd.Cmd_1311:                                                                           break;       // 접속승인     변경
                case GUIAPIConfig.Cmd.Cmd_1312:                                                                           break;       // 접속승인     삭제
                case GUIAPIConfig.Cmd.Cmd_1313: jsonResMessage = mGUIAPIABiz_Cmd_131X.Cmd_1313(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // 접속승인  조회
                case GUIAPIConfig.Cmd.Cmd_1314: jsonResMessage = mGUIAPIABiz_Cmd_131X.Cmd_1314(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // 접속승인  접속승인요청


                default:
                        jsonResMessage = new JSONObject();
                        jsonResultCode = new JSONObject();

                        jsonResultCode.put("code",              "99");
                        jsonResultCode.put("message",  "실패(기타오류)");

                        jsonResMessage.put("result", jsonResultCode);
                        break;
        }
        System.out.println("jsonResMessage(2):"+jsonResMessage);
        return new ResponseEntity<String>(jsonResMessage.toString(), resHeaders, HttpStatus.OK) ;
    }
}
