package com.stocks;

import com.stocks.models.stocks.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.stocks" })
public class SpringActiveMqTopicProducerApplication implements CommandLineRunner {

    @Autowired
    StockUpdateHandler stockUpdateHandler;


    public static void main(String[] args) {
        SpringApplication.run(SpringActiveMqTopicProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        stockUpdateHandler.subscribe(Code.NCBS);
        Thread th = new Thread(stockUpdateHandler);
        th.start();

    }
}
