package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

@Controller
@RequestMapping("/eqacclog")
public class EqAccLogAction
{
    final String pagePrefix = "eqacclog";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(EqAccLogAction.class.getName());

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqacclog.biz.EqAccLogBiz")
    EqAccLogBiz mBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidc.service.EqIdcBiz")
    EqIdcBiz mEqIdcBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz mEqListBiz;

    @RequestMapping(value ={ "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean       resultBean  = null;
        BasicBean       idcInfoBean = mEqIdcBiz.ListData(new MyMap());              // IDC List

        resultBean = mBiz.ListPagingData( paramMap );

        model.addAttribute("paramMap",          paramMap);
        model.addAttribute("Data",              resultBean);
        model.addAttribute("IdcInfoList",       idcInfoBean.getList());

        if ( paramMap.getInt("idcSeq", 0) > 0)
        {
            MyMap eqListParamMap = new MyMap();

            eqListParamMap.put("rows",     10000);
            eqListParamMap.put("refEqIdc", paramMap.getInt("idcSeq"));

            model.addAttribute("EqListInfoList",       mEqListBiz.ListPagingData(eqListParamMap).getList());
        }

        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value ={ "/EqList/ListData.do" })
    public @ResponseBody ResultMessage EqListListData(Model model)
    {
        MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        MyMap eqListParamMap = new MyMap();

        eqListParamMap.put("rows",     10000);
        eqListParamMap.put("refEqIdc", paramMap.getInt("idcSeq", 0));


        return new ResultMessage(ResultCode.RESULT_OK, mEqListBiz.ListPagingData(eqListParamMap) );
    }

    @RequestMapping(value ={ "/SelectOneData.do" })
    public String SelectOneData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return null;
    }

    @RequestMapping(value = { "/RegisterData.do" })
    public ResultMessage RegisterData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value = { "/ModifyData.do" })
    public ResultMessage ModifyData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }

    @RequestMapping(value = { "/DeleteData.do" })
    public ResultMessage DeleteData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        return new ResultMessage("", null);
    }
}
