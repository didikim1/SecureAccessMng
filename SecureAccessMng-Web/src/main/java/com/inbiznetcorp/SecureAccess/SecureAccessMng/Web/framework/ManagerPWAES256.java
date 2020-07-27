package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.framework;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class ManagerPWAES256
{
    private static volatile ManagerPWAES256 INSTANCE;

    final static String secretKey   = "182B86722E7877625C598F0ECF51E4D4";

    public static ManagerPWAES256 getInstance(){
        if(INSTANCE==null){
            synchronized(ManagerPWAES256.class){
                if(INSTANCE==null)
                    INSTANCE=new ManagerPWAES256();
            }
        }
        return INSTANCE;
    }

    private ManagerPWAES256()
    {

    }

    //암호화
    public static String AES_Encode(String str) throws Exception
    {
        SecretKey secureKey = new SecretKeySpec(DatatypeConverter.parseHexBinary(secretKey), "AES");

        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");

        c.init(Cipher.ENCRYPT_MODE, secureKey);
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));

        return DatatypeConverter.printHexBinary(encrypted);
    }

    //복호화
    public static String AES_Decode(byte[] str) throws Exception
    {
    	byte[] bytePassword =   DatatypeConverter.parseHexBinary( secretKey );

        SecretKey secureKey = new SecretKeySpec( bytePassword , "AES");

        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secureKey);
        byte[] byteStr = str;
        return new String(c.doFinal(byteStr),"UTF-8");
    }
}
