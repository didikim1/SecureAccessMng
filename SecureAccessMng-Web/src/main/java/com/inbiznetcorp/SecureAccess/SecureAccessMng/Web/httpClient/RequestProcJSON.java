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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestProcJSON
{

    public JSONObject sendPacket(String url, String strParamObject)
    {
        String              strJSONObject       = this.excute(url, strParamObject);
        JSONObject          jsonObject          = this.str2JSONObject(strJSONObject);

        return jsonObject;
    }

    @SuppressWarnings("deprecation")
    private String excute(String url, String strParamObject)
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
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");

            StringEntity entity = new StringEntity(strParamObject, "UTF-8");
            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String line = "";
            while ((line = rd.readLine()) != null)
            {
                System.out.println("line:::" +line);
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

    private JSONObject str2JSONObject(String strJSONObject)
    {
        JSONObject json = null;

        try
        {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(strJSONObject);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return json;
    }
}
