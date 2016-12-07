package com.wx.aibox.tools;


import com.alibaba.fastjson.JSONObject;
import com.wx.aibox.service.token.Code;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.net.URLDecoder;

import static org.apache.commons.httpclient.methods.multipart.FilePart.DEFAULT_CHARSET;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.HOST;

public class HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    public static JSONObject httpPost(String url,JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }


    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            method.setHeader("Authorization","");
            Base64Utils.decode(new String().getBytes());


            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            logger.info("code:    "+result.getStatusLine().getStatusCode());
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }else {
                jsonResult=  JSONObject.parseObject(String.valueOf(result.getStatusLine().getStatusCode()));
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse,  String Authorization){
        //post请求返回结果

        //   DefaultHttpClient httpClient = new DefaultHttpClient();
        CloseableHttpClient httpClient= HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "UTF-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            method.setHeader("Authorization","Bearer "+Authorization);
            Base64Utils.decode(new String().getBytes());


            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");

            logger.info("code:    "+result.getStatusLine().getStatusCode());
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败状态:" + url, e);
                }
            }else {
                jsonResult=  JSONObject.parseObject(String.valueOf(result.getStatusLine().getStatusCode()));
            }

        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        logger.info(" httpGet:   {}",url);
        JSONObject jsonResult = null;
        try {
            CloseableHttpClient client= HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(url);
            request.addHeader("Accept-Charset", "UTF-8");
            request.addHeader("Host", HOST);
            request.addHeader("Accept", ACCEPT);
            HttpResponse response = client.execute(request);
            logger.info(" response.getStatusLine().getStatusCode(){}",response.getStatusLine().getStatusCode());
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url+"code"+response.getStatusLine().getStatusCode());
                jsonResult=  JSONObject.parseObject(String.valueOf(response.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("get请求提交失败:" + url, e);
        }
        logger.info("result" + jsonResult.toString());
        return jsonResult;

    }


}
