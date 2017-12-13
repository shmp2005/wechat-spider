package com.zhongba01.event;

import com.zhongba01.service.AccountService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@Component
public class MyEventHandler {

    @EventListener
    public void event(ApplicationReadyEvent event) {
        AccountService accountService = event.getApplicationContext().getBean(AccountService.class);
        accountService.dumpAccounts("财富");
    }
}
