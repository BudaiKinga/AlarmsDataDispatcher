package com.stocks;

import com.stocks.scheduler.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.stocks" })
public class StockDataProducer implements CommandLineRunner {

    @Autowired
    private RequestExecutor executor;
    private static final Logger LOGGER = LoggerFactory.getLogger(StockDataProducer.class);

    public static void main(String[] args) {
        SpringApplication.run(StockDataProducer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        executor.startRequesting();
        LOGGER.info("Stock Price data provider started successfully.");
    }
}
