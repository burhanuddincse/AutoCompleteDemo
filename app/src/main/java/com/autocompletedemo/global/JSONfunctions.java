package com.autocompletedemo.global;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Android on 28-09-2015.
 */
public class JSONfunctions
{
    static InputStream is = null;
    static String strResponse;
    static JSONObject jObj = null;
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;
    static String json = "";
    public static String strException = "";


    public JSONfunctions() {

    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
    public String makeServiceCall(String url, int method ) {
        return this.makeServiceCall(url, method, null);
    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> params)
    {
        try
        {
            // http client

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method == POST)
            {
                HttpPost httpPost = new HttpPost(url);
                // adding post params
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);

            }
            else if (method == GET)
            {
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, HTTP.UTF_8);
                    url += "?" + paramString;

                    Log.e("url","" + url);
                }
                HttpGet httpGet = new HttpGet(url);

                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, HTTP.UTF_8), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            //json = sb.toString();
            strResponse =sb.toString();
            Log.e("JSON", json);
        }
        catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
            strException = "No Internet Connection. Check your internet connection.";
        }
        return response;
    }
    public JSONObject makeServiceCall(String url, String method,
                                      List<NameValuePair> params) {
        // TODO Auto-generated method stub
        try
        {
            if(method=="POST")
            {
                DefaultHttpClient httpClient=new DefaultHttpClient();
                HttpPost httpPost=new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                is=httpEntity.getContent();

            }
            else if(method=="GET")
            {
                DefaultHttpClient httpClient=new DefaultHttpClient();
                String paramString= URLEncodedUtils.format(params, "urf-8");
                url+="?"+paramString;
                HttpGet httpGet=new HttpGet(url);
                HttpResponse httpResponse=httpClient.execute(httpGet);
                HttpEntity httpEntity=httpResponse.getEntity();
                is=httpEntity.getContent();
            }
        }
        catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch(ClientProtocolException e){
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            BufferedReader reader=new BufferedReader(new InputStreamReader(is, HTTP.UTF_8), 8);
            StringBuilder sb=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null)
            {
                sb.append(line + "\n");

            }
            is.close();
            json=sb.toString();

        }
        catch(Exception e)
        {
            Log.e("Buffer error", "error converting result" + e.toString());
            strException = "No Internet Connection. Check your internet connection.";
        }
        try
        {
            jObj=new JSONObject(json);
        }
        catch(Exception e)
        {
            Log.e("Json parser", "error parsing data" + e.toString());
            strException = "No Internet Connection. Check your internet connection.";
        }
        return jObj;
    }
}
