package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.role.biz;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyCamelMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;

import scala.annotation.meta.param;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleBizTesster
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.role.biz.RoleBiz")
    RoleBiz mBiz;

    @Test
    public void 권한조회()
    {
        BasicBean  bean = mBiz.ListPagingData(new MyMap());

        List<MyCamelMap> list = bean.getList();


        for (MyCamelMap info : list)
        {
            System.out.println(info.toString());
        }

    }

    @Test
    public void 권한등록()
    {
    	MyMap paramMap = new MyMap();
    	paramMap.put("role", "권한");
    	paramMap.put("roleName", "권한명칭");
    	paramMap.put("frstRegisterId", "등록자");
    	
    	int iRtn = mBiz.RegisterData(paramMap);
    		
    	System.out.println(iRtn);
    }
    	

    @Test
    public void 권한상세()
    {
    	MyMap paramMap = new MyMap();
    	paramMap.put("roleId", 2);
    	MyCamelMap  info = mBiz.SelectOneData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
  	
    }
    	
    @Test
    public void 권한수정()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("role", "권한");
    	paramMap.put("roleName", "권한명칭");
    	paramMap.put("frstRegisterId", "등록자");
    	
    	int iRtn = mBiz.ModifyData(paramMap);
    		
    	System.out.println(iRtn);
    	
    }

    @Test
    public void 권한삭제()
    {
    	
    	MyMap paramMap = new MyMap();
    	paramMap.put("roleId", 2);
    	int  info = mBiz.DeleteData(paramMap);
    	
    	System.out.println("InOF::"+info);
    	
    }
}
