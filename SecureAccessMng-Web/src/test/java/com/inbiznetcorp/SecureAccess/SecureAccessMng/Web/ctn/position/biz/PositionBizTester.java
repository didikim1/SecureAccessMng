package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.position.biz;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.position.biz.PositionBiz;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;

import scala.annotation.meta.param;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionBizTester
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.position.biz.PositionBiz")
    PositionBiz mBiz;

    @Test
    public void 직위조회()
    {
    	BasicBean  bean = mBiz.ListPagingData(new MyMap());

        List<MyCamelMap> list = bean.getList();

        for (MyCamelMap info : list)
        {
            System.out.println(info.toString());
        }

    }

    @Test
    public void 직위등록()
    {
    	MyMap paramMap = new MyMap();
    	paramMap.put("positionId", "직위고유ID");
    	paramMap.put("position", "직위명");
    	paramMap.put("frstRegisterId", "김다혜");
    	
    	int iRtn = mBiz.RegisterData(paramMap);
    		
    	System.out.println(iRtn);
    }
    	

    @Test
    public void 직위상세()
    {
    	MyMap paramMap = new MyMap();
    	paramMap.put("positionId", 2);
    	MyCamelMap  info = mBiz.SelectOneData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
    }
    	
    @Test
    public void 직위수정()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("positionId", "2");
    	paramMap.put("position", "직위");
    	paramMap.put("lastUpdusrId", "수정자");
    	
    	int iRtn = mBiz.ModifyData(paramMap);
    		
    	System.out.println(iRtn);

    }

    @Test
    public void 직위삭제()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("positionId", 2);
    	int  info = mBiz.DeleteData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
    }
}
