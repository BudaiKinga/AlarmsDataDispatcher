package com.stocks.config;

import com.stocks.StockUpdateHandler;
import com.stocks.scheduler.RequestExecutor;
import com.stocks.scheduler.RequestService;
import com.stocks.scheduler.provider.StockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class StockDataHandlerFactoryConfig {

    @Bean
    @DependsOn({"stockProvider"})
    public RequestService service() {
        RequestService service = new RequestService();
        return service;
    }

    @Bean
    public StockUpdateHandler stockUpdateHandler() {
        StockUpdateHandler su = new StockUpdateHandler();
        return su;
    }

    @Bean
    public RequestExecutor executor() {
        RequestExecutor executor = new RequestExecutor();
        return executor;
    }

    @Bean("stockProvider")
    public StockProvider stockProvider() {
        StockProvider stockProvider = new StockProvider();
        return stockProvider;
    }
}
