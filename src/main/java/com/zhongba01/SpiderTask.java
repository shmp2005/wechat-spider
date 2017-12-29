package com.zhongba01;

import com.zhongba01.domain.User;
import com.zhongba01.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */

@Component
public class SpiderTask {
    final static Logger LOGGER = LoggerFactory.getLogger(SpiderTask.class);

    @Autowired
    UserService userService;

    @Scheduled(cron = "0 2,10,22 * * * *")
    public void crawWechat() {
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

    /**
     * 每分钟打一次
     */
    @Scheduled(fixedRate = 60000)
    public void tick() {
        LOGGER.info("ticking at " + LocalDateTime.now());
    }
}
