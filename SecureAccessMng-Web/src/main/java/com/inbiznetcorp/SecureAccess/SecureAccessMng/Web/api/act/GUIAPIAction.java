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
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz;
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

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz")
    EqAccLogBiz mEqAccLogBiz;

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

        System.out.println("jsonReqMessage : "+ jsonReqMessage);

        switch (requestCmd)
        {
                case GUIAPIConfig.Cmd.Cmd_1110:                                                                           break;       // ?????? IP  ??????
                case GUIAPIConfig.Cmd.Cmd_1111:                                                                           break;       // ?????? IP  ??????
                case GUIAPIConfig.Cmd.Cmd_1112:                                                                           break;       // ?????? IP  ??????
                case GUIAPIConfig.Cmd.Cmd_1113:  jsonResMessage = mGUIAPIABiz_Cmd_111X.Cmd_1113(jsonReqMessage, request); break;       // ?????? IP  ??????

                case GUIAPIConfig.Cmd.Cmd_1210:                                                                           break;       // ??????     ??????
                case GUIAPIConfig.Cmd.Cmd_1211:                                                                           break;       // ??????     ??????
                case GUIAPIConfig.Cmd.Cmd_1212:                                                                           break;       // ??????     ??????
                case GUIAPIConfig.Cmd.Cmd_1213:                                                                           break;       // ??????     ??????
                case GUIAPIConfig.Cmd.Cmd_1214: jsonResMessage = mGUIAPIABiz_Cmd_121X.Cmd_1214(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // ??????     ?????????

                case GUIAPIConfig.Cmd.Cmd_1310:                                                                           break;       // ????????????     ??????
                case GUIAPIConfig.Cmd.Cmd_1311:                                                                           break;       // ????????????     ??????
                case GUIAPIConfig.Cmd.Cmd_1312:                                                                           break;       // ????????????     ??????
                case GUIAPIConfig.Cmd.Cmd_1313: jsonResMessage = mGUIAPIABiz_Cmd_131X.Cmd_1313(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // ????????????  ??????
                case GUIAPIConfig.Cmd.Cmd_1314: jsonResMessage = mGUIAPIABiz_Cmd_131X.Cmd_1314(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // ????????????  ??????????????????
                case GUIAPIConfig.Cmd.Cmd_1315: jsonResMessage = mGUIAPIABiz_Cmd_131X.Cmd_1315(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // ????????????  ????????????(SMS)
                case GUIAPIConfig.Cmd.Cmd_1316: jsonResMessage = mGUIAPIABiz_Cmd_131X.Cmd_1316(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // ????????????  ?????????????????????
                case GUIAPIConfig.Cmd.Cmd_1317: jsonResMessage = mGUIAPIABiz_Cmd_131X.Cmd_1317(jsonReqMessage, request); System.out.println("jsonResMessage:"+jsonResMessage); break;       // ????????????  ??????????????????

                default:
                        jsonResMessage = new JSONObject();
                        jsonResultCode = new JSONObject();

                        jsonResultCode.put("code",              "99");
                        jsonResultCode.put("message",  "??????(????????????)");

                        jsonResMessage.put("result", jsonResultCode);
                        break;
        }
        System.out.println("jsonResMessage(2):"+jsonResMessage);
        return new ResponseEntity<String>(jsonResMessage.toString(), resHeaders, HttpStatus.OK) ;
    }
}
