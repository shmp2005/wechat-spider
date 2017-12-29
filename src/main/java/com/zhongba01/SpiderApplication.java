package com.zhongba01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ author: tangjianhua
 * @ date: 2017/12/8.
 */
@SpringBootApplication
@EnableScheduling
public class SpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }
}
