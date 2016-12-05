package com.wx.aibox.model;

import java.io.Serializable;

/**
 * Created by cxj on 2016/12/5.
 */
public class TestModel implements Serializable {
    private  int id;
    private  String test;


    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", test='" + test + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
