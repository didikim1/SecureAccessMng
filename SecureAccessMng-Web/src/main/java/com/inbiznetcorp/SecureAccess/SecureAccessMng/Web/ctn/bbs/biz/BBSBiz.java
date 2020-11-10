package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkPagingUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.bbs.BBSDao;


@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.bbs.biz.BBSBiz")
public class BBSBiz
{
    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.bbs.BBSDao")
    BBSDao mDao;

    @Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz")
    FileUploadBiz mFileUploadBiz;

    private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(BBSBiz.class.getName());

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
    
    public List<MyCamelMap> ListLatestData(MyMap paramMap)
    {
    	List<MyCamelMap> resultList = null;

        FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 10));
        resultList = mDao.ListPagingData(paramMap);

        return resultList;
    }

    /**
    * 상세 조회
    * @param paramMap
    * @return
    */
    public MyCamelMap SelectOneData(MyMap paramMap)
    {
    	MyCamelMap SelectOneData = mDao.SelectOneData(paramMap);

    	SelectOneData.put("nttCn", FrameworkUtils.unescapeHtml(SelectOneData.getStr("nttCn")));

    	Logger.info("## SelectOneData = " + SelectOneData);

    	paramMap.put("SESSION_BOARD_ID", SelectOneData.getStr("sessionBoardId"));

    	List<MyCamelMap> ListFileData =  mFileUploadBiz.AllListData(paramMap);

    	SelectOneData.put("ListFileData", ListFileData);

        return SelectOneData;
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
