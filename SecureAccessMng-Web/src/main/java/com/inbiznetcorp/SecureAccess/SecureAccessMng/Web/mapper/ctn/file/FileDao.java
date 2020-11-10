package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file;

import org.springframework.stereotype.Repository;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.config.mybatis.support.Master;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;

@Master
@Repository("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDao")
public interface FileDao
{

	/**
	* 페이징 갯수
	* @param paramMap
	* @return
	*/

	public int SelectOnePagingCount(MyMap paramMap);

	/**
	* 페이징 목록
	* @param paramMap
	* @return
	*/
	public java.util.List<MyCamelMap> ListPagingData(MyMap paramMap);

	/**
	* 상세
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap);

	/**
	* 추가
	* @param paramMap
	* @return
	*/

	public int RegisterData(MyMap paramMap);

	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap);

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap);
}
