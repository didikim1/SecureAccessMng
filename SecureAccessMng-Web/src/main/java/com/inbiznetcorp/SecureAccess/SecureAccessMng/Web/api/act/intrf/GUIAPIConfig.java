package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.api.act.intrf;

public class GUIAPIConfig
{
    public interface Cmd
    {
        /**
         *  초기값(Invalid)
         */
        final static String Cmd_Invalid    = "Invalid";



        /**
         * 허용IP 등록 (1110)
         */
        final static String Cmd_1110    = "1110";
        /**
         * 허용IP 변경 (1111)
         */
        final static String Cmd_1111    = "1111";
        /**
         * 허용IP 삭제 (1112)
         */
        final static String Cmd_1112    = "1112";
        /**
         * 허용IP 조회 (1113)
         */
        final static String Cmd_1113    = "1113";


        /**
         * 회원 등록 (1210)
         */
        final static String Cmd_1210    = "1210";
        /**
         * 회원 변경 (1211)
         */
        final static String Cmd_1211    = "1211";
        /**
         * 회원 삭제 (1212)
         */
        final static String Cmd_1212    = "1212";
        /**
         * 회원 조회 (1213)
         */
        final static String Cmd_1213    = "1213";
        /**
         * 회원 로그인 (1214)
         */
        final static String Cmd_1214    = "1214";



        /**
         * 접속승인 등록 (1210)
         */
        final static String Cmd_1310    = "1310";
        /**
         * 접속승인 변경 (1211)
         */
        final static String Cmd_1311    = "1311";
        /**
         * 접속승인 삭제 (1212)
         */
        final static String Cmd_1312    = "1312";
        /**
         * 접속승인 조회 (1213)
         */
        final static String Cmd_1313    = "1313";
        /**
         * 접속승인 접속승인요청 (1214)
         */
        final static String Cmd_1314    = "1314";
    }
}
