package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.charge.biz;

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
public class ChargeBizTester
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.charge.biz.ChargeBiz")
    ChargeBiz mBiz;

    @Test
    public void 담당업무조회()
    {
    	BasicBean  bean = mBiz.ListPagingData(new MyMap());
        List<MyCamelMap> list = bean.getList();

        for (MyCamelMap info : list)
        {
            System.out.println(info.toString());
        }

    }

    @Test
    public void 담당업무등록()
    {
    	MyMap paramMap = new MyMap();
//    	paramMap.put("seq", "고유ID");
    	paramMap.put("name", "회계");
    	paramMap.put("frstRegisterId", "김다혜");
    	
    	int iRtn = mBiz.RegisterData(paramMap);
    		
    	System.out.println(iRtn);
    }
    	

    @Test
    public void 담당업무상세()
    {
    	MyMap paramMap = new MyMap();
    	paramMap.put("seq", 2);
    	MyCamelMap  info = mBiz.SelectOneData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
    }
    	
    @Test
    public void 담당업무수정()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("seq", "고유ID");
    	paramMap.put("name", "담당업무");
    	paramMap.put("frstRegisterId", "등록자");
    	
    	int iRtn = mBiz.ModifyData(paramMap);
    		
    	System.out.println(iRtn);
    	
    }

    @Test
    public void 담당업무삭제()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("seq", 3);
    	int  info = mBiz.DeleteData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
    	
    }
}
