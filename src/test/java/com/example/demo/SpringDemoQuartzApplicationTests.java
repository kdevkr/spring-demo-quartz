package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@SpringBootTest
class SpringDemoQuartzApplicationTests {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Test
    void contextLoads() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        Assertions.assertNotNull(scheduler);
    }

}
