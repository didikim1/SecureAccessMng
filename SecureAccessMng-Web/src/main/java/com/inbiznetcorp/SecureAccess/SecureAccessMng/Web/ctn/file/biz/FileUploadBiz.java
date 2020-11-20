package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.FrameworkBeans;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDao;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDetailDao;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.file.biz.FileUploadBiz")
public class FileUploadBiz
{
	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(FileUploadBiz.class.getName());

	private static final String ConfigFileManager = null;

	String UPLOADDIR = "UPLOAD";

	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDao")
	FileDao mFileDao;

	@Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.file.FileDetailDao")
	FileDetailDao mFileDetailDao;

	 @Autowired
	 ServletContext context;

	public java.util.List<MyCamelMap> AllListData(MyMap paramMap)
	{
		return mFileDetailDao.AllListData(paramMap);
	}

	public List<MyMap> RegisterProcFileupload(String SESSION_BOARD_ID)
	{
		MyCamelMap					objAtchfile		 		= null;
		MultipartHttpServletRequest objMultipartRequest 	= null;
		List<MyMap>					objDetailFileMaps		= new ArrayList<MyMap>();
		String						strSESSION_BOARD_ID 	= SESSION_BOARD_ID;
		String						strSESSION_USER_ID		= FrameworkBeans.findHttpServletBean().findClientRequestParameter().getStr("SESSION_USER_ID");
		
		objMultipartRequest 			= (MultipartHttpServletRequest) FrameworkBeans.findHttpServletBean().getHttpServletRequest();
//		List<MultipartFile> fileList 	= objMultipartRequest.getFiles("files[]");
		List<MultipartFile> fileList 	= objMultipartRequest.getFiles("files");


		if ( FrameworkUtils.isNull( strSESSION_BOARD_ID ) ) return null;

		objAtchfile			= this.SelectOneAtchFile( strSESSION_BOARD_ID, strSESSION_USER_ID );

		Logger.info("## strSESSION_BOARD_ID = " +strSESSION_BOARD_ID);
		Logger.info("## strSESSION_USER_ID  = " +strSESSION_USER_ID);
		Logger.info("## objAtchfileID  = " +objAtchfile.getInt("atchFileId"));
		
		
//		  MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) FrameworkBeans.findHttpServletBean().getHttpServletRequest();
//          Iterator iter = mhsr.getFileNames();
		
		System.out.println("?!!!!![0000]");
		for (MultipartFile multipartFile : fileList)
		{
			System.out.println("?!!!!![111111]");
			objDetailFileMaps.add(fileSaveDirectoryCreate(objAtchfile, strSESSION_USER_ID, strSESSION_BOARD_ID, multipartFile, "Y"));
		}
          
//          MultipartFile mfile = null;
//          String fieldName = "";
//          
//          
//          String path = "D://";
//          
//          System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//          
//          try {
//        	  while (iter.hasNext())
//              {
//        		  System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@[1]");
//                  fieldName = iter.next().toString(); // 내용을 가져와서
//                  mfile = mhsr.getFile(fieldName);
//                  String origName;
//                  origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); //한글꺠짐 방지 // 파일명이 없다면
//
//                  if ("".equals(origName)) { continue; } // 파일 명 변경(uuid로 암호화)
//
//                  String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
//                  String saveFileName = getUuid() + ext;//getUuid() + ext; // 설정한 path에 파일저장
//                  File serverFile = new File(path + File.separator + saveFileName);
//                  mfile.transferTo(serverFile);
//                  Map file = new HashMap();
//                  file.put("origName", origName);
//                  file.put("sfile", serverFile);
////                  resultList.add(file);
//
//                  //DB에 들어갈만한 건들
//                  System.out.println("복호화된 파일 이름 : "+serverFile.getName()); //복호화된 파일 이름
//                  System.out.println("물리적 저장 경로  : "+serverFile.getAbsolutePath()); //물리적 저장 경로
//                  System.out.println("파일 크기 : "+serverFile.length()); //파일 크기
//                  System.out.println("원래 파일 명 : "+origName); //원래 파일 명
//              }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return objDetailFileMaps;
	}

	/**
	 * SessionBoardId 값으로 이미 등록된 atchFile 찾거나 생성한다.
	 * @param SESSION_BOARD_ID
	 * @return
	 */
	private MyCamelMap SelectOneAtchFile(String SESSION_BOARD_ID, String SESSION_USER_ID)
	{
		MyCamelMap  objAtchfile = null;
		MyMap 		paramMap 	= new MyMap();

		paramMap.put("SESSION_BOARD_ID", SESSION_BOARD_ID);

		objAtchfile = mFileDao.SelectOneData(paramMap);

		if ( objAtchfile == null )
		{
			paramMap.put("SESSION_USER_ID", SESSION_USER_ID);
			mFileDao.RegisterData(paramMap);

			objAtchfile = mFileDao.SelectOneData(paramMap);
		}
		return objAtchfile;
	}

