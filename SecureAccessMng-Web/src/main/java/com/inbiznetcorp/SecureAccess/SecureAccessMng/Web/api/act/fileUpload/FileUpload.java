package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.fileUpload;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


// /exe/FileUpload/fileUp.do
@Controller
@RequestMapping("/exe/FileUpload")
public class FileUpload
{
	final String pagePrefix = "login";

//	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(FileUpload.class.getName());

	@RequestMapping(value = "/fileUp.do")
    public String ExcelUp(HttpServletRequest req, HttpServletResponse rep){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

//		 return pagePrefix + "/index";

        //파일이 저장될 path 설정
        String path = "D://";
        Map returnObject = new HashMap();

        try
        {
        	// MultipartHttpServletRequest 생성
            MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
            Iterator iter = mhsr.getFileNames();
            MultipartFile mfile = null;
            String fieldName = "";
            List resultList = new ArrayList(); // 디레토리가 없다면 생성
            File dir = new File(path);

            if (!dir.isDirectory())
            {
                dir.mkdirs();
            }

            // 값이 나올때까지
            while (iter.hasNext())
            {
                fieldName = iter.next().toString(); // 내용을 가져와서
                mfile = mhsr.getFile(fieldName);
                String origName;
                origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); //한글꺠짐 방지 // 파일명이 없다면

                if ("".equals(origName)) { continue; } // 파일 명 변경(uuid로 암호화)

                String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
                String saveFileName = getUuid() + ext;//getUuid() + ext; // 설정한 path에 파일저장
                File serverFile = new File(path + File.separator + saveFileName);
                mfile.transferTo(serverFile);
                Map file = new HashMap();
                file.put("origName", origName);
                file.put("sfile", serverFile);
                resultList.add(file);

                //DB에 들어갈만한 건들
                System.out.println("복호화된 파일 이름 : "+serverFile.getName()); //복호화된 파일 이름
                System.out.println("물리적 저장 경로  : "+serverFile.getAbsolutePath()); //물리적 저장 경로
                System.out.println("파일 크기 : "+serverFile.length()); //파일 크기
                System.out.println("원래 파일 명 : "+origName); //원래 파일 명

            }

            returnObject.put("files", resultList);
            returnObject.put("params", mhsr.getParameterMap());

        } catch (UnsupportedEncodingException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IllegalStateException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return pagePrefix + "/index";
    }

	 public static String getUuid() {
	       return UUID.randomUUID().toString().replaceAll("-", "");
	  }


}
