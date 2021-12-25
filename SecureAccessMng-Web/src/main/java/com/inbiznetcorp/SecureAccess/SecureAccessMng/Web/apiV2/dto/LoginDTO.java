package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.dto;

public class LoginDTO
{
    /** 유저 고유 ID */
    private String uniqid;

    /** 유저 고유 패스워드 */
    private String password;

    public String getUniqid()
    {
        return uniqid;
    }
    public void setUniqid(String uniqid)
    {
        this.uniqid = uniqid;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }


}
