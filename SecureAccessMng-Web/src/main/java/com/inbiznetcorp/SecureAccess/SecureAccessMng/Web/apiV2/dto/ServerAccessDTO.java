package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.apiV2.dto;

public class ServerAccessDTO
{
    /** 작업 고유 키값 */
    private int  worktypecodeId = 0;

    /** IDC Center 고유 키값 */
    private int  refEqIdc       = 0;

    /** IDC Center Server 고유 키값 */
    private int  refEqList      = 0;

    /** Server idpwd 고유 키값 */
    private int  refEqIdpwd     = 0;

    /** 사용자 고유 키값 */
    private int  refNrlmber     = 0;

    /** 접근 IP(클라이언트) 고유 키값 */
    private int  refAllowIp     = 0;

    /** SSH Client 프로세스 ID */
    private String processID    = null;

    /** 서버 접근 사유 */
    private String reason       = null;

    public int getWorktypecodeId()
    {
        return worktypecodeId;
    }
    public void setWorktypecodeId(int worktypecodeId)
    {
        this.worktypecodeId = worktypecodeId;
    }
    public int getRefEqIdc()
    {
        return refEqIdc;
    }
    public void setRefEqIdc(int refEqIdc)
    {
        this.refEqIdc = refEqIdc;
    }
    public int getRefEqList()
    {
        return refEqList;
    }
    public void setRefEqList(int refEqList)
    {
        this.refEqList = refEqList;
    }
    public int getRefEqIdpwd()
    {
        return refEqIdpwd;
    }
    public void setRefEqIdpwd(int refEqIdpwd)
    {
        this.refEqIdpwd = refEqIdpwd;
    }
    public int getRefNrlmber()
    {
        return refNrlmber;
    }
    public void setRefNrlmber(int refNrlmber)
    {
        this.refNrlmber = refNrlmber;
    }
    public int getRefAllowIp()
    {
        return refAllowIp;
    }
    public void setRefAllowIp(int refAllowIp)
    {
        this.refAllowIp = refAllowIp;
    }
    public String getProcessID()
    {
        return processID;
    }
    public void setProcessID(String processID)
    {
        this.processID = processID;
    }
    public String getReason()
    {
        return reason;
    }
    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
