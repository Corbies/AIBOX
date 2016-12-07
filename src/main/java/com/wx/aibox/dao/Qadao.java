package com.wx.aibox.dao;

import com.wx.aibox.model.QaModel;
import com.wx.aibox.model.TestModel;
import com.wx.aibox.tools.Page;

import java.util.List;

/**
 * Created by cxj on 2016/12/6.
 */
public interface Qadao {

    List<QaModel> listAllTests();

    int getCount();

    List<QaModel> getpage(Page page);

    QaModel findById(int id);

    List<QaModel> findByName(String username);

    int insert(QaModel qaModel);
}
