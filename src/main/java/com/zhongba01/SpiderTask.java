package com.zhongba01;

import com.zhongba01.dao.AccountDao;
import com.zhongba01.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */

@Component
public class SpiderTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountService accountService;

    @Scheduled(fixedDelay = 5000)
    public void reportTime() throws InterruptedException {
        long count = accountDao.count();
        System.out.println(String.format("===第%s次执行，当前时间为：%s", count, dateFormat.format(new Date())));
    }
}
