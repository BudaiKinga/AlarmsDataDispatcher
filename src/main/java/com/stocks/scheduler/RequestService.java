package com.stocks.scheduler;


import com.stocks.messaging.StockDataDispatcher;
import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.provider.StockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestService implements Runnable {

    @Autowired
    StockDataDispatcher publisher;

    @Autowired
    private StockProvider stockProvider;

    @Override
    public void run() {
        List<StockPriceData> result = stockProvider.getPriceData();
        if (result.isEmpty()) {
            return;
        }
        publisher.send(result);
    }
}
