package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqacclog;

import org.springframework.stereotype.Repository;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.config.mybatis.support.Master;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;

@Master
@Repository("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.eqacclog.EqAccLogMapper")
public interface EqAccLogMapper
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
	public java.util.List ListPagingData(MyMap paramMap);

	/**
	 * 목록
	 * @param paramMap
	 * @return
	 */
	public java.util.List ListData(MyMap paramMap);




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

	public int UpdateLogOutAccLog(MyMap paramMap);

	public int UpdateLogOutAccLogByRefNrlmberWithProcessid(MyMap paramMap);

	public int UpdateLogOutAccLogByRefNrlmber(MyMap paramMap);

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap);


	public int SelectOnePagingAllCount(MyMap paramMap);

	public java.util.List ListAllData(MyMap paramMap);

}
