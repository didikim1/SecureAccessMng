package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.act;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.act.NrlmberAct;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz;

@Controller
@RequestMapping("/login")
public class LoginAct
{
    final String pagePrefix = "login";

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(NrlmberAct.class.getName());

    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz")
    LoginBiz mBiz;


    @RequestMapping(value = { "/", "/index.do" })
    public String index(Model model)
    {
            return pagePrefix + "/index";
    }

    @RequestMapping(value = { "/ListPagingData.do" })
    public String ListPagingData(Model model)
    {
        MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        BasicBean       resultBean  = null;

        resultBean = mBiz.ListPagingData( paramMap );

        model.addAttribute("paramMap",          paramMap);
        model.addAttribute("Data",              resultBean);

        return pagePrefix + "/ListPagingData";
    }

    @RequestMapping(value = { "/ModifyPassword.do" })
    public String ModifyPassword(Model model)
    {
    	MyMap           paramMap    = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

    	model.addAttribute("paramMap",          paramMap);

    	return pagePrefix + "/ModifyPassword";
    }

    @RequestMapping(value = { "/SelectOneData.do" })
    public String SelectOneData(Model model)
    {
        MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap      resultMap       = new MyCamelMap();

        resultMap = mBiz.SelectOneData(paramMap);

        model.addAttribute("paramMap",      paramMap);
        model.addAttribute("Data",          resultMap);

        return null;
    }

    @RequestMapping(value = { "/ProcSelectOneData.do" })
    public @ResponseBody ResultMessage ProcSelectOneData(Model model)
    {
        MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap      resultMap       = new MyCamelMap();
        String          resultCode      = ResultCode.RESULT_OK;

        resultMap                         = mBiz.SelectOneData(paramMap);

        if ( resultMap == null )
        {
                resultCode = ResultCode.RESULT_EMPTY;
        }
        else
        {
        		if( "0".equals( checkPw( paramMap.getStr("password", "") ) ) == false )
        		{
        			// RESULT_NOT_MODIFIED 패스워드 변경이 필요(현재 정책에 맞지않는 패스워드 사용중)
        			return new ResultMessage(ResultCode.RESULT_NOT_MODIFIED, "RESULT_NOT_MODIFIED", paramMap);
        		}
        		else
        		{
        			FrameworkBeans.findSessionBean().mberSeq        = resultMap.getStr("seq");
        			FrameworkBeans.findSessionBean().roleId      = resultMap.getStr("roleId");
        			FrameworkBeans.findSessionBean().dpamentId      = resultMap.getStr("dpamentId");
        			FrameworkBeans.findSessionBean().positionId     = resultMap.getStr("positionId");
        			FrameworkBeans.findSessionBean().uniqId         = resultMap.getStr("uniqId");
        			FrameworkBeans.findSessionBean().mberName       = resultMap.getStr("mberName");
        			FrameworkBeans.findSessionBean().mberSttus      = resultMap.getStr("mberSttus");
        			FrameworkBeans.findSessionBean().moblphonNo     = resultMap.getStr("moblphonNo");
        			FrameworkBeans.findSessionBean().emailAddress   = resultMap.getStr("emailAddress");
        		}
        }

        return new ResultMessage(resultCode, "success");
    }