	private MyMap fileSaveDirectoryCreate(MyCamelMap objATCHFileMap, String strSESSION_USER_ID, String strGenerateSessionID, MultipartFile multiPartFile, String isView)
	{
		String			   strDir			= null;
		String			   strSaveUrl 		= null;
		String			   strSavePath 		= null;
		String			   SESSION_USER_ID	= null;

		MultipartFile 	 			objMultipartFile 	 = null;
		HttpServletRequest 			objRequest 			 = FrameworkBeans.findHttpServletBean().getHttpServletRequest();
		
		SESSION_USER_ID = strSESSION_USER_ID;
		
		String 			   strDate	  = FrameworkUtils.getDateToStr("yyyyMMdd");

		strDir	   =  "/" + UPLOADDIR + "/" + strDate;

//		strSaveUrl 		= objRequest.getScheme()+"://"+objRequest.getServerName()+":"+objRequest.getServerPort() + strDir;
		strSavePath 	= context.getRealPath("")+File.separator+strDir;
//		strSavePath 	= ConfigFileManager.getInstance().getFile_Upload_Path()+File.separator+strDir;

		if( new File(strSavePath).exists() == false ) { new File(strSavePath).mkdirs(); }

//		Logger.info("   ConfigFileManager.getInstance().getFile_Upload_Path()= " + ConfigFileManager.getInstance().getFile_Upload_Path());
		Logger.info("   File Save Dir Path = " + strSavePath);
		Logger.info("   File Save Dir URL  = " + strSaveUrl);

		objMultipartFile	= multiPartFile;

		if ( objMultipartFile.isEmpty() == true )
		{
			Logger.info("   objMultipartFile Empty() " );
			return null;
		}

		System.out.println("------------- file start -------------");
		System.out.println("name : "+objMultipartFile.getName());
		try {
			System.out.println("filename : "+URLDecoder.decode(objMultipartFile.getOriginalFilename(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("size : "+objMultipartFile.getSize());
		System.out.println("-------------- file end --------------\n");
		
		

		String strORIGNLFileNm  = null;
		int	   iAtchFileId 		= objATCHFileMap.getInt("atchFileId");
		String strFileExtsn     = FilenameUtils.getExtension(objMultipartFile.getOriginalFilename());
		try {
			strORIGNLFileNm  = URLDecoder.decode(objMultipartFile.getOriginalFilename(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String stFileNm  		= strGenerateSessionID +  "." +strFileExtsn;
		
		
		
		
		String strFileUrl		= strSaveUrl  + "/" +  stFileNm;
		String strFilePath		= strSavePath + File.separator +  stFileNm;

		Logger.debug("## strFileUrl = " +strFileUrl);
		Logger.debug("## strFilePath = " +strFilePath);
		
		Logger.debug("## strFilePath [0]");
		MyMap objDetailFileMap = new MyMap();
		Logger.debug("## strFilePath [1]");
		try {
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("SESSION_USER_ID",   SESSION_USER_ID);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("SESSION_BOARD_ID",  strGenerateSessionID);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("atchFileId",   	 iAtchFileId);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("streFileNm",   	 stFileNm);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("orignlFileNm", 	 strORIGNLFileNm);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("fileExtsn",    	 strFileExtsn);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("fileStreCours",    strFilePath);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("urlPath",    		 strFileUrl);
			Logger.debug("## strFilePath [1]");
			objDetailFileMap.put("isview",    		 isView);
			Logger.debug("## strFilePath [1]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		Logger.info(" objDetailFileMap = " +objDetailFileMap.toString());

		mFileDetailDao.RegisterData(objDetailFileMap);

		this.upload(objMultipartFile, strFilePath);

		return objDetailFileMap;
	}




	private void upload(MultipartFile multipartFile, String strSavePath)
	{
		try
		{
			multipartFile.transferTo(new File(strSavePath));
		}
		catch (IllegalStateException | IOException e)
		{
			e.printStackTrace();
			Logger.warn("============================================================");
			Logger.warn("[RED:00][WARN]"+e.getMessage());
			Logger.warn("============================================================");
		}
	}
	
	 public static String getUuid() {
	       return UUID.randomUUID().toString().replaceAll("-", "");
	  }


}
