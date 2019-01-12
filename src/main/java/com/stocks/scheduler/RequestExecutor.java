package com.stocks.scheduler;

import com.stocks.models.stocks.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class RequestExecutor {
    // configure from file?
    public static int CORE_POOL_SIZE = 5;
    public static int INITIAL_DELAY = 0;
    public static int PERIOD = 12;
    private static TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    @Autowired
    RequestService service;

    public void subscribe(Set<Code> symbols) {
        service.suscribe(symbols);
    }

    public void startRequesting() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                service.run();
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(r, INITIAL_DELAY, PERIOD, TIME_UNIT);
    }

}
