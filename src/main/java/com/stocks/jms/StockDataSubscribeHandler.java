package com.stocks.jms;

import com.stocks.models.stocks.Code;
import com.stocks.scheduler.provider.StockProvider;
import com.stocks.scheduler.provider.alphavantage.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StockDataSubscribeHandler {

    @Autowired
    StockProvider stockDataProvider;

    @JmsListener(destination = "${messaging.subscribe.subject}")
    public void receive(Set<Code> msg) {
        System.out.println("Received Message: " + msg);
        stockDataProvider.updateSymbols(msg);
    }

    public void addAsListener(StockProvider stockProvider) {
        this.stockDataProvider = stockProvider;
    }
}
