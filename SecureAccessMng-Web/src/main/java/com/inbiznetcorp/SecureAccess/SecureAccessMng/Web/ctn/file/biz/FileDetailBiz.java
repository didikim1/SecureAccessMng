package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDetailDao;


@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz")
public class FileDetailBiz
{
	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(FileDetailBiz.class.getName());

	@Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDetailDao")
	FileDetailDao mService;

	/**
	* 페이징 데이터
	* @param paramMap
	* @return
	*/
	public BasicBean ListPagingData(MyMap paramMap)
	{
		BasicBean resultBean = null;
		return resultBean;
	}

	/**
	* 상세 조회
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap)
	{
		return mService.SelectOneData(paramMap);
	}

	/**
	* 등록/수정
	* @param paramMap
	* @return
	*/
	public int RegisterData(MyMap paramMap)
	{
		return mService.RegisterData(paramMap);
	}

	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap)
	{
		return mService.ModifyData(paramMap);
	}

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap)
	{
		return mService.DeleteData(paramMap);
	}
}
