package com.stocks;

import com.stocks.scheduler.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StockUpdateHandler implements Runnable {

    @Autowired
    private RequestExecutor executor;
    private static final Logger LOGGER = LoggerFactory.getLogger(StockUpdateHandler.class);

    @Override
    public void run() {
        executor.startRequesting();
        LOGGER.info("Stock Price data provider started successfully.");
    }
}
