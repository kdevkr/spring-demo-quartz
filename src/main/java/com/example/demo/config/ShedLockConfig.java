package com.example.demo.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Profile("shedlock")
@EnableSchedulerLock(interceptMode = EnableSchedulerLock.InterceptMode.PROXY_SCHEDULER, defaultLockAtMostFor = "10m")
@Configuration(proxyBeanMethods = false)
public class ShedLockConfig {

    @Bean
    public LockProvider lockProvider(RedisConnectionFactory connectionFactory) {
        return new RedisLockProvider(connectionFactory, "DEFAULT");
    }

}
