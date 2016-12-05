package com.wx.aibox.controller;

import com.wx.aibox.model.TestModel;
import com.wx.aibox.service.TestService;
import com.wx.aibox.tools.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by cxj on 2016/12/5.
 */
@Controller
public class testcontroller {
    private static final Logger logger = LoggerFactory.getLogger(testcontroller.class);

    @Autowired
    TestService testService;

    @RequestMapping(value = "/page")
    @ResponseBody
    public List<TestModel> index() {
        return testService.getpage(3);
    }
}
