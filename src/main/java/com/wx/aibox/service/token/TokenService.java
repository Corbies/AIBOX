package com.wx.aibox.service.token;

import com.alibaba.fastjson.JSONObject;
import com.wx.aibox.model.TokenAuthEachRsp;
import com.wx.aibox.tools.Constant;
import com.wx.aibox.tools.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by cxj on 2016/12/5.
 */
@Service
public class TokenService {

    @Autowired
    TokenAuthEachRsp tokenAuthEachRsp;

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    public void getToken() throws UnsupportedEncodingException {
        String strurl = Constant.url;
        logger.info("" + strurl);
        URI uri;
        //  url=URLEncoder.encode(url, "UTF-8");
//        strurl = strurl.replaceAll("&", "%26");
//        strurl = strurl.replaceAll(" ", "%20");
        try {

            URL url = new URL(strurl);
             uri= new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            logger.info("发送的uri" + uri.toString());
            JSONObject jsonObject = HttpClient.httpGet(uri.toString());
            logger.info(" json" + jsonObject.toString());
            try{
            tokenAuthEachRsp = (TokenAuthEachRsp) jsonObject.toJavaObject(TokenAuthEachRsp.class);
            }catch (Exception e){
                e.printStackTrace();
                logger.error(jsonObject.toString());
            }
            logger.info("成功" + tokenAuthEachRsp.toString());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
            logger.info("token 失败 重新发送");
           getToken();
        }

    }
}
