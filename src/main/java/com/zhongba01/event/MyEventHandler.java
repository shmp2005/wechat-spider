package com.zhongba01.event;

import com.zhongba01.VerifyCodeException;
import com.zhongba01.domain.User;
import com.zhongba01.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@Component
public class MyEventHandler {
    final static Logger LOGGER = LoggerFactory.getLogger(MyEventHandler.class);

    @Autowired
    UserService userService;

    @EventListener
    public void event(ApplicationReadyEvent event) {
        LOGGER.info("Begin craw Wechat...");
        List<User> users = userService.findActives();
        for (User s : users) {
            try {
                userService.dumpUser(s.getWeixin());
            } catch (VerifyCodeException e) {
                e.printStackTrace();
                break;
            }
        }
        LOGGER.info("End craw Wechat。");
    }
}
