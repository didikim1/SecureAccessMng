package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.act;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.charge.biz.ChargeBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.act.NrlmberAct;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.ManagerPWAES256;
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
public class LoginAct {
	final String pagePrefix = "login";

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(NrlmberAct.class.getName());

	@Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.login.biz.LoginBiz")
	LoginBiz mBiz;

	@Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz")
	NrlmberBiz mNrlmberBiz;

	@Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz")
	CommonBiz mCommonBiz;

	@Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.charge.biz.ChargeBiz")
	ChargeBiz mChargeBiz;

	 @Value("${spring.profiles.active}")
	 public String active;

	@RequestMapping(value = { "/", "/index.do" })
	public String index(Model model) {
		return pagePrefix + "/index";
	}

    @SuppressWarnings("static-access")
	@RequestMapping(value = { "/idCheck.do" })
    public @ResponseBody ResultMessage idCheck(Model model)
    {
        MyMap paramMap  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

        if( mBiz.SelectOneIDCheck(paramMap)  > 0 )		// ??????
        {
            return new ResultMessage(ResultCode.RESULT_OK, null);
        }
        else
        {
            return new ResultMessage(ResultCode.RESULT_NOT_FOUND, null);
        }

    }

	@RequestMapping(value = { "/ListPagingData.do" })
	public String ListPagingData(Model model) {
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		BasicBean resultBean = null;

		resultBean = mBiz.ListPagingData(paramMap);

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("Data", resultBean);

		return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/ModifyPassword.do" })
	public String ModifyPassword(Model model) {
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		model.addAttribute("paramMap", paramMap);

		return pagePrefix + "/ModifyPassword";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model) {
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap resultMap = new MyCamelMap();

		resultMap = mBiz.SelectOneData(paramMap);

		model.addAttribute("paramMap", paramMap);
		model.addAttribute("Data", resultMap);

		return null;
	}

	@RequestMapping(value = { "/ProcSelectOneData.do" })
	public @ResponseBody ResultMessage ProcSelectOneData(Model model) {
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap resultMap = new MyCamelMap();
		String resultCode = ResultCode.RESULT_OK;

		resultMap = mBiz.SelectOneData(paramMap);

		if (resultMap == null) {
			resultCode = ResultCode.RESULT_EMPTY;
		} else {
			FrameworkBeans.findSessionBean().mberSeq = resultMap.getStr("seq");
			FrameworkBeans.findSessionBean().roleId = resultMap.getStr("roleId");
			FrameworkBeans.findSessionBean().dpamentId = resultMap.getStr("dpamentId");
			FrameworkBeans.findSessionBean().positionId = resultMap.getStr("positionId");
			FrameworkBeans.findSessionBean().uniqId = resultMap.getStr("uniqId");
			FrameworkBeans.findSessionBean().mberName = resultMap.getStr("mberName");
			FrameworkBeans.findSessionBean().mberSttus = resultMap.getStr("mberSttus");
			FrameworkBeans.findSessionBean().moblphonNo = resultMap.getStr("moblphonNo");
			FrameworkBeans.findSessionBean().emailAddress = resultMap.getStr("emailAddress");
		}

		return new ResultMessage(resultCode, "success");
	}

	@RequestMapping(value = { "/ModifyPasswordProc.do" })
	public @ResponseBody ResultMessage ModifyPasswordProc(Model model) {
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap resultMap = new MyCamelMap();
		String resultCode = ResultCode.RESULT_OK;

		int resultInt = 0;
		String resultCheckPw = "99";

		if (FrameworkUtils.isNull(paramMap.getStr("password"))) {
			return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, "error");
		}
		if (FrameworkUtils.isNull(paramMap.getStr("uniqId"))) {
			return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, "error");
		}
		if (FrameworkUtils.isNull(paramMap.getStr("newpassword"))) {
			return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, "error");
		}

		resultCheckPw = checkPw(paramMap.getStr("newpassword"));

		if ("0".equals(resultCheckPw) == false) {
			return new ResultMessage(ResultCode.RESULT_BAD_REQUEST, "error");
		}

		resultInt = mBiz.ModifyDataPassword(paramMap);

		if (resultInt >= 1) {
			resultCode = ResultCode.RESULT_OK;

			paramMap.put("password", paramMap.getStr("newpassword"));

			resultMap = mBiz.SelectOneData(paramMap);

			FrameworkBeans.findSessionBean().mberSeq = resultMap.getStr("seq");
			FrameworkBeans.findSessionBean().dpamentId = resultMap.getStr("dpamentId");
			FrameworkBeans.findSessionBean().positionId = resultMap.getStr("positionId");
			FrameworkBeans.findSessionBean().uniqId = resultMap.getStr("uniqId");
			FrameworkBeans.findSessionBean().mberName = resultMap.getStr("mberName");
			FrameworkBeans.findSessionBean().mberSttus = resultMap.getStr("mberSttus");
			FrameworkBeans.findSessionBean().moblphonNo = resultMap.getStr("moblphonNo");
			FrameworkBeans.findSessionBean().emailAddress = resultMap.getStr("emailAddress");
		} else {
			resultCode = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
		}

		return new ResultMessage(resultCode, "success");
	}

	// ????????????
	@RequestMapping(value = { "/DeleteData.do" })
	public String DeleteData(Model model, HttpServletRequest request) {
		request.getSession().invalidate();

		return "redirect:/login/";
	}

	/**
	 * ??????????????? ????????? ?????? ????????????.
	 *
	 * [????????????] Util.checkPw(String inputPw);
	 *
	 * Return
	 *
	 * 0: OK (????????? ?????????)
	 *
	 * 1: ????????? ??????????????? null????????? ??????.
	 *
	 * 2: ????????? ??????????????? 16??? ?????????.
	 *
	 * 3: ????????? ??????????????? 2?????? ??????, 9?????? ?????????.
	 *
	 * 4: ????????? ??????????????? 3????????????, 9?????? ?????????.
	 *
	 * 5: ????????? ??????????????? 2?????? ?????????.
	 *
	 * 6: ????????? ??????????????? 3?????? ?????? ????????? ?????? ?????????. (???, abc, def, 123)
	 *
	 * 7: ????????? ??????????????? ????????? ???????????? 3?????? ?????? ????????? ?????? ?????????. (???, asd, qwe, jkl)
	 *
	 * 8: ????????? ??????????????? 3?????? ?????? ?????? ?????? ?????????. (???, aaa, 222)
	 *
	 * 99: ??????
	 */
	public static String checkPw(String inputPw) {
		String strResult = "";
		if (inputPw == null || inputPw.equals(""))
			return "1";
		if (inputPw.length() > 16)
			return "2";

		try {
			Pattern pAlphabetLow = null;
			Pattern pAlphabetUp = null;
			Pattern pNumber = null;
			Pattern pSpecialChar = null;
			Pattern pThreeChar = null;
			Matcher match;
			int nCharType = 0;

			pAlphabetLow = Pattern.compile("[a-z]"); // ????????????
			pAlphabetUp = Pattern.compile("[A-Z]"); // ????????????
			pNumber = Pattern.compile("[0-9]"); // ??????
			pSpecialChar = Pattern.compile("\\p{Punct}"); // ???????????? -_=+\\|()*&^%$#@!~`?> pThreeChar =
															// Pattern.compile("(\\p{Alnum})\\1{2,}");// 3?????? ?????? ?????? ?????? ??????
															// ??????

			// ??????????????? ???????????? ??????????
			match = pAlphabetLow.matcher(inputPw);
			if (match.find())
				nCharType++;
			// ??????????????? ???????????? ??????????
			match = pAlphabetUp.matcher(inputPw);
			if (match.find())
				nCharType++;
			// ????????? ???????????? ??????????
			match = pNumber.matcher(inputPw);
			if (match.find())
				nCharType++;
			// ??????????????? ???????????? ??????????
			match = pSpecialChar.matcher(inputPw);
			if (match.find())
				nCharType++;

			// 3?????? ?????? ?????? ?????? ?????? ????????? ???????????? ??????????
//            match = pThreeChar.matcher(inputPw);
//            if(match.find()) return "8";

			// 3?????? ?????? ?????????????
			if (nCharType >= 3) {
				if (inputPw.length() < 9)
					return "4";
				else
					strResult = "0";
				// 2?????? ?????????????
			} else if (nCharType == 2) {
				if (inputPw.length() < 9)
					return "3";
				else
					strResult = "0";
				// 1?????? ?????????????
			} else {
				return "5";
			}

			// ????????? 3?????? ????????? ????????? ????????? ???????????? ??????????
			String listThreeChar = "abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz|012|123|234|345|456|567|678|789|890";
			String[] arrThreeChar = listThreeChar.split("\\|");
			for (int i = 0; i < arrThreeChar.length; i++) {
				if (inputPw.toLowerCase().matches(".*" + arrThreeChar[i] + ".*")) {
					return "6";
				}
			}

			// ????????? 3?????? ????????? ????????? ????????? ???????????? ??????????
			String listKeyboardThreeChar = "qwe|wer|ert|rty|tyu|yui|uio|iop|asd|sdf|dfg|fgh|ghj|hjk|jkl|zxc|xcv|cvb|vbn|bnm";
			String[] arrKeyboardThreeChar = listKeyboardThreeChar.split("\\|");
			for (int j = 0; j < arrKeyboardThreeChar.length; j++) {
				if (inputPw.toLowerCase().matches(".*" + arrKeyboardThreeChar[j] + ".*")) {
					return "7";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			strResult = "99";
		}

		return strResult;
	}

	@RequestMapping(value = { "/CallAuthPage.do" })
	public String CallAuthPage(Model model) {
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();



		MyMap resultMap = mBiz.SelectOneData(paramMap);
		System.out.println("moblphonNo : " + resultMap);



//    	int authNumber  = 0;

//		resultMap.getStr(key)

		model.addAttribute("paramMap", 		paramMap);
		model.addAttribute("moblphonNo", 	resultMap.getStr("moblphonNo"));
		model.addAttribute("uniqId", 		resultMap.getStr("uniqId"));
//    	model.addAttribute("authNumber",    authNumber);

		return pagePrefix + "/RegisterCallAuthPage";
	}

	// ???????????? ????????????
	@RequestMapping(value = { "/CallAuth.do" })
	public @ResponseBody ResultMessage CallAuth(Model model) {
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		JSONObject rtrn = null;
		// ????????? A()

		String moblphonNo = paramMap.getStr("moblphonNo");

		String authNumber = paramMap.getStr("authNumber");

		String resultCode = ResultCode.RESULT_OK;

		String callResult = "99";
		if("LOCAL".equals(active.toUpperCase()))
		{
		    callResult =  "00";
		}
		else
		{
		    String intro1 = "???????????????. ???????????? ?????? ????????? ????????? ??????????????? ???????????????.";
		    String intro2 = "???????????? ?????? ????????? ????????? ??????????????? ???????????????.";

		    rtrn = mCommonBiz.authCallDynamicSender(paramMap.getStr("moblphonNo"), authNumber, intro1, intro2);

		    callResult = (String) rtrn.get("result");
		}

		if (callResult.equals("00")) {
			resultCode = ResultCode.RESULT_OK;

			MyMap resultMap = mBiz.SelectOneData(paramMap);

			FrameworkBeans.findSessionBean().mberSeq = resultMap.getStr("seq");
			FrameworkBeans.findSessionBean().dpamentId = resultMap.getStr("dpamentId");
			FrameworkBeans.findSessionBean().positionId = resultMap.getStr("positionId");
			FrameworkBeans.findSessionBean().uniqId = resultMap.getStr("uniqId");
			FrameworkBeans.findSessionBean().mberName = resultMap.getStr("mberName");
			FrameworkBeans.findSessionBean().mberSttus = resultMap.getStr("mberSttus");
			FrameworkBeans.findSessionBean().moblphonNo = resultMap.getStr("moblphonNo");
			FrameworkBeans.findSessionBean().emailAddress = resultMap.getStr("emailAddress");
			FrameworkBeans.findSessionBean().chargeId = resultMap.getStr("chargeId");

		} else {
			resultCode = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
		}

		return new ResultMessage(resultCode, rtrn);
	}
}
