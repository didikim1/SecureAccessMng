package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkPagingUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.bbs.BBSMasterDao;


@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSMasterBiz")
public class BBSMasterBiz
{
    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.bbs.BBSMasterDao")
    BBSMasterDao mDao;

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(BBSMasterBiz.class.getName());

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
                mDao.SelectOnePagingCount(paramMap),
                mDao.ListPagingData(paramMap));

        return resultBean;
    }

    /**
     * 전체 데이터
     * @param paramMap
     * @return
     */
    public List<MyCamelMap> ListAllData(MyMap paramMap)
    {
    	return mDao.ListAllData(paramMap);
    }

    /**
    * 상세 조회
    * @param paramMap
    * @return
    */
    public MyCamelMap SelectOneData(MyMap paramMap)
    {
        return mDao.SelectOneData(paramMap);
    }

    /**
     * 상세 조회
     * @param bbsCode
     * @return
     */
    public int findOneDataBBSID(String bbsCode)
    {
    	MyMap paramMap = new MyMap();
    	paramMap.put("bbsCode", bbsCode);

        return mDao.SelectOneData(paramMap).getInt("bbsId", 0);
    }

    /**
    * 등록/수정
    * @param paramMap
    * @return
    */
    public int RegisterData(MyMap paramMap)
    {
        return mDao.RegisterData(paramMap);
    }

    /**
    * 수정
    * @param paramMap
    * @return
    */
    public int ModifyData(MyMap paramMap)
    {
        return mDao.ModifyData(paramMap);
    }

    /**
    * 삭제
    * @param paramMap
    * @return
    */
    public int DeleteData(MyMap paramMap)
    {
        return mDao.DeleteData(paramMap);
    }
}
