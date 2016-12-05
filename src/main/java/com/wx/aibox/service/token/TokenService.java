package com.wx.aibox.service.token;

import com.alibaba.fastjson.JSONObject;
import com.wx.aibox.model.TokenAuthEachRsp;
import com.wx.aibox.tools.Constant;
import com.wx.aibox.tools.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cxj on 2016/12/5.
 */
@Service
public class TokenService {

    @Autowired
    TokenAuthEachRsp tokenAuthEachRsp;

    private static  final Logger logger= LoggerFactory.getLogger(TokenService.class);

    public void getToken() {

        try {
            JSONObject  jsonObject = HttpClient.httpGet(Constant.tokenauth + "?grant_type=" + Constant.grant_type + "&appid=" + Constant.appid + "&secret=" + Constant.secret);
            tokenAuthEachRsp = (TokenAuthEachRsp) jsonObject.toJavaObject(TokenAuthEachRsp.class);
            logger.info("成功"+tokenAuthEachRsp.toString());

        }catch (Exception e){
            logger.error(e.toString());
            try{Thread.sleep(2000);}catch (InterruptedException r){r.printStackTrace();}
            logger.info("token 失败 重新发送");
            getToken();
        }

    }
}
