package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.excel;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;


@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.excel.ExcelWrite")
public class ExcelWrite {
	public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExcelWrite.class);


	public void selectExcelList(HttpServletResponse response, String[] title, String[] cols, List<MyCamelMap> list, String fileName) {

	    	// 메모리에 100개의 행을 유지합니다. 행의 수가 넘으면 디스크에 적습니다.
			SXSSFWorkbook wb 	= new SXSSFWorkbook(60000);
	    	Sheet 		  sheet = wb.createSheet();

	    	Row row = sheet.createRow(0);
            Cell cell = null;

           
            for (int i = 0; i < title.length; i++) 
            {
            	cell = row.createCell(i);
            	cell.setCellValue(title[i]);
			}
            
	    	for (int i = 0; i < list.size(); i++)
	    	{
	    		 MyCamelMap vo = list.get(i);
	    		 row = sheet.createRow(i+1);
	    		 cell = null;
	    		 
	    		 for (int j = 0; j < cols.length; j++) 
	    		 {
	    			 cell = row.createCell(j);
	    			 cell.setCellValue(vo.getStr(cols[j], ""));
				 }
			}
	    try {

	        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
	        response.setHeader("Content-Disposition", "attachment; filename="+encodeFileName(fileName + ".xlsx"));
	        response.setHeader("Content-Transfer-Encoding", "binary;");
	        response.setHeader("Expires", "-1");
	        response.setHeader("Cache-Control", "no-cache");
	        wb.write(response.getOutputStream());

	    } catch(Exception e) {

	        e.printStackTrace();

	        response.setHeader("Set-Cookie", "fileDownload=false; path=/");
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	        response.setHeader("Content-Type","text/html; charset=utf-8");

	        OutputStream out = null;
	        try {
	            out = response.getOutputStream();
	            byte[] data = new String("fail..").getBytes();
	            out.write(data, 0, data.length);
	        } catch(Exception ignore) {
	            ignore.printStackTrace();
	        } finally {
	            if(out != null) try { out.close(); } catch(Exception ignore) {}
	        }

	    } finally {
	        // 디스크 적었던 임시파일을 제거합니다.
	        wb.dispose();
	        try { wb.close(); } catch(Exception ignore) {}
	    }
	}

    private String encodeFileName(String filename) {
        try {
            return URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }



}
