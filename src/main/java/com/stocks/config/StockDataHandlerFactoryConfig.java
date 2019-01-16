package com.stocks.config;

import com.stocks.scheduler.RequestExecutor;
import com.stocks.scheduler.RequestService;
import com.stocks.scheduler.provider.StockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Class used to configure stock update executor, service and provider beans to periodically send
 * request for price data to Alphavantage for subscribed stocks.
 */
@Configuration
public class StockDataHandlerFactoryConfig {

    @Bean
    @DependsOn({"stockProvider"})
    public RequestService service() {
        RequestService service = new RequestService();
        return service;
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
