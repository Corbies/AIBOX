package com.wx.aibox.tools;

import org.junit.Test;

/**
 * Created by cxj on 2016/12/5.
 */
public class http {
    String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx6f41feeb796d964e&secret=0f8aab5aac94d181caa0d0cfa94b7fe6";

    @Test
    public void httpTest(){

        System.out.println(HttpClient.httpGet(url).toString());
        System.out.println(HttpClient.httpGet(url).toJSONString());
    }
}