    @RequestMapping(value = { "/ModifyPasswordProc.do" })
    public @ResponseBody ResultMessage ModifyPasswordProc(Model model)
    {
        MyMap           paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap      resultMap       = new MyCamelMap();
        String          resultCode      = ResultCode.RESULT_OK;

        int				resultInt		= 0;
        String			resultCheckPw   = "99";

        if ( FrameworkUtils.isNull( paramMap.getStr("password") ) ) 	{ return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, "error"); }
        if ( FrameworkUtils.isNull( paramMap.getStr("uniqId") ) ) 		{ return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, "error"); }
        if ( FrameworkUtils.isNull( paramMap.getStr("newpassword") ) )  { return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, "error"); }

        resultCheckPw = checkPw(paramMap.getStr("newpassword")) ;

        if ( "0".equals( resultCheckPw ) == false )
        {
        	return new ResultMessage(ResultCode.RESULT_BAD_REQUEST, "error");
        }

        resultInt                       = mBiz.ModifyDataPassword(paramMap);

        if ( resultInt >= 1 )
        {
                resultCode = ResultCode.RESULT_OK;

                paramMap.put("password", paramMap.getStr("newpassword"));

                resultMap                         = mBiz.SelectOneData(paramMap);

                FrameworkBeans.findSessionBean().mberSeq        = resultMap.getStr("seq");
    			FrameworkBeans.findSessionBean().dpamentId      = resultMap.getStr("dpamentId");
    			FrameworkBeans.findSessionBean().positionId     = resultMap.getStr("positionId");
    			FrameworkBeans.findSessionBean().uniqId         = resultMap.getStr("uniqId");
    			FrameworkBeans.findSessionBean().mberName       = resultMap.getStr("mberName");
    			FrameworkBeans.findSessionBean().mberSttus      = resultMap.getStr("mberSttus");
    			FrameworkBeans.findSessionBean().moblphonNo     = resultMap.getStr("moblphonNo");
    			FrameworkBeans.findSessionBean().emailAddress   = resultMap.getStr("emailAddress");
        }
        else
        {
        	resultCode = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
        }

        return new ResultMessage(resultCode, "success");
    }


    // 로그아웃
    @RequestMapping(value = { "/DeleteData.do" })
    public String DeleteData(Model model, HttpServletRequest request)
    {
            request.getSession().invalidate();

            return "redirect:/login/";
    }



    /**
     * 패스워드를 규칙에 맞게 체크한다.

     * [사용방법]
     * Util.checkPw(String inputPw);

     * Return

     * 0: OK (규칙에 부합됨)

     * 1: 입력된 패스워드가 null이거나 없음.

     * 2: 입력된 패스워드가 16자 이상임.

     * 3: 입력된 패스워드가 2조합 인데, 9자리 미만임.

     * 4: 입력된 패스워드가 3조합인데, 9자리 미만임.

     * 5: 입력된 패스워드가 2조합 미만임.

     * 6: 입력된 패스워드가 3자리 이상 연속된 값이 포함됨. (예, abc, def, 123)

     * 7: 입력된 패스워드가 키보드 조합으로 3자리 이상 연속된 값이 포함됨. (예, asd, qwe, jkl)

     * 8: 입력된 패스워드가 3자리 이상 같은 값이 포함됨. (예, aaa, 222)

     * 99: 에러
     */
    public static String checkPw(String inputPw) {
        String strResult = "";
        if (inputPw == null || inputPw.equals("")) return "1";
        if (inputPw.length() > 16) return "2";

        try {
            Pattern pAlphabetLow = null;
            Pattern pAlphabetUp = null;
            Pattern pNumber = null;
            Pattern pSpecialChar = null;
            Pattern pThreeChar = null;
            Matcher match;
            int nCharType = 0;

            pAlphabetLow = Pattern.compile("[a-z]");             // 영소문자
            pAlphabetUp  = Pattern.compile("[A-Z]");              // 영대문자
            pNumber 	 = Pattern.compile("[0-9]");                  // 숫자
            pSpecialChar = Pattern.compile("\\p{Punct}");        // 특수문자 -_=+\\|()*&^%$#@!~`?>             pThreeChar = Pattern.compile("(\\p{Alnum})\\1{2,}");// 3자리 이상 같은 문자 또는 숫자

            // 영소문자가 포함되어 있는가?
            match = pAlphabetLow.matcher(inputPw);
            if(match.find()) nCharType++;
            // 영대문자가 포함되어 있는가?
            match = pAlphabetUp.matcher(inputPw);
            if(match.find()) nCharType++;
            // 숫자가 포함되어 있는가?
            match = pNumber.matcher(inputPw);
            if(match.find()) nCharType++;
            // 특수문자가 포함되어 있는가?
            match = pSpecialChar.matcher(inputPw);
            if(match.find()) nCharType++;

            // 3자리 이상 같은 문자 또는 숫자가 포함되어 있는가?
//            match = pThreeChar.matcher(inputPw);
//            if(match.find()) return "8";

            // 3가지 이상 조합인가?
            if (nCharType >= 3) {
                if(inputPw.length() < 9 ) return "4";
                else strResult = "0";
            // 2가지 조합인가?
            }else if(nCharType == 2) {
                if(inputPw.length() < 9 ) return "3";
                else strResult = "0";
            // 1가지 조합인가?
            } else {
                return "5";
            }

            // 연속된 3자리 이상의 문자나 숫자가 포함되어 있는가?
            String listThreeChar = "abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz|012|123|234|345|456|567|678|789|890";
            String[] arrThreeChar = listThreeChar.split("\\|");
            for (int i=0; i<arrThreeChar.length; i++){
            		if(inputPw.toLowerCase().matches(".*" + arrThreeChar[i] + ".*")) {
                    return "6";
                }
            }

            // 연속된 3자리 이상의 키보드 문자가 포함되어 있는가?
            String listKeyboardThreeChar = "qwe|wer|ert|rty|tyu|yui|uio|iop|asd|sdf|dfg|fgh|ghj|hjk|jkl|zxc|xcv|cvb|vbn|bnm";
            String[] arrKeyboardThreeChar = listKeyboardThreeChar.split("\\|");
            for (int j=0; j<arrKeyboardThreeChar.length; j++){
            		if(inputPw.toLowerCase().matches(".*" + arrKeyboardThreeChar[j] + ".*")) {
                    return "7";
                }
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            strResult = "99";
        }

        return strResult;
    }

}
