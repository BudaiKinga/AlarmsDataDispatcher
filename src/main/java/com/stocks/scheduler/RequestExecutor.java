package com.stocks.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class RequestExecutor {
    @Value("${data.update.executor.pool.size}")
    private int corePoolSize;
    @Value("${data.update.executor.initial.delay}")
    private int initialDelay;
    @Value("${data.update.executor.rate}")
    private int executing_period;

    @Autowired
    RequestService service;

    public void startRequesting() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(corePoolSize);
        scheduledExecutorService.scheduleAtFixedRate(service, initialDelay, executing_period, TimeUnit.SECONDS);
    }

}
