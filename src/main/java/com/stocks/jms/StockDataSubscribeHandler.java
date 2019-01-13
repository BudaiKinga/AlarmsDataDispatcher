package com.stocks.jms;

import com.stocks.models.stocks.Code;
import com.stocks.scheduler.provider.StockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StockDataSubscribeHandler {

    @Autowired
    StockProvider stockDataProvider;

    @JmsListener(destination = "${messaging.subscribe.subject}")
    public void receive(Set<String> msg) {
        System.out.println("Received Message: " + msg);
        stockDataProvider.updateSymbols(msg.stream().map(Code::valueOf).collect(Collectors.toSet()));
    }

    public void addAsListener(StockProvider stockProvider) {
        this.stockDataProvider = stockProvider;
    }
}
