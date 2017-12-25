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
        accountService.dumpAccounts("价值投资");
//        String wxAccount = "yc2585728466";
//        String profileUrl = "https://mp.weixin.qq.com/profile?src=3&timestamp=1513219190&ver=1&signature=A0*5qK1VldQj3xNfk7R3D5a4O-sZg7pGQBh2b44tqbwQbVUhg1L0DbIgsC07BZL0fGx6NGHptMb3ZkNTIykS-Q==";
//        accountService.accountProfile(wxAccount, profileUrl);
    }
}
