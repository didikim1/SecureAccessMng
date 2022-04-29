package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkPagingUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.nrlmber.NrlmberrMapper;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.nrlmberHistory.NrlmberHistoryMapper;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz")
public class NrlmberBiz
{
    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(NrlmberBiz.class.getName());

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.nrlmber.NrlmberrMapper")
    NrlmberrMapper mMapper;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.nrlmberHistory.NrlmberHistoryMapper")
    NrlmberHistoryMapper mHistoryMapper;

    /**
    * 페이징 데이터
    * @param paramMap
    * @return
    */
    public BasicBean ListPagingData(MyMap paramMap)
    {
        BasicBean resultBean = null;
        FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 10));
        resultBean = FrameworkPagingUtils.pagingData(paramMap,
        		paramMap.getInt("rows", 10),
        		mMapper.SelectOnePagingCount(paramMap),
        		mMapper.ListPagingData(paramMap));

        return resultBean;
    }

    /**
    * 상세 조회
    * @param paramMap
    * @return
    */
    public MyCamelMap SelectOneData(MyMap paramMap)
    {
        return mMapper.SelectOneData(paramMap);
    }

    /**
    * 등록/수정
    * @param paramMap
    * @return
    */
    public int RegisterData(MyMap paramMap)
    {
        System.out.println("==================inset 전 ================");
        System.out.println(paramMap);
        System.out.println("==================//inset 전 ================");
    	if( mMapper.RegisterData(paramMap)  >0 )
    	{
    	 System.out.println("==================inset 후 ================");
         System.out.println(paramMap);
         System.out.println("==================//inset 후 ================");
                paramMap.put("nrlmberId", paramMap.getInt("seq"));
    		paramMap.put("procSttus", "I");
    		return mHistoryMapper.ModifyData(paramMap);
    	}

    	return 0;
    }

    /**
    * 수정
    * @param paramMap
    * @return
    */
    public int ModifyData(MyMap paramMap)
    {

    	if(mMapper.ModifyData(paramMap) > 0 )
        {
    		paramMap.put("procSttus", "U");
        	return mHistoryMapper.ModifyData(paramMap);

        }
        return 0;
    }

    /**
    * 삭제
    * @param paramMap
    * @return
    */
    public int DeleteData(MyMap paramMap)
    {

//    	if (mMapper.DeleteData(paramMap) > 0 )
//    	{
//    		paramMap.put("procSttus", "D");
//    		paramMap.put("mberSttus", "C");
//    		return mHistoryMapper.DeleteData(paramMap);
//    	}
    	return mMapper.DeleteData(paramMap);
    }
}
