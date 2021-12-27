package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.act;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

@Controller
@RequestMapping("/api/v2/idccenter")
public class ApiV2IdcCenterAct
{
    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(ApiV2IdcCenterAct.class.getName());

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz")
    EqIdcBiz   mEqIdcBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz  mEqListBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz")
    EqIdpwdBiz mEqIdpwdBiz;

    /**
     * IDC 목록
     * @return
     */
    @RequestMapping(value="/list.do", method=RequestMethod.GET, consumes="application/json")
    public @ResponseBody ResultMessage list()
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        List<MyCamelMap> responseList       = null;

        responseList = mEqIdcBiz.ListData(new MyMap()).getList();

        if(responseList != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, responseList);
    }

    /**
     * IDC 서버 목록
     * @param refEqIdc
     * @return
     */
    @RequestMapping(value="/{refEqIdc}/list.do", method=RequestMethod.GET, consumes="application/json")
    public @ResponseBody ResultMessage server_list(@PathVariable("refEqIdc") int refEqIdc)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        List<MyCamelMap> responseList       = null;

        MyMap paramMap = new MyMap();
        paramMap.put("refEqIdc", refEqIdc);

        responseList = mEqListBiz.ListData(paramMap).getList();

        if(responseList != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, responseList);
    }

    /**
     * IDC 서버 계정 목록
     * @param refEqIdc
     * @return
     */
    @RequestMapping(value="/{refEqIdc}/{refEqList}/list.do", method=RequestMethod.GET, consumes="application/json")
    public @ResponseBody ResultMessage server_idpwd_list(@PathVariable("refEqIdc") int refEqIdc, @PathVariable("refEqList") int refEqList)
    {
        String result_code = ResultCode.RESULT_NOT_FOUND;

        List<MyCamelMap> responseList       = null;

        MyMap paramMap = new MyMap();
        paramMap.put("refEqList", refEqList);

        responseList = mEqIdpwdBiz.ListData(paramMap).getList();

        if(responseList != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, responseList);
    }
}
