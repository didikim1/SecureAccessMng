package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.act;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqlist.biz.EqListBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.ManagerPWAES256;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;

@Controller
@RequestMapping("/eqidpwd")
public class EqIdpwdAction
{
    final String pagePrefix = "eqlist";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(EqIdpwdAction.class.getName());

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.eqidpwd.service.EqIdpwdBiz")
    EqIdpwdBiz mBiz;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.svinfo.biz.EqListBiz")
    EqListBiz mEqListBiz;

    @RequestMapping(value = { "/", "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        BasicBean resultBean = mBiz.ListPagingData(paramMap);

        model.addAttribute("Data", resultBean);

        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value = { "/ListData.do" })
    public @ResponseBody ResultMessage ListData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean resultBean = null;

        resultBean = mBiz.ListPagingData(paramMap);

        return new ResultMessage("200", resultBean);
    }

    @RequestMapping(value ={ "/SelectOneData.do" })
    public @ResponseBody ResultMessage SelectOneData(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap resultMap = mBiz.SelectOneData(paramMap);

        return new ResultMessage("200", resultMap);
    }

    @RequestMapping(value = { "/Register.do" }, method = RequestMethod.GET)
    public String Register(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyMap infoMap   = null;
        String receivingPnttm = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        paramMap.put("receivingPnttm", receivingPnttm);


        infoMap = mEqListBiz.SelectOneData(paramMap);

        model.addAttribute("paramMap",          paramMap);
        model.addAttribute("infoMap",           infoMap);

        return "eqidpwd/Register";
    }

    @SuppressWarnings("static-access")
	@RequestMapping(value = { "/RegisterData.do" }) 
    public @ResponseBody ResultMessage RegisterData(Model model)
    {
    	// 무조건 이 메서드는 ResultMessage값을 return해줘야해
    	
    	
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        int   iRtnValue = 0;
       
        String refEqList = null;  // 서버고유키값
        String id			      = null; // 서버ID
        
        String strPWD	 = null;
        String strEncPWD = null;
        
        
        /*중복체크 서버 계정ID*/
        
        // SelectOneIDCheck 이건 어때?? 이ㅔ서드는??그건 이해했고, if 문에서 0이랑 같으면 ~~ 까지는 이해했는데, 저 밑에 101이.. 무슨말인지 모르겠어요 
        // 0이 아니면 중복으로 보는거지...
        if( mBiz.SelectOneIDCheck(paramMap)  == 0 )		// 중복이아니고
        {
        	strPWD    = paramMap.getStr("pwd");
            try 
            {
            	// aes256으로 패스워드 암호화도 성공하고 실패하면 `Exception` 발생~~ 이건 어때??100%는 아니지만 알것같기도하고... 직접해보라고하면 못하고요
            	// 이건 좀 하다보면 알게될거야 자연스럽게~~
    			strEncPWD = ManagerPWAES256.getInstance().AES_Encode( strPWD );  
    			paramMap.put("pwd", strEncPWD);
    			// 계정 등록
    			iRtnValue = mBiz.RegisterData(paramMap); 
    			
    			if ( iRtnValue > 0 )
    			{
    				return new ResultMessage(ResultCode.RESULT_OK, null);
    			}
    			else
    			{
    				return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
    			}
    		} 
            catch (Exception e) 
            {
            	e.printStackTrace();
            	 return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null); 
    		} 
        } 
        else 
        {
        	// RESULT_NOT_EMPTY 101  중복값
        	/*
        	 * 음.....이해됨...? ㅜ 아니요..부분이 뭔가 이해안되면 마우스 뺏어가 넹.. 근데 이 부분 너무 어려워요 조금만 천천히...해주세욘
        	 */
        	/// NOT :아니다
        	//  EMPTY: 빈값 
        	// 빈값이 아니다 ?라는 뜻아님??음... 저 밑에 server error는 여?
        	//  저부분이 저기에 있어도되는건데
        	// 어느구문에서 문제가 도리지 모르니깐 일단 무조건 오류로 리턴하대~~
        	 // 중복이면 ajax응답값으로 101이 응답되는거징...  RESULT_NOT_EMPTY == 101
        	// 70% 이해됨??
        	//그정도는 아니에여..괜찮아 시간많아 ㅎ
        	// 하나만 더해보자
        	
        	MyMap abcde = new MyMap();
        	
        	abcde.put("rtrtrt", "값입니다..");
        	
        	return new ResultMessage(ResultCode.RESULT_NOT_EMPTY, "중복된데이터입니다.. ", abcde);
        }
        
        
       
        
    }

    @RequestMapping(value = { "/ModifyData.do" })
    public @ResponseBody ResultMessage ModifyData(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        int   iRtnValue = 0;
        // 계정 수정
        iRtnValue = mBiz.ModifyData(paramMap);

        if ( iRtnValue > 0 )
        {
            return new ResultMessage(ResultCode.RESULT_OK, null);
        }
        else
        {
            return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
        }
    }

    @RequestMapping(value = { "/DeleteData.do" })
    public @ResponseBody ResultMessage DeleteData(Model model)
    {
        MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        int   iRtnValue = 0;

        // 계정 삭제
        iRtnValue = mBiz.DeleteData(paramMap);

        if ( iRtnValue > 0 )
        {
            return new ResultMessage(ResultCode.RESULT_OK, null);
        }
        else
        {
            return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
        }
    }
}
