package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberHistoryBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

@Controller
@RequestMapping("/ctn/nrlmber/history")
public class NrlmberHistoryAct
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberHistoryBiz")
    NrlmberHistoryBiz mBiz;

    @RequestMapping(value = { "/ListPagingData.do" })
    public @ResponseBody ResultMessage ListPagingDatas(Model model)
    {
        MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        String          resultCode      = ResultCode.RESULT_OK;
        BasicBean       resultBean      = null;

        resultBean   = mBiz.ListPagingData( paramMap );



        return new ResultMessage(resultCode, resultBean);
    }

    /*
     *
     * Ajax 시
     *   public @ResponseBody ResultMessage {메서드명}(Model model)
     *   - @ResponseBody 선언
     *   - ResultMessage ResultMessage
     *
     * PAGE 전환시
     *   public String ModifyData(Model model)
     */
}
