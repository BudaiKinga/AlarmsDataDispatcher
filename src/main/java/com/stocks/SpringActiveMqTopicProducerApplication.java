package com.stocks;

import com.stocks.models.stocks.Code;
import com.stocks.scheduler.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.stocks" })
public class SpringActiveMqTopicProducerApplication implements CommandLineRunner {

    @Autowired
    private RequestExecutor executor;
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringActiveMqTopicProducerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringActiveMqTopicProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        executor.startRequesting();
        LOGGER.info("Stock Price data provider started successfully.");
    }
}
