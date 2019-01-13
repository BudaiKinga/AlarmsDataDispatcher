package com.stocks.scheduler;


import com.stocks.jms.StockDataDispatcher;
import com.stocks.models.stocks.Code;
import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.provider.StockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RequestService implements Runnable {

    @Autowired
    StockDataDispatcher publisher;

    @Autowired
    private StockProvider stockProvider;

    @Override
    public void run() {
        List<StockPriceData> result = stockProvider.getPriceData();
        publisher.send(result);
        System.out.println("Executed!");
    }

    public void suscribe(Set<Code> symbols) {
        stockProvider.updateSymbols(symbols);
    }
}
