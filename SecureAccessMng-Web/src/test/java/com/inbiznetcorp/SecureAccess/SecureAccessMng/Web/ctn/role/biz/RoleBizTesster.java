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
        BasicBean  bean = mBiz.ListPagingData(new MyMap());

        List<MyCamelMap> list = bean.getList();


        for (MyCamelMap info : list)
        {
            System.out.println(info.toString());
        }

    }


    @Test
    public void 권한상세()
    {
        BasicBean  bean = mBiz.ListPagingData(new MyMap());

        List<MyCamelMap> list = bean.getList();


        for (MyCamelMap info : list)
        {
            System.out.println(info.toString());
        }

    }

    @Test
    public void 권한수정()
    {
        BasicBean  bean = mBiz.ListPagingData(new MyMap());

        List<MyCamelMap> list = bean.getList();


        for (MyCamelMap info : list)
        {
            System.out.println(info.toString());
        }

    }

    @Test
    public void 권한삭제()
    {
        BasicBean  bean = mBiz.ListPagingData(new MyMap());

        List<MyCamelMap> list = bean.getList();


        for (MyCamelMap info : list)
        {
            System.out.println(info.toString());
        }

    }
}
