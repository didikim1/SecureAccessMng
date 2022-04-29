package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.act;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.dto.ServerAccessDTO;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

/**
 * 서버 접근관련
 * @author hyo
 *
 */
@Controller
@RequestMapping("/api/v2/serveraccess")
public class ApiV2ServerAccessAct
{
    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz")
    EqAccLogBiz mEqAccLogBiz;

    /** 작업 고유 키값 */
    private String  kKey_worktypecodeId = "worktypecodeId";

    /** IDC Center 고유 키값 */
    private String  kKey_refEqIdc       = "refEqIdc";

    /** IDC Center Server 고유 키값 */
    private String  kKey_refEqList      = "refEqList";

    /** Server idpwd 고유 키값 */
    private String  kKey_refEqIdpwd     = "refEqIdpwd";

    /** 사용자 고유 키값 */
    private String  kKey_refNrlmber     = "refNrlmber";

    /** 접근 IP(클라이언트) 고유 키값 */
    private String  kKey_refAllowIp     = "refAllowIp";

    /** SSH Client 프로세스 ID */
    private String kKey_processID       = "processID";

    /** 서버 접근 사유 */
    private String kKey_reason          = "reason";


    /**
     * 상태값
     */
    private String kKey_sttus          = "sttus";


    @RequestMapping(value="/accLogs.do", method=RequestMethod.GET)
    public @ResponseBody ResultMessage list(@RequestParam(value="rows", defaultValue="10") int rows)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        List<MyCamelMap> responseList       = null;
        MyMap           paramMap        = new MyMap();

        paramMap.put("rows", rows);
        responseList = mEqAccLogBiz.ListPagingData(paramMap).getList();

        if(responseList != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, responseList);
    }

    @RequestMapping(value="/register.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage register(@RequestBody ServerAccessDTO dto)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        int             intRtnValue     = 0;
        MyMap           paramMap        = new MyMap();

        paramMap.put(kKey_worktypecodeId,       dto.getWorktypecodeId());
        paramMap.put(kKey_refEqIdc,             dto.getRefEqIdc());
        paramMap.put(kKey_refEqList,            dto.getRefEqList());
        paramMap.put(kKey_refEqIdpwd,           dto.getRefEqIdpwd());
        paramMap.put(kKey_refNrlmber,           dto.getRefNrlmber());
        paramMap.put(kKey_refAllowIp,           dto.getRefAllowIp());
        paramMap.put(kKey_processID,            dto.getProcessID());
        paramMap.put(kKey_reason,               dto.getReason());

        intRtnValue = mEqAccLogBiz.RegisterData(paramMap);

        if(intRtnValue > 0)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, intRtnValue);
    }


    @RequestMapping(value="/processAllTermination.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage processAllTermination(@RequestBody ServerAccessDTO dto)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        int             intRtnValue     = 0;
        MyMap           paramMap        = new MyMap();

        paramMap.put(kKey_refNrlmber,           dto.getRefNrlmber());
        paramMap.put(kKey_sttus,                dto.getSttus());

        List<MyCamelMap> list = mEqAccLogBiz.ListData(paramMap);

        System.out.println("size : " + list.size());

        for (MyCamelMap info : list)
        {;
            paramMap        = new MyMap();
            paramMap.put("processid",  info.getStr("processid") );
            intRtnValue += mEqAccLogBiz.UpdateLogOutAccLog(paramMap);
        }

        if(intRtnValue > 0)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, intRtnValue);
    }

    /**
     * 프로세스종료
     * @param processId
     * @return
     */
    @RequestMapping(value="/termination.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage termination(@RequestBody ServerAccessDTO dto)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        int             intRtnValue     = 0;
        MyMap           paramMap        = new MyMap();

        paramMap.put("processid",  dto.getProcessID() );

        intRtnValue = mEqAccLogBiz.UpdateLogOutAccLog(paramMap);

        if(intRtnValue > 0)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, intRtnValue);
    }

    @RequestMapping(value="/termination2.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage termination2(@RequestBody ServerAccessDTO dto)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        int             intRtnValue     = 0;
        MyMap           paramMap        = new MyMap();

        paramMap.put("processid",   dto.getProcessID() );
        paramMap.put("refNrlmber",  dto.getRefNrlmber() );

        intRtnValue = mEqAccLogBiz.UpdateLogOutAccLogByRefNrlmberWithProcessid(paramMap);

        if(intRtnValue > 0)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, intRtnValue);
    }

    @RequestMapping(value="/termination3.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage termination3(@RequestBody ServerAccessDTO dto)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        int             intRtnValue     = 0;
        MyMap           paramMap        = new MyMap();

        paramMap.put("processid",   dto.getProcessID() );
        paramMap.put("refNrlmber",  dto.getRefNrlmber() );

        intRtnValue = mEqAccLogBiz.UpdateLogOutAccLogByRefNrlmber(paramMap);

        if(intRtnValue > 0)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, intRtnValue);
    }
}
