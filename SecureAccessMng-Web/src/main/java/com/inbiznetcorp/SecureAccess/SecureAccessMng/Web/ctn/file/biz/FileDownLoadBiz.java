package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDetailDao;


@Service("com.lab603.albert.blog.common.biz.FileDownLoadBiz")
public class FileDownLoadBiz
{
	String UPLOADDIR = "Upload";

	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDetailDao")
	FileDetailDao mFileDetailDao;

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(FileDownLoadBiz.class.getName());

	public void downloadFile(HttpServletResponse response, MyMap paramMap)
	{
		FileInputStream 	fis = null;
		ServletOutputStream out = null;

		MyCamelMap   objSelectOneDataMap			= null;
		String		 strFileStreCours				= null;
		String		 strOrignlFileNm				= null;

		objSelectOneDataMap = this.SelectOneData(paramMap);
		strOrignlFileNm		= objSelectOneDataMap.getStr("orignlFileNm");
		strFileStreCours	= objSelectOneDataMap.getStr("fileStreCours");

		try {
			out = response.getOutputStream();

			File file = new File( strFileStreCours );

			fis = new FileInputStream(file);


			response.setContentLength( (int)file.length() );
			response.setHeader("Content-Length", "" + (int)file.length() );

			response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(strOrignlFileNm, "UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary;");

			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");

			int byteRead = 0;
			byte[] buffer = new byte[8192];
			while( (byteRead = fis.read(buffer, 0, 8192) ) != -1 ) {
				out.write(buffer, 0, byteRead);
			}

			out.flush();
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally {
			if( out != null )
				try { out.close();	}catch(IOException e) {}
			if( fis != null )
				try { fis.close();	}catch(IOException e) {}
		}
	}

	/**
	* 페이징 데이터
	* @param paramMap
	* @return
	*/
	public BasicBean ListPagingData(MyMap paramMap)
	{
		return null;
	}

	/**
	* 상세 조회
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap)
	{
		return mFileDetailDao.SelectOneData(paramMap);
	}

	/**
	* 등록/수정
	* @param paramMap
	* @return
	*/
	public int RegisterData(MyMap paramMap)
	{
		return -1;
	}

	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap)
	{
		return -1;
	}

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap)
	{
		return -1;
	}
}
