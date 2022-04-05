package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.common.biz.CommonBiz")
public class CommonBiz
{
    @Value("${spring.profiles.active}")
    public String active;
}
