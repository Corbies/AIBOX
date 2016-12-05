package com.wx.aibox.dao;

import com.wx.aibox.model.TestModel;
import com.wx.aibox.tools.Page;

import java.util.List;

/**
 * Created by cxj on 2016/12/5.
 */

public interface Testdao {
    List<TestModel> listAllTests();
    int getCount();
    List<TestModel> getpage(Page page);
}
