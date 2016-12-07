package com.wx.aibox.model.resp.Basics;

import java.io.Serializable;

/**
 * Created by cxj on 2016/12/6.
 */
public class Image implements Serializable{
    private String MediaId;

    @Override
    public String toString() {
        return "Image{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
