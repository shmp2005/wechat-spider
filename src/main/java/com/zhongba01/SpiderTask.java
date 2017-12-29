package com.zhongba01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */

@Component
public class SpiderTask {
    final static Logger LOGGER = LoggerFactory.getLogger(SpiderTask.class);

    /**
     * 每分钟打一次
     */
    @Scheduled(fixedRate = 60000)
    public void tick() {
        LOGGER.info("ticking at " + LocalDateTime.now());
    }
}
