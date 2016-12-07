package com.wx.aibox.model.resp;

import java.io.Serializable;

/**
 * Created by cxj on 2016/12/6.
 */
public class TextMessage extends BaseMessage implements Serializable {

    private String Content;
    private String MsgId;
    private  int FuncFlag;

    @Override
    public String toString() {
        return "TextMessage{" +
                "Content='" + Content + '\'' +
                ", MsgId='" + MsgId + '\'' +
                ", FuncFlag=" + FuncFlag +
                '}';
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }


    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
