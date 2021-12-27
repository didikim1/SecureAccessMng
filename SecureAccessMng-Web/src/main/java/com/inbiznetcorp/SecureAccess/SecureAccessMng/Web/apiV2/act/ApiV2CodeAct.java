package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.act;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.dto.CodeDTO;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultCode;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.result.ResultMessage;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper;

@Controller
@RequestMapping("/api/v2/code")
public class ApiV2CodeAct
{
    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(ApiV2CodeAct.class.getName());

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper")
    CodeMapper mCodeMapper;

    public static String kKey_title     = "title";
    public static String kKey_type      = "type";

    @RequestMapping(value="/findByTitle.do", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody ResultMessage findByUserInfo(@RequestBody CodeDTO dto)
    {
        Logger.info("##############");
        String result_code = ResultCode.RESULT_NOT_FOUND;

        List<MyCamelMap>      responseMap     = null;
        MyMap                 paramMap        = new MyMap();

        paramMap.put(kKey_title,       dto.getTitle());
        paramMap.put(kKey_type,        dto.getType());

        responseMap = mCodeMapper.ListData(paramMap);

        if(responseMap != null)
        {
            result_code = ResultCode.RESULT_OK;
        }

        return new ResultMessage(result_code, responseMap);
    }
}
