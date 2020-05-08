package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.code.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkPagingUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctncode.biz.CtnCodeBiz")
public class CtnCodeBiz
{
	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(CtnCodeBiz.class.getName());

	@Resource(name = "com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.code.CodeMapper")
	CodeMapper mMapper;

	/**
	 * 데이터 (전체)
	 * @param paramMap
	 * @return
	 */
	public BasicBean ListData(MyMap paramMap) {
		BasicBean resultBean = null;
		
		paramMap.put("sidx", paramMap.getStr("sidx", "codeSeq"));
		paramMap.put("sord", paramMap.getStr("sord", "desc"));

		FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 1000));
		resultBean = FrameworkPagingUtils.pagingData(paramMap, paramMap.getInt("rows", 1000),
				mMapper.SelectOnePagingCount(paramMap), mMapper.ListPagingData(paramMap));

		return resultBean;
	}

	/**
	* 페이징 데이터
	* @param paramMap
	* @return
	*/
	public BasicBean ListPagingData(MyMap paramMap) {
		BasicBean resultBean = null;

		FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 10));
		resultBean = FrameworkPagingUtils.pagingData(paramMap, paramMap.getInt("rows", 10),
				mMapper.SelectOnePagingCount(paramMap), mMapper.ListPagingData(paramMap));

		return resultBean;
	}

	/**
	* 상세 조회
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap) {
		return mMapper.SelectOneData(paramMap);
	}

	/**
	* 등록/수정
	* @param paramMap
	* @return
	*/
	public int RegisterData(MyMap paramMap) {
		return mMapper.RegisterData(paramMap);
	}

	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap) {
		return mMapper.ModifyData(paramMap);
	}

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap) {
		return mMapper.DeleteData(paramMap);
	}
}
