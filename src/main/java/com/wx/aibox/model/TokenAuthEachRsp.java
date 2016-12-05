package com.wx.aibox.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by cxj on 2016/12/5.
 */
@Component
@Scope("singleton")
public class TokenAuthEachRsp implements Serializable{
    String access_token;
    long expires_in;

    @Override
    public String toString() {
        return "tokenAuthEachRsp{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
