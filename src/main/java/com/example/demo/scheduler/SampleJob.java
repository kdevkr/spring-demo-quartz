package com.example.demo.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleJob extends QuartzJobBean {
    public static final String JOB_NAME = "SampleJob";
    public static final String JOB_DETAIL_NAME = JOB_NAME + "Detail";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("{}, {}, {}", context.getTrigger().getKey().toString(), context.getJobInstance().toString(), context.getFireTime());
    }

    @Bean(JOB_DETAIL_NAME)
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(SampleJob.class)
                .storeDurably()
                .withIdentity("SampleJobDetail")
                .withDescription("Invoke Sample Job...")
                .build();
    }

    @Bean
    public Trigger simpleTrigger(@Qualifier(JOB_DETAIL_NAME) JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("SampleJobTrigger")
                .withDescription("Sample trigger with interval")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatForever().withIntervalInSeconds(5))
                .build();
    }

    @Bean
    public Trigger cronTrigger(@Qualifier(JOB_DETAIL_NAME) JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("SampleJobTrigger2")
                .withDescription("Sample trigger with cron")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();
    }
}
