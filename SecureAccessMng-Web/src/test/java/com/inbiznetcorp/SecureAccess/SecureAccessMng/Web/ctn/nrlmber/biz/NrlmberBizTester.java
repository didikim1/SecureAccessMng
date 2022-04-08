package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NrlmberBizTester
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.nrlmber.biz.NrlmberBiz")
    NrlmberBiz mBiz;

    @Test
    public void 일반회원조회()
    {
        BasicBean bean = mBiz.ListPagingData(new MyMap());

        List<MyCamelMap> list = bean.getList();

        for (MyCamelMap info : list)
        {
            System.out.println(info);
        }

    }
    
    @Test
    public void 일반회원등록()
    {
    	MyMap paramMap = new MyMap();
//    	paramMap.put("seq", 			"회원ID");
    	paramMap.put("dpamentId",   	2);
    	paramMap.put("positionId",  	4);
    	paramMap.put("roleId", 			1);
    	paramMap.put("chargeId",    	1);
    	paramMap.put("dpamentName", 	"재경팀");
    	paramMap.put("positionName",	"과장");
    	paramMap.put("roleName", 		"읽기쓰기수정삭제");
    	paramMap.put("uniqId", 			"abcde1234");
    	paramMap.put("mberName", 		"홍길동");
    	paramMap.put("mberSttus", 		"A");
    	paramMap.put("moblphonNo",  	"01012345678");
    	paramMap.put("emailAddress", 	"이메일주소");
    	paramMap.put("frstRegisterId",  "hsjeon1224");
    	
    	int iRtn = mBiz.RegisterData(paramMap);
    		
    	System.out.println(iRtn);
    }
    	

    @Test
    public void 일반회원상세()
    {
    	MyMap paramMap = new MyMap();
    	paramMap.put("seq", 1);
    	MyCamelMap  info = mBiz.SelectOneData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
  	
    }
    	
    @Test
    public void 일반회원수정()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("seq", 			2);
    	paramMap.put("mberName", 		"김다혜");
    	paramMap.put("moblphonNo",  	"777777777777");
    	paramMap.put("emailAddress", 	"kdh1126@inbiznercorp.com");
    	paramMap.put("lastUpdusrId",  	"김다혜");
    	
    	int iRtn = mBiz.ModifyData(paramMap);
    		
    	System.out.println(iRtn);
    	
    }

    @Test
    public void 일반회원삭제()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("seq", 1);
    	int  info = mBiz.DeleteData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
    }
}
