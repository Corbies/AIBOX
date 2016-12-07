package com.wx.aibox.model;

/**
 * Created by cxj on 2016/12/6.
 */
public class TokenAuthEachRspError {
    private  long errcode ;
    private String errmsg;

    @Override
    public String toString() {
        return "TokenAuthEachRspError{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }

    public long getErrcode() {
        return errcode;
    }

    public void setErrcode(long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
