package com.wx.aibox.controller;

import com.wx.aibox.service.CoreService;
import com.wx.aibox.tools.SignUtil;
import org.apache.cxf.helpers.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/")
public class CoreController {
    private static final Logger logger = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    CoreService coreService;

    //验证是否来自微信服务器的消息
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public String checkSignature(@RequestParam(name = "signature", required = false) String signature,
                                 @RequestParam(name = "nonce", required = false) String nonce,
                                 @RequestParam(name = "timestamp", required = false) String timestamp,
                                 @RequestParam(name = "echostr", required = false) String echostr) {
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        logger.info(" {},{},{}",signature,timestamp,nonce);
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "";
    }

    // 调用核心业务类接收消息、处理消息跟推送消息，这里暂时不做处理

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/xml; charset=UTF-8")
    public String post(HttpServletRequest req) throws IOException{
        InputStream inputStream = req.getInputStream();
        String postData = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(postData);
        String respMessage = coreService.processRequest(postData);
        return respMessage;
    }
}
