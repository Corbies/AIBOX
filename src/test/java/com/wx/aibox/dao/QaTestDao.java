package com.wx.aibox.dao;

import com.wx.aibox.model.QaModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cxj on 2016/12/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QaTestDao {

    private final static Logger logger= LoggerFactory.getLogger(QaTestDao.class);

    @Autowired
    Qadao qadao;

    @Test
    public void insert (){
        QaModel qaModel=new QaModel();
        qaModel.setEmail("1231");
        qaModel.setProblem("问题");
        qaModel.setUsername("usjre");
        qaModel.setAnswer("fawef");
        qadao.insert(qaModel);
    }
}
