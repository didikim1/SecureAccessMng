package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.role.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.beans.BasicBean;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.mymap.MyMap;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework.utils.FrameworkPagingUtils;
import com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.role.RoleMapper;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.ctn.role.biz.RoleBiz")
public class RoleBiz
{
    @Resource(name="com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.mapper.ctn.role.RoleMapper")
    RoleMapper mMapper;

    public BasicBean ListPagingData(MyMap paramMap)
    {
        BasicBean resultBean = null;

        FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 10));
        resultBean = FrameworkPagingUtils.pagingData(paramMap,
                paramMap.getInt("rows", 10),
                mMapper.SelectOnePagingCount(paramMap),
                mMapper.ListPagingData(paramMap));

        return resultBean;
    }

}
