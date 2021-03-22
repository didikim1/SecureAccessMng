package com.inbiznetcorp.SecureAccess.SecureAccessMng.Web.httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpRequestProc
{

    public String sendPacket(String url, Map<String, String> paramMap)
    {
        List<NameValuePair> paramList           = this.map2NameValuePairList(paramMap);
        String              rtnStr              = this.excute(url, paramList);


        return rtnStr;
    }

    public JSONObject sendPacket2(String url, Map<String, String> paramMap)
    {
        List<NameValuePair> paramList           = this.map2NameValuePairList(paramMap);
        String              rtnStr              = this.excute(url, paramList);

        JSONObject          rtnJSON             = null;
        try
        {
            rtnJSON             = (JSONObject) new JSONParser().parse( rtnStr );
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            rtnJSON             = new JSONObject();
        }

        return rtnJSON;
    }

    @SuppressWarnings("deprecation")
    private String excute(String url, List<NameValuePair> paramList)
    {
        HttpClient client = new DefaultHttpClient();
        StringBuffer returnData = new StringBuffer();
        BufferedReader rd = null;
        int soketTimeout = Integer.valueOf(180);
        try
        {
            client.getParams().setParameter("http.socket.timeout", new Integer(soketTimeout * 1000));
            client.getParams().setParameter("http.protocol.content-charset", "UTF-8");

            HttpPost post = new HttpPost(url);

            post.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));

            HttpResponse response = client.execute(post);

            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String line = "";
            while ((line = rd.readLine()) != null)
            {
                returnData.append(line);
            }
            rd.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
        finally
        {
            if (rd != null)
                try
                {
                    rd.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            client.getConnectionManager().shutdown();
        }
        return returnData.toString();
    }

    private List<NameValuePair> map2NameValuePairList(Map<String, String> paramMap)
    {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        Iterator<String> keys = paramMap.keySet().iterator();
        while (keys.hasNext())
        {
            String key = keys.next();
            nameValuePairs.add(new BasicNameValuePair(key, paramMap.get(key)));
        }
        return nameValuePairs;
    }
}
