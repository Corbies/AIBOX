package com.wx.aibox.service;

import com.wx.aibox.dao.Testdao;
import com.wx.aibox.model.TestModel;
import com.wx.aibox.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxj on 2016/12/5.
 */
@Service
public class TestService {

    @Autowired
    Testdao testdao;

    public List<TestModel>  getpage(int pageNumber){
       // Map<String,Map<Page,List<TestModel>>> map= new HashMap<>();
        //Map<Page,List<TestModel>> mapPageList= new HashMap<>();
        Page page=new Page();
        page.setPageNumber(pageNumber);
        page.setTotalNumber(testdao.getCount());
        List<TestModel> list= testdao.getpage(page);
        return list;
    }
    int getCount(){
       return testdao.getCount();
    }
    List<TestModel> listAll(){
        return testdao.listAllTests();
    }
}
