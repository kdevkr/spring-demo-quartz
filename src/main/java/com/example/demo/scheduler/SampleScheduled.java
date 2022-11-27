package com.example.demo.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile("shedlock")
@Slf4j
@Component
public class SampleScheduled {

    @SchedulerLock(name = "SampleScheduled", lockAtMostForString = "500ms")
    @Scheduled(fixedRate = 1000)
    public void schedule() {
        log.info("{}", this);
    }
}
