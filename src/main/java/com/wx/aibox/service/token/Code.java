package com.wx.aibox.service.token;

import java.io.Serializable;

/**
 * Created by cxj on 2016/8/15.
 */
public class Code implements Serializable {
    private  int code;

    public Code(int code) {
        this.code = code;
    }
    public Code(){
        super();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
