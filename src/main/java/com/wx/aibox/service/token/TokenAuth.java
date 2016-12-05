package com.wx.aibox.service.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cxj on 2016/12/5.
 */
@Service
@EnableScheduling
public class TokenAuth {
        private Logger logger = LoggerFactory.getLogger(TokenAuth.class);

        private static final SimpleDateFormat dateFormat = new SimpleDateFormat();


        @Autowired
        private TokenService tokenService;

        @Scheduled(fixedRate = 7000000)
        public void reportCurrentTime() {
            logger.info(">Update token" + dateFormat.format(new Date()));
            tokenService.getToken();
            logger.info("<Update token");
        }


    }

