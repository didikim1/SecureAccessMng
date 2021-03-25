<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>


<%
InetAddress inetAddress;
StringBuilder sb = new StringBuilder();
String ipAddress="",macAddress="";
int i=0;
try {
    inetAddress=InetAddress.getLocalHost();
    ipAddress=inetAddress.getHostAddress();
    NetworkInterface network=NetworkInterface.getByInetAddress(inetAddress);
     byte[] hw=network.getHardwareAddress();
     for(i=0; i<hw.length; i++)
        sb.append(String.format("%02X%s", hw[i], (i < hw.length - 1) ? "-" :
         ""));
    macAddress=sb.toString();
  } catch(Exception e) {
   out.print("<br/>"+e.toString());
    macAddress="-";
  }
  out.print("<br/>"+ipAddress);
  out.print("<br/>"+macAddress);
 %>