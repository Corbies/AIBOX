package com.wx.aibox.dao;

import com.wx.aibox.model.TestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by cxj on 2016/12/5.
 */@RunWith(SpringRunner.class)
@SpringBootTest
public class testTestDao {
    private final static Logger logger= LoggerFactory.getLogger(testTestDao.class);

    @Autowired
    Testdao testdao;


    @Test
    public void listAllTests(){
        List<TestModel> list =testdao.listAllTests();
        for(TestModel testModel:list){
            logger.info(testModel.toString());
        }
    }


}
