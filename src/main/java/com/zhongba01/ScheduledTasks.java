package com.zhongba01;

import com.zhongba01.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/15.
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    AccountMapper accountMapper;

    @Scheduled(fixedRate = 1000 * 3)
    public void reportCurrentTime() {
        System.out.println("fixed: " + LocalDateTime.now());

        System.out.println(accountMapper.count());
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void reportCurrentCron() {
        System.out.println("Cron: " + LocalDateTime.now());
    }
}
